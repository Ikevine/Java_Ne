package rw.ac.rca.OnlineShop.services;

import rw.ac.rca.OnlineShop.models.Course;

import java.util.List;

public interface ICourseService {
    Course create(Course course);
    List<Course> getCourses();
}
