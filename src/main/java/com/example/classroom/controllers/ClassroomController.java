package com.example.classroom.controllers;


import com.example.classroom.assignment.Assignment;
import com.example.classroom.assignment.AssignmentDTO;
import com.example.classroom.assignment.AssignmentRepository;
import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.file.File;
import com.example.classroom.file.FileRepository;
import com.example.classroom.post.Post;
import com.example.classroom.post.PostDTO;
import com.example.classroom.post.PostRepository;
import com.example.classroom.submission.Submission;
import com.example.classroom.submission.SubmissionDTO;
import com.example.classroom.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomRepository classes;

    @Autowired
    private PostRepository posts;

    @Autowired
    private SubmissionRepository submits;

    @Autowired
    private AssignmentRepository assRepo;

    @Autowired
    private FileRepository fileSaves;


    @Autowired
    private FileRepository allfiles;
    @PostMapping("/createpost")
    @ResponseBody
    public  String create_post(@RequestBody PostDTO pdto){
        Post post = new Post();

        post.setTime(pdto.getTime());;
        post.setPosted_by(pdto.getPosted_by());
        post.setLink(pdto.getLink());
        post.setText(pdto.getText());
        post.setClassroom(classes.findById(pdto.getClassroom_id()).get());


        posts.save(post);
        return "OK";

    }

    @GetMapping("/getpost/{id}")
    @ResponseBody
    public  PostDTO get_post(@PathVariable Long id){
        PostDTO p = new PostDTO(posts.findById(id).get());

        return p;
    }


    @GetMapping("/getsubmission/{id}")
    @ResponseBody
    public  SubmissionDTO get_submit(@PathVariable Long id){
        SubmissionDTO p = new SubmissionDTO(submits.findById(id).get());

        return p;
    }


    @GetMapping("/getassignment/{id}")
    @ResponseBody
    public AssignmentDTO get_assignment(@PathVariable Long id){
        AssignmentDTO p = new AssignmentDTO(assRepo.findById(id).get());

        return p;
    }
    @PostMapping("/createsubmission")
    @ResponseBody
    public  String create_sub(@RequestBody SubmissionDTO sdto){
        Submission sub = new Submission();


        sub.setId(sdto.getId());
        sub.setInformation(sdto.getInformation());
        sub.setSubmittedOn(sdto.getSubmittedOn());
        sub.setAddedFiles(new ArrayList<>()) ;
        for(Long x : sdto.getAddedFiles() ){
            sub.getAddedFiles().add(allfiles.findById(x).get());
        }
        submits.save(sub);
        return "OK";

    }


    @PostMapping("/createassignment")
    @ResponseBody
    public  String create_ass(@RequestBody AssignmentDTO sdto){
        Assignment sub = new Assignment();


        sub.setId(sdto.getId());
        sub.setClassroom(classes.findById(sdto.getClassroomid()).get());
        sub.setMarks(sdto.getMarks());
        sub.setDeadline(sdto.getDeadline());
        sub.setInstruction(sdto.getInstruction());
        sub.setTitle(sdto.getTitle());

        for(Long x : sdto.getNeededFilesID()){
            sub.getNeededFiles().add(fileSaves.findById(x).get());
        }

        for(Long x : sdto.getSubmissionsOfThisID()){
            sub.getSubmissionsOfThis().add(submits.findById(x).get());
        }
        assRepo.save(sub);
        return "OK";

    }
}
