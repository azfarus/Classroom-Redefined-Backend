package com.example.classroom.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance , AttendanceID> {
    long countAttendanceByClassroomid(Long ClassroomID);
    long countAttendanceByClassroomidAndAndStudentid(Long ClassroomID , Long StudentID);
    long countAttendanceByClassroomidAndStudentidAndIsPresent(Long ClassroomID , Long StudentID ,Boolean isPresent);
}
