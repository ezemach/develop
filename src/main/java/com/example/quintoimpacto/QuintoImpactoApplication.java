package com.example.quintoimpacto;

import com.example.quintoimpacto.models.Course;
import com.example.quintoimpacto.models.Shift;
import com.example.quintoimpacto.models.User;
import com.example.quintoimpacto.repositories.CourseRepository;
import com.example.quintoimpacto.repositories.InscriptionRepository;
import com.example.quintoimpacto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class QuintoImpactoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuintoImpactoApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, InscriptionRepository inscriptionRepository, CourseRepository courseRepository){
        return (args -> {
            // Student 1
            User Student1 = new User(
                    "001-01-5554",
                    "Michael",
                    "Jordan",
                    "michael@gmail.com",
                    "843-444-1525",
                    "03/16/1995",
                    "New York",
                    "Mount Vernon",
                    "326 Kennedy Dr",
                    passwordEncoder.encode("basketball"));
            // Save Student 1
            userRepository.save(Student1);

            // Student 2
            User Student2 = new User(
                    "001-01-5554",
                    "Paul",
                    "Aaron",
                    "aaron@gmail.com",
                    "561-249-0865",
                    "15/05/1987",
                    "Florida",
                    "Miami",
                    "360 NW 98th St",
                    passwordEncoder.encode("aaron"));
            // Save Student 2
            userRepository.save(Student2);

            // Student 3
            User Student3 = new User(
                    "001-01-5560",
                    "Walter",
                    "White",
                    "walter@gmail.com",
                    "561-249-0877",
                    "04/12/1987",
                    "New York",
                    "California",
                    "380 99th St",
                    passwordEncoder.encode("walter"));
            // Save Student 3
            userRepository.save(Student3);

            // Teacher 1
            User teacher1 = new User(
                    "001-01-7088",
                    "Ivan ",
                    "San Miguel",
                    "ivansanmiguel@edu.com",
                    "561-249-0999",
                    "12/10/1987",
                    "New York",
                    "California",
                    "380 40th St",
                    passwordEncoder.encode("teacher1"));
            //Save Teacher 1
            userRepository.save(teacher1);

            // Teacher 1
            User teacher2 = new User(
                    "001-01-6060",
                    "Ezequiel",
                    "Mazzucco",
                    "ezequielmazzucco@edu.com",
                    "561-249-4458",
                    "23/11/1987",
                    "New York",
                    "California",
                    "370 40th St",
                    passwordEncoder.encode("teacher2"));
            //Save Teacher 1
            userRepository.save(teacher2);

            // Admin
            User admin = new User(
                    "001-01-6060",
                    "admin",
                    "admin",
                    "admin@admin.com",
                    "561-249-000",
                    "01/01/2000",
                    "New York",
                    "California",
                    "200 40th St",
                    passwordEncoder.encode("admin"));
            //Save admin
            userRepository.save(admin);

            //Course 1
            Course course1 = new Course(
                    "Full Stack web developer",
                    "is a specialist in the development of websites that integrates the design of the customer experience on a page, as well as the programming and maintenance of the internal architecture of the site.",
                    List.of(Shift.MORNING),
                    "./img/Courses/card1Computacion.jpg");
            //Save Course 1
            courseRepository.save(course1);

            //Course 2
            Course course2 = new Course(
                    "Community Manager",
                    "Setting and implementing social media and communication campaigns to align with marketing strategies.",
                    List.of(Shift.MORNING),
                    "./img/Courses/card2Management.jpg");
            //Save Course 2
            courseRepository.save(course2);

            //Course 3
            Course course3 = new Course(
                    "Back end developer",
                    "responsibilities include creating, maintaining, testing, and debugging the entire back end of an application or system.",
                    List.of(Shift.AFTERNOON),
                    "./img/Courses/card3ComunityManager.jpg");
            //Save Course 3
            courseRepository.save(course3);

            //Course 4
            Course course4 = new Course(
                    "Front end developer",
                    "computer programmers who specialize in website design. ",
                    List.of(Shift.AFTERNOON),
                    "./img/Courses/card4.jpg");
            //Save Course 4
            courseRepository.save(course4);

            //Course 5
            Course course5 = new Course(
                    "communication",
                    " the exchange of ideas and thoughts within two or more individuals. It can be done through writing, speech, gestures, symbols, or written communication.  ",
                    List.of(Shift.AFTERNOON),
                    "./img/Courses/card5.jpg");
            //Save Course 5
            courseRepository.save(course5);

            //Course 6
            Course course6 = new Course(
                    "Project management",
                    "the application of processes, methods, skills, knowledge and experience to achieve specific project objectives according to the project acceptance criteria within agreed parameters. ",
                    List.of(Shift.NIGHT),
                    "./img/Courses/card6.jpg");
            //Save Course 6
            courseRepository.save(course6);

            //Course 7
            Course course7 = new Course(
                    "QA Tester",
                    "play a critical role in delivering high quality, perfectly-functioning software and web applications to customers. ",
                    List.of(Shift.AFTERNOON),
                    "./img/Courses/card7.jpg");
            //Save Course 7
            courseRepository.save(course7);

            //Course 8
            Course course8 = new Course(
                    "Digital Design",
                    "is a type of visual communication that presents information or a product or service through a digital interface. ",
                    List.of(Shift.NIGHT),
                    "./img/Courses/card8.jpg");
            //Save Course 8
            courseRepository.save(course8);

            //Course 9
            Course course9 = new Course(
                    "Administrative Assistant",
                    "making travel and meeting arrangements, preparing reports and maintaining appropriate filing systems. ",
                    List.of(Shift.NIGHT),
                    "./img/Courses/card9.jpg");
            //Save Course 9
            courseRepository.save(course9);


        });
    }
}
