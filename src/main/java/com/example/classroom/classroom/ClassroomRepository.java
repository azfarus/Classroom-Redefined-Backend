package com.example.classroom.classroom;

import com.example.classroom.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom , Long> {


    List<Classroom> findClassroomsByStudents(Student k);
}
