package com.example.classroom.student;

import com.google.gson.Gson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
        Optional<Student> findStudentByStudid(Long studid);
        List<Student> findStudentsByDept(String dept);

}
