package com.example.classroom.controllers;


import com.example.classroom.assignment.Assignment;
import com.example.classroom.assignment.AssignmentDTO;
import com.example.classroom.assignment.AssignmentRepository;
import com.example.classroom.attendance.Attendance;
import com.example.classroom.attendance.AttendanceID;
import com.example.classroom.attendance.AttendanceRepository;
import com.example.classroom.classroom.Classroom;
import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.email.EmailSender;
import com.example.classroom.file.File;
import com.example.classroom.file.FileRepository;
import com.example.classroom.post.Post;
import com.example.classroom.post.PostDTO;
import com.example.classroom.post.PostRepository;
import com.example.classroom.student.Student;
import com.example.classroom.student.StudentRepository;
import com.example.classroom.submission.Submission;
import com.example.classroom.submission.SubmissionDTO;
import com.example.classroom.submission.SubmissionRepository;
import com.example.classroom.teacher.Teacher;
import com.example.classroom.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    StudentRepository stud;

    @Autowired
    TeacherRepository teaches;
    EmailSender es ;
    @Autowired
    private FileRepository allfiles;

    @Autowired
    private AttendanceRepository presents;
    @PostMapping("/createpost")
    @ResponseBody
    public  String create_post(@RequestBody PostDTO pdto){


        Post post = new Post();

        post.setTime(pdto.getTime());;
        post.setPosted_by(pdto.getPosted_by());
        post.setLink(pdto.getLink());
        post.setText(pdto.getText());
        Classroom c = classes.findById(pdto.getClassroom_id()).get();


        post.setClassroom(c);
        posts.save(post);
        Set<Classroom> classroomSet = new HashSet<>();
        classroomSet.add(c);
        List<Student> mail_students= stud.findByClassroomsIn(classroomSet);

        String text = "You have a new post from " + post.getPosted_by() +" in your classroom of " + c.getCoursename() +".";


        es = new EmailSender(mail_students , text , "New Post" );
        Thread thread = new Thread(es);
        thread.start();




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
    public  SubmissionDTO get_submit(@PathVariable String id){
        SubmissionDTO p = new SubmissionDTO(submits.findById(id).get());

        return p;
    }


    @GetMapping("/getassignment/{id}")
    @ResponseBody
    public AssignmentDTO get_assignment(@PathVariable Long id){
        AssignmentDTO p = new AssignmentDTO(assRepo.findById(id).get());

        return p;
    }


    @GetMapping("/stat/allclasses/{classid}/{studid}")
    @ResponseBody
    public Float attendance_allstat(@PathVariable Long classid , @PathVariable Long studid){

            Float val = (float) presents.countAttendanceByClassroomidAndStudentid(classid , studid);

            return val;

    }
    @GetMapping("/stat/presentclasses/{classid}/{studid}")
    @ResponseBody
    public Float attendance_presentstat(@PathVariable Long classid , @PathVariable Long studid){

        Float val = (float) presents.countAttendanceByClassroomidAndStudentidAndIsPresent(classid , studid , true);

        return val;

    }
    @PostMapping("/createsubmission")
    @ResponseBody
    public  String create_sub(@RequestBody SubmissionDTO sdto){
        Submission sub = new Submission();

        sub.setAssignment(assRepo.findById(sdto.getAssignmentId()).get());
        sub.setSubmittedBy(sdto.getSubmittedBy());
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

    @PostMapping("/createattendance")
    @ResponseBody
    public  String create_att(@RequestBody Attendance adto){

        presents.save(adto);
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
        sub.setNeededFiles(new ArrayList<>());
        sub.setSubmissionsOfThis(new ArrayList<>());
        for(Long x : sdto.getNeededFilesID()){
            sub.getNeededFiles().add(fileSaves.findById(x).get());
        }

        for(String x : sdto.getSubmissionsOfThisID()){
            sub.getSubmissionsOfThis().add(submits.findById(x).get());
        }
        assRepo.save(sub);
        return "OK";

    }
}
