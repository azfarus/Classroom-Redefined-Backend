package com.example.classroom.controllers;


import com.example.classroom.classroom.Classroom;
import com.example.classroom.classroom.ClassroomDTO;
import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.*;

@RestController
@RequestMapping("/fetch")
public class PreClassroomController {

    @Autowired
    ClassroomRepository clas;

    @Autowired
    StudentRepository stud;
    @GetMapping("/classroom/{id}")
    @ResponseBody
    public ClassroomDTO fetch_class(@PathVariable Long id){
        Optional<Classroom> copt = clas.findById(id);
        ClassroomDTO cdto = new ClassroomDTO();
        cdto.setId((long)-1);
        if(copt.isPresent()){
            Classroom c = copt.get();
//            if(c.getStudents().isEmpty()){
//                copt.get().getStudents().addAll(stud.findStudentsByDept(c.getDept()));
//                clas.save(copt.get());
//            }
            cdto = new ClassroomDTO(copt.get());
        }
        return cdto;
    }

    @GetMapping("/classroom/all")
    @ResponseBody
    public List<ClassroomDTO> fetch_all_class(){
        List<ClassroomDTO> class_list_id = new ArrayList<>();
        List<Classroom> class_list = new ArrayList<>();
        class_list.addAll(clas.findAll());
        for(Classroom x : class_list){
            class_list_id.add(new ClassroomDTO(x));
        }
        return class_list_id;
    }


    @GetMapping("/classroom/register")
    @ResponseBody
    public List<ClassroomDTO> register_class(){
        List<ClassroomDTO> class_list_id = new ArrayList<>();
        List<Classroom> class_list = new ArrayList<>();
        class_list.addAll(clas.findAll());
        for(Classroom x : class_list){
            class_list_id.add(new ClassroomDTO(x));
        }
        return class_list_id;
    }
}
