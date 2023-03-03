package com.example.arparina.classroom;

import com.example.arparina.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom , Long> {


    List<Classroom> findClassroomsByStudents(Student k);
}
