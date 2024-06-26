package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;

@Data
public class CreateCourseDTO {
    private String courseCode;
    private String courseName;
    private Double weight;
}
