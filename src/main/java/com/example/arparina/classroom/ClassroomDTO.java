package com.example.arparina.classroom;


import com.example.arparina.student.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClassroomDTO {
    private Long id;
    private String dept;
    private Set<Long> students = new HashSet<>();

    public  ClassroomDTO(Classroom clss){
        this.id = clss.getId();
        this.dept = clss.getDept();

        System.out.println(clss.getStudents().isEmpty());
        for (Student x: clss.getStudents()) {

            students.add(x.getId());

        }
    }
}
