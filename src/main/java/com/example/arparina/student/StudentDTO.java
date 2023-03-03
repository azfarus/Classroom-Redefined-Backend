package com.example.arparina.student;


import com.example.arparina.classroom.Classroom;
import lombok.Data;

import java.util.ArrayList;


@Data
public class StudentDTO  {



    private  Long id;
    private float cgpa;

    private ArrayList<Long> classroom_id = new ArrayList<>();

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public float getCgpa() {
        return cgpa;
    }

    public StudentDTO(Student s) {

        this.id = s.getId();
        this.cgpa = s.getCgpa();

        System.out.println(s.getClassrooms().isEmpty());
        for (Classroom c: s.getClassrooms()) {
            classroom_id.add(c.getId());
            System.out.println(c.getId());
        }

    }

    public StudentDTO() {
        this.id =  (long)0;
        this.cgpa = 0;
        classroom_id = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }








}
