package com.example.classroom.controllers;


import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.post.Post;
import com.example.classroom.post.PostDTO;
import com.example.classroom.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomRepository classes;

    @Autowired
    private PostRepository posts;
    @PostMapping("/createpost")
    public  void create_post(@RequestBody PostDTO pdto){
        Post post = new Post();

        post.setTime(pdto.getTime());;
        post.setPosted_by(pdto.getPosted_by());
        post.setLink(pdto.getLink());
        post.setText(pdto.getText());
        post.setClassroom(classes.findById(pdto.getClassroom_id()).get());


        posts.save(post);

    }
}
