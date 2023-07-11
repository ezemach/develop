package com.example.quintoimpacto.DTOs;


import com.example.quintoimpacto.models.Inscription;
import com.example.quintoimpacto.models.Shift;
import com.example.quintoimpacto.models.User;

import java.time.LocalDateTime;

public class InscriptionDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Shift shift;
    private User user;
    private CourseDTO courseDTO;
    public InscriptionDTO(Inscription inscription){
        this.id = inscription.getId();
        this.dateTime = inscription.getDateTime();
        this.user = inscription.getUser();
        this.courseDTO = new CourseDTO(inscription.getCourse());
        this.shift = inscription.getShift();
    }

    /* GETTERS */
    public Long getId() {return id;}
    public LocalDateTime getDateTime() {return dateTime;}
    public User getUser() {return user;}
    public CourseDTO getCourseDTO() {return courseDTO;}
    public Shift getShift() {return shift;}
}