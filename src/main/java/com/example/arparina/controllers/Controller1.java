package com.example.arparina.controllers;

import com.example.arparina.classroom.Classroom;
import com.example.arparina.classroom.ClassroomDTO;
import com.example.arparina.classroom.ClassroomRepository;
import com.example.arparina.student.Student;
import com.example.arparina.student.StudentDTO;
import com.example.arparina.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class Controller1 {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    StudentRepository stud ;

    @Autowired
    ClassroomRepository clas ;

    @PostMapping("/insert")
    public String insert_student(@RequestBody StudentDTO student){
        Student s = new Student();
        s.setCgpa(student.getCgpa());

        Classroom c = new Classroom();
        c.setDept("ABC");

        c.getStudents().add(s);
        clas.save(c);
        stud.save(s);

        return "Done" + student.getCgpa();

    }

    @GetMapping("/find")
    @ResponseBody
    public StudentDTO find_student(@RequestBody StudentDTO student){


        Optional<Student> s = stud.findById(student.getId());

        //s.get().getClassrooms().addAll(clas.findClassroomsByStudents(s.get()));
        StudentDTO sdto = new StudentDTO(s.get());
        return sdto;





    }

    @GetMapping("/findclass")
    @ResponseBody
    public ClassroomDTO find_student(@RequestBody ClassroomDTO clss){


        Optional<Classroom> s = clas.findById(clss.getId());


        ClassroomDTO cdto = new ClassroomDTO(s.get());
        return cdto;





    }


}
