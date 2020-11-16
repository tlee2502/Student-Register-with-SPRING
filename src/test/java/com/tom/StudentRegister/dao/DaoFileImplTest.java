package com.sg.studentregister.dao;

import com.sg.studentregister.dto.Student;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaoFileImplTest {
    
    Dao testDao;
    
    public DaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testSystem.txt";
        new FileWriter(testFile);
        testDao = new DaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddStudent() throws Exception {
        String studentId = "0001";
        Student student = new Student(studentId);
        student.setFirstName("John");
        student.setLastName("Appleseed");
        student.setCohort("Cohort1");
        testDao.addStudent(studentId, student);
        Student retrievedStudent = testDao.getStudent(studentId);

        assertEquals(student.getStudentId(),
                retrievedStudent.getStudentId(),
                "Checking student id.");
        assertEquals(student.getFirstName(),
                retrievedStudent.getFirstName(),
                "Checking student first name.");
        assertEquals(student.getLastName(), 
                retrievedStudent.getLastName(),
                "Checking student last name.");
        assertEquals(student.getCohort(), 
                retrievedStudent.getCohort(),
                "Checking student cohort.");
    }
    
    @Test
    public void testAddGetAllStudents() throws Exception {
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("John");
        firstStudent.setLastName("Appleseed");
        firstStudent.setCohort("Cohort1");
        
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Fred");
        secondStudent.setLastName("Pardy");
        secondStudent.setCohort("Cohort2");
        
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        
        Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());
        assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");
        
        List<Student> allStudents = testDao.getAllStudents();
        assertNotNull( allStudents, "All students list should be not null.");
        assertEquals( 1, allStudents.size(), "All students should only have 1 student.");
        
        assertFalse( allStudents.contains(firstStudent), "All students should NOT include Ada.");
        assertTrue( allStudents.contains(secondStudent), "All students should NOT include Charles."); 
        
        removedStudent = testDao.removeStudent(secondStudent.getStudentId());
        assertEquals( removedStudent, secondStudent, "The removed student should be Charles.");

        allStudents = testDao.getAllStudents();
        assertTrue( allStudents.isEmpty(), "The retrieved list of students should be empty.");
        
        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
        assertNull(retrievedStudent, "Ada was removed, should be null.");

        retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
        assertNull(retrievedStudent, "Charles was removed, should be null.");
    }
    
}