package rw.ac.rca.OnlineShop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.OnlineShop.DTOs.AddMarksDTO;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.models.Course;
import rw.ac.rca.OnlineShop.models.Mark;
import rw.ac.rca.OnlineShop.models.Student;
import rw.ac.rca.OnlineShop.repositories.ICourseRepository;
import rw.ac.rca.OnlineShop.repositories.IStudentRepository;
import rw.ac.rca.OnlineShop.services.ICourseService;
import rw.ac.rca.OnlineShop.services.IMarkService;
import rw.ac.rca.OnlineShop.services.IStudentService;

import java.util.NoSuchElementException;


@AllArgsConstructor
@RequestMapping("/api/v1/marks")
public class MarkController {
    private ICourseRepository courseRepository;
    private IStudentRepository studentRepository;
    private IMarkService markService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMark(@RequestBody AddMarksDTO marksDTO){
        Course course = courseRepository.findById(marksDTO.getCourseId()).orElseThrow(() -> new NoSuchElementException("Course not found"));
        Student student = studentRepository.findById(marksDTO.getStudentId()).orElseThrow(() -> new NoSuchElementException("Student not found"));
        Mark newMark = new Mark();
        newMark.setCourse(course);
        newMark.setStudent(student);
        newMark.setScore(marksDTO.getScore());
        newMark = markService.createMark(newMark);
        return ResponseEntity.ok(new ApiResponse("Marks assigned", HttpStatus.CREATED,newMark));
    }
}
