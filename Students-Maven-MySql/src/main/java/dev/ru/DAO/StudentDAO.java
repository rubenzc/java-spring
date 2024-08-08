package dev.ru.DAO;

import dev.ru.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static dev.ru.connection.ConnectionDB.getConnection;

public class StudentDAO {

    public List<Student> listStudents(){
        List<Student> students = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();

        String sql = "SELECT * FROM student ORDER BY id_student ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var student = new Student();
                student.setIdStudent(rs.getInt("id_student"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setPhone(rs.getString("phone"));
                student.setMail(rs.getString("mail"));
                students.add(student);
            }
        } catch (Exception e){
            System.out.println("An error occurred while selecting data: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("An error occurred while closing connection: " + e.getMessage());
            }
        }
        return students;
    }

    public boolean searchStudentByID(Student student) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM student WHERE id_student =?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();
            if(rs.next()){
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setMail(rs.getString("mail"));
                student.setPhone(rs.getString("phone"));
                return true;
            }
        } catch (Exception e){
            System.out.println("An error occurred when searching student: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("An error occurred while closing connection: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean addStudent(Student student){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO student(name, surname, mail, phone)" +
                "VALUES(?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getMail());
            ps.setString(4, student.getPhone());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error adding student: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("An error occurred while closing connection: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updatedStudent(Student student){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE student SET name=?, surname=?, mail=?, phone=?" +
                "WHERE id_student=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getMail());
            ps.setString(4, student.getPhone());
            ps.setInt(5, student.getIdStudent());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("An error occurred updating student: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("An error occurred while closing connection: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean deleteStudent(Student student){

        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE from student WHERE id_student = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error deleting student: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("An error occurred while closing connection: " + e.getMessage());
            }
        }
        return false;
    }


    public static void main(String[] args) {
        var DAOstudent = new StudentDAO();

        //Add student
        /*var newStudent =
                new Student("Harvey", "Specter", "harvey@specter.com", "677887777");
        var addedStudent = DAOstudent.addStudent(newStudent);
        if(addedStudent){
            System.out.println("Added student: " + addedStudent);
        } else {
            System.out.println("Not added student: " + addedStudent);
        }*/

        //Update student
        var updateStudent =
                new Student(4, "Harvey", "Specter", "677887777", "harvey@specter.com");
        var updated = DAOstudent.updatedStudent(updateStudent);
        if(updated){
            System.out.println("Updated student: " + updated);
        } else {
            System.out.println("Not updated student: " + updated);
        }

        //Delete student
        /*var deleteStudent = new Student(3);
        var deleted = DAOstudent.deleteStudent(deleteStudent);
        if (deleted){
            System.out.println("Deleted student: " + deleted);
        } else {
            System.out.println("Not deleted student: " + deleted);
        }*/

        //List students
        System.out.println("Students list: ");
        List<Student> students = DAOstudent.listStudents();
        students.forEach(System.out::println);

        //Search student by ID
        var student1 = new Student(1);
        System.out.println("Student before search: " + student1);
        System.out.println("Search student by ID: ");
        var found = DAOstudent.searchStudentByID(student1);
        if(found){
            System.out.println("Found student: " + student1);
        } else {
            System.out.println("Not found student: " + student1.getIdStudent());
        }
    }

}
