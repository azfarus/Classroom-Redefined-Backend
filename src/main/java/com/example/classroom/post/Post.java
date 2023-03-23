package com.example.classroom.post;


import com.example.classroom.classroom.Classroom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private Timestamp time ;

    private String posted_by;

    private String link;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Classroom classroom;


}
