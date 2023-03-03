package com.example.arparina.student;


import com.example.arparina.classroom.Classroom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Student  {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  Long id;
    private float cgpa;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "students")
    private Set<Classroom> classrooms = new HashSet<>();

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public float getCgpa() {
        return cgpa;
    }

    public Long getId() {
        return id;
    }
    public Set<Classroom> getClassrooms() {
        return classrooms;
    }


}
