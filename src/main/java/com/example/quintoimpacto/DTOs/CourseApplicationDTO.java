package com.example.quintoimpacto.DTOs;

import com.example.quintoimpacto.models.Shift;

import java.util.List;
import java.util.Set;

public class CourseApplicationDTO {
    private String name;
    private String description;
    private Set<Shift> shifts;
    private String imageUrl;

    // GETTERS
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Set<Shift> getShifts() {return shifts;}

    public String getImageUrl() {
        return imageUrl;
    }
}

