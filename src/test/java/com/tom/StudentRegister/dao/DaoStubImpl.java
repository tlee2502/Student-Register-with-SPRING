package com.sg.studentregister.dao;

import com.sg.studentregister.dto.Student;
import java.util.ArrayList;
import java.util.List;

public class DaoStubImpl implements Dao {
    
    public Student onlyStudent;
    
    public DaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("John");
        onlyStudent.setLastName("Appleseed");
        onlyStudent.setCohort("Cohort1");
    }
    
    public DaoStubImpl(Student testStudent) {
        this.onlyStudent = testStudent;
    }

    @Override
    public Student addStudent(String studentId, Student student) throws PersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() throws PersistenceException {
        List<Student> studentList = new ArrayList<>();
        studentList.add(onlyStudent);
        return studentList;
    }

    @Override
    public Student getStudent(String studentId) throws PersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        } 
    }

    @Override
    public Student removeStudent(String studentId) throws PersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }
    
}