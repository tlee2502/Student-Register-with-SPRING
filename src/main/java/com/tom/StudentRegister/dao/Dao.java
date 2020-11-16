package com.sg.studentregister.dao;

import com.sg.studentregister.dto.Student;
import java.util.List;

public interface Dao {
 
    Student addStudent(String studentId, Student student)
            throws PersistenceException;

    List<Student> getAllStudents() throws PersistenceException;

    Student getStudent(String studentId) throws PersistenceException;

    Student removeStudent(String studentId) throws PersistenceException;
    
}