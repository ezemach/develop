package com.example.quintoimpacto.DTOs;

import com.example.quintoimpacto.models.Shift;

public class InscriptionApplicationDTO {
    private Long user_id;
    private Long course_id;
    private Shift shift;

    // GETTERS
    public Long getUser_id() {return user_id;}
    public Long getCourse_id() {return course_id;}
    public Shift getShift() {return shift;}
}