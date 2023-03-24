package com.example.classroom.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher , Long> {

    Optional<Teacher> findTeacherByTeachid(Long teach_id);
}
