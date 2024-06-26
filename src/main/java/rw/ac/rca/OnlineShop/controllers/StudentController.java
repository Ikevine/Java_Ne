package rw.ac.rca.OnlineShop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.OnlineShop.DTOs.CreateStudentDTO;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.models.Course;
import rw.ac.rca.OnlineShop.models.Student;
import rw.ac.rca.OnlineShop.repositories.ICourseRepository;
import rw.ac.rca.OnlineShop.services.ICourseService;
import rw.ac.rca.OnlineShop.services.IStudentService;

import java.util.*;


@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {
    private IStudentService studentService;
    private ICourseRepository courseRepository;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStudent(@RequestBody() CreateStudentDTO studentDTO){
            Student newStudent = new Student();
            Set<Course> courses = new HashSet<>();
            for(UUID uuid : studentDTO.getCourses()){
                Course course = courseRepository.findById(uuid).orElseThrow(() -> new NoSuchElementException("Course not found!"));
                System.out.println(course);
                if(course != null) courses.add(course);
            }
            newStudent.setFullNames(studentDTO.getFullNames());
            newStudent.setGrade(studentDTO.getGrade());
            newStudent.setCourses(courses);
            newStudent = studentService.create(newStudent);
            return ResponseEntity.ok(new ApiResponse("Student created successfully", HttpStatus.CREATED,newStudent));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getStudents(){
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(new ApiResponse("Students",HttpStatus.OK,students));
    }

}
