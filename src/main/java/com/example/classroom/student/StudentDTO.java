package com.example.classroom.student;


import com.example.classroom.classroom.Classroom;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Data
@Getter
@Setter
@NoArgsConstructor
public class StudentDTO  {



    private  Long id;
    private float cgpa;

    private ArrayList<Long> classroom_id = new ArrayList<>();



    public StudentDTO(Student s) {

        this.id = s.getId();
        this.cgpa = s.getCgpa();

        System.out.println(s.getClassrooms().isEmpty());
        for (Classroom c: s.getClassrooms()) {
            classroom_id.add(c.getId());
            System.out.println(c.getId());
        }

    }









}
