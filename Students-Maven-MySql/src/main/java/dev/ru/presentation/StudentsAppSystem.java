package dev.ru.presentation;

import dev.ru.DAO.StudentDAO;
import dev.ru.domain.Student;

import java.util.Scanner;

public class StudentsAppSystem {
    public static void main(String[] args) {
        var exit = false;
        var console = new Scanner(System.in);

        //Instance to service class (StudentDao)
        var DAOstudent = new StudentDAO();
        while(!exit){

            try{
            showMenu();
            exit = executeOptions(console, DAOstudent);
            } catch(Exception e) {
                System.out.println("An error occurred exceuting operation: "
                        + e.getMessage());
            }
            System.out.println();
        }//end while
    }

    private static void showMenu(){
        System.out.print("""
                    *** Students system ***
                    1. List students
                    2. Search student
                    3. Add student
                    4. Update student
                    5. Delete student
                    6. Exit
                    Choose an option:
                    """);
    }

    private static boolean executeOptions(Scanner console, StudentDAO DAOstudent){
        var option = Integer.parseInt(console.nextLine());
        var exit = false;
        switch (option){
            case 1 -> { //List students
                System.out.println("Students list...");
                var students = DAOstudent.listStudents();
                students.forEach(System.out::println);
            }
            case 2 -> { //Search by ID
                System.out.println("Search student by ID...");
                System.out.println("Insert ID student to search: ");
                var idToSearch = Integer.parseInt(console.nextLine());
                var studentSearched = new Student(idToSearch);
                var foundedStudent = DAOstudent.searchStudentByID(studentSearched);
                if (foundedStudent) {
                    System.out.println("Student found: " + studentSearched);
                } else {
                    System.out.println("Student not found: " + idToSearch);
                }
            }
            case 3 ->{ //Add student
                System.out.println("Add student: ");
                System.out.print("Name: ");
                var name = console.nextLine();
                System.out.print("Surname: ");
                var surname = console.nextLine();
                System.out.print("Mail: ");
                var mail = console.nextLine();
                System.out.print("Phone: ");
                var phone = console.nextLine();
                //Create student object without ID
                var studentAdded = new Student(name, surname, mail, phone);
                var added = DAOstudent.addStudent(studentAdded);
                if(added) {
                    System.out.println("Student added successfully: " + added);
                } else {
                    System.out.println("Student not added: " + added);
                }
            }
            case 4 -> { //Update student
                System.out.println("Update student: ");
                System.out.println("ID student");
                var idStudent = Integer.parseInt(console.nextLine());
                System.out.print("Name: ");
                var name = console.nextLine();
                System.out.print("Surname: ");
                var surname = console.nextLine();
                System.out.print("Mail: ");
                var mail = console.nextLine();
                System.out.print("Phone: ");
                var phone = console.nextLine();
                var studentUpdated = new Student(idStudent, name, surname, mail, phone);
                var updated = DAOstudent.updatedStudent(studentUpdated);
                if(updated){
                    System.out.println("Student updated: " + updated);
                } else {
                    System.out.println("Student not updated: " + updated);
                }
            }
            case 5 -> { //Delete student
                System.out.println("Delete student... ");
                System.out.println("ID student");
                var idStudent = Integer.parseInt(console.nextLine());
                var student = new Student(idStudent);
                var deleted = DAOstudent.deleteStudent(student);
                if(deleted) {
                    System.out.println("Student deleted: " + deleted);
                } else {
                    System.out.println("Student not deleted: " + deleted);
                }
            }
            case 6 -> { //Exit
                System.out.println("See you!!");
                exit = true;
            }
            default -> {
                System.out.println("Unrecognized option");
            }
        }
        return exit;
    }


}
