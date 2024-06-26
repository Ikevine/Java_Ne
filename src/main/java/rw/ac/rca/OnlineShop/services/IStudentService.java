package rw.ac.rca.OnlineShop.services;

import rw.ac.rca.OnlineShop.models.Student;

import java.util.List;

public interface IStudentService {
    Student create(Student student);
    List<Student> getStudents();
}
