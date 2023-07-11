package com.example.quintoimpacto.controllers;

import com.example.quintoimpacto.DTOs.InscriptionApplicationDTO;
import com.example.quintoimpacto.DTOs.InscriptionDTO;
import com.example.quintoimpacto.models.Course;
import com.example.quintoimpacto.models.Inscription;
import com.example.quintoimpacto.models.User;
import com.example.quintoimpacto.repositories.CourseRepository;
import com.example.quintoimpacto.repositories.InscriptionRepository;
import com.example.quintoimpacto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class InscriptionController {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    // Get all inscriptions
    @GetMapping("/api/inscriptions")
    public List<InscriptionDTO> getInscriptions(){
        return inscriptionRepository.findAll()
                .stream()
                .map(inscription -> new InscriptionDTO(inscription))
                .collect(Collectors.toList());
    }
    // Create a new inscription
    @PostMapping("/api/inscriptions")
    public ResponseEntity<Object> createInscription(@RequestBody InscriptionApplicationDTO inscriptionApplicationDTO,
                                                    Authentication authentication){
        //Get rol authentication
        String rol = authentication.getAuthorities().stream().collect(toList()).get(0).toString();
        // Get user to enroll
        User userToEnroll = userRepository.findById(inscriptionApplicationDTO.getUser_id()).orElse(null);
        // Get course to enroll user
        Course courseToEnroll = courseRepository.findById(inscriptionApplicationDTO.getCourse_id()).orElse(null);
        // Get Inscription user
        List <Inscription> inscriptionUser = userToEnroll.getInscriptions().stream().collect(Collectors.toList());
        // Validation User
        if(userToEnroll == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.FORBIDDEN);
        }
        // Validation course
        if(courseToEnroll == null){
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.FORBIDDEN);
        }
        // Validation shift
        if(!inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("MORNING") &&
                !inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("AFTERNOON") &&
                !inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("NIGHT")){
            return new ResponseEntity<>("Wrong shift, the shift available are 'MORNING', 'AFTERNOON' and 'NIGHT'", HttpStatus.FORBIDDEN);
        }
        if (rol.equals("STUDENT")) {
            if (inscriptionUser.stream().anyMatch(inscription -> inscription.getCourse().getId().equals(inscriptionApplicationDTO.getCourse_id())))
                return new ResponseEntity<>("Your already are inscripted in this course", HttpStatus.FORBIDDEN);
        } else if (rol.equals("TEACHER")) {
            if (inscriptionUser.stream().anyMatch(inscription -> inscription.getShift().equals(inscriptionApplicationDTO.getShift()))){
                return new ResponseEntity<>("Your already are inscripted in this shift, please choose other", HttpStatus.FORBIDDEN);
            }

        }


        // Create a new inscription
        Inscription newInscription = new Inscription();
        // Set Shift
        newInscription.setShift(inscriptionApplicationDTO.getShift());
        // Set LocalDateTime
        newInscription.setDateTime(LocalDateTime.now());
        // Save inscription
        inscriptionRepository.save(newInscription);
        // Add user to inscription
        userToEnroll.addInscription(newInscription);
        // Save user
        userRepository.save(userToEnroll);
        // Add course to inscription
        courseToEnroll.addInscription(newInscription);
        // Save course
        courseRepository.save(courseToEnroll);

        return new ResponseEntity<>("Inscription successfully", HttpStatus.CREATED);
    }
    // Modify an inscription
    @PutMapping("/api/inscriptions/{id}")
    public ResponseEntity<Object> modifyInscription(@PathVariable Long id, @RequestBody InscriptionApplicationDTO inscriptionApplicationDTO){
        // Get inscription
        Inscription inscriptionToModify = inscriptionRepository.findById(id).orElse(null);
        // Get User
        User userToEnroll = userRepository.findById(inscriptionApplicationDTO.getUser_id()).orElse(null);
        // Get Course
        Course courseToEnroll = courseRepository.findById(inscriptionApplicationDTO.getCourse_id()).orElse(null);
        // Validation inscription
        if(inscriptionToModify == null){
            return new ResponseEntity<>("Inscription doesn't exist", HttpStatus.FORBIDDEN);
        }
        // Validation User
        if(userToEnroll == null){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.FORBIDDEN);
        }
        // Validation course
        if(courseToEnroll == null){
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.FORBIDDEN);
        }
        // Validation shift
        if(!inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("MORNING") &&
                !inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("AFTERNOON") &&
                !inscriptionApplicationDTO.getShift().name().equalsIgnoreCase("NIGHT")){
            return new ResponseEntity<>("Wrong shift, the shift available are 'MORNING', 'AFTERNOON' and 'NIGHT'", HttpStatus.FORBIDDEN);
        }
        // Set changes
        inscriptionToModify.setUser(userToEnroll); // User
        inscriptionToModify.setCourse(courseToEnroll); // Course
        inscriptionToModify.setShift(inscriptionApplicationDTO.getShift()); // Shift

        return new ResponseEntity<>("Inscription modified successfully", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/api/inscriptions/{id}")
    public void deleteInscriptions(@PathVariable Long id){
        Inscription inscriptionToDelete = inscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inscription not found for this id ::" + id));
        // Delete inscription
        inscriptionRepository.delete(inscriptionToDelete);
    }

}
