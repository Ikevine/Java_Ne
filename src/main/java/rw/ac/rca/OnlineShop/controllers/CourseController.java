package rw.ac.rca.OnlineShop.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.OnlineShop.DTOs.CreateCourseDTO;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.models.Course;
import rw.ac.rca.OnlineShop.services.ICourseService;

import java.util.List;


@AllArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
       private ICourseService courseService;
       private ModelMapper modelMapper;
       @PostMapping("/create")
       public ResponseEntity<ApiResponse> createCourse(@RequestBody CreateCourseDTO courseDTO){
           Course newCourse = new Course();
           modelMapper.map(courseDTO,newCourse);
           newCourse = courseService.create(newCourse);
           return ResponseEntity.ok(new ApiResponse("Course Created successfully", HttpStatus.CREATED, newCourse));
       }

       @GetMapping
       public ResponseEntity<ApiResponse> getCourses(){
           List<Course> courses = courseService.getCourses();
           return ResponseEntity.ok(new ApiResponse("Courses",HttpStatus.OK,courses));
       }
}
