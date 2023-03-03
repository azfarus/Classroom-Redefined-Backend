package com.example.classroom.controllers;


import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.login.LoginDTO;
import com.example.classroom.student.Student;
import com.example.classroom.student.StudentDTO;
import com.example.classroom.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private StudentRepository stud;
    @GetMapping("/student")
    @ResponseBody
    public StudentDTO student_login(@RequestBody LoginDTO ldto){
        StudentDTO sdto = new StudentDTO();

        sdto.setStudid((long) -1);


        Optional<Student> sopt = stud.findStudentByStudid(ldto.getStudentid());

        if(sopt.isPresent()){
            if(sopt.get().getPassword().equals(ldto.getPassword())){
                sdto = new StudentDTO(sopt.get());
            }
        }
        return sdto;
    }

}
