package com.sg.studentregister.service;

import com.sg.studentregister.dao.AuditDao;
import com.sg.studentregister.dao.Dao;
import com.sg.studentregister.dao.PersistenceException;
import com.sg.studentregister.dto.Student;
import java.util.List;

public class ServiceLayerImpl implements ServiceLayer {

    private Dao dao;
    private AuditDao auditDao;

    public ServiceLayerImpl(Dao dao, AuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws DuplicateIdException, DataValidationException, PersistenceException {
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new DuplicateIdException("Error: Could not create new Student."
                    + " Student Id" + student.getStudentId() + " already exists");
        }
        validateStudentData(student);
        dao.addStudent(student.getStudentId(), student);
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " created.");
    }

    @Override
    public List<Student> getAllStudents() throws PersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws PersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws PersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        auditDao.writeAuditEntry("Student " + studentId + " removed.");
        return removedStudent;
    }

    private void validateStudentData(Student student) throws DataValidationException {

        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {

            throw new studentregisterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
}