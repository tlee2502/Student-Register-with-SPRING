package com.sg.studentregister.service;

import com.sg.studentregister.dao.PersistenceException;
import com.sg.studentregister.dto.Student;
import java.util.List;

public interface ServiceLayer {
 
    void createStudent(Student student) throws 
            DuplicateIdException,
            DataValidationException,
            PersistenceException;
 
    List<Student> getAllStudents() throws 
            PersistenceException;
 
    Student getStudent(String studentId) throws 
            PersistenceException;
 
    Student removeStudent(String studentId) throws 
            PersistenceException;
 
}