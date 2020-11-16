package com.tom.studentregister.controller;

import com.tom.studentregister.dao.Dao;
import com.tom.studentregister.dao.PersistenceException;
import com.tom.studentregister.dao.DaoFileImpl;
import com.tom.studentregister.dto.Student;
import com.tom.studentregister.service.DataValidationException;
import com.tom.studentregister.service.DuplicateIdException;
import com.tom.studentregister.service.ServiceLayer;
import com.tom.studentregister.ui.View;
import java.util.List;

public class Controller {

    private View view;
    private ServiceLayer service;

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            while (keepGoing) {

                menuSelection = getMenuSelection(); // method defined below

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (PersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            
        }
    }
    
    

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws PersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DuplicateIdException | DataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

        
    private void listStudents() throws PersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws PersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws PersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
