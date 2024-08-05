package dev.ru.DAO;

import connection.ConnectionDB;
import domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static connection.ConnectionDB.getConnection;

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

    //Students list test
    public static void main(String[] args) {
        var DAOstudent = new StudentDAO();
        System.out.println("Students list: ");
        List<Student> students = DAOstudent.listStudents();
        students.forEach(System.out::println);
    }

}
