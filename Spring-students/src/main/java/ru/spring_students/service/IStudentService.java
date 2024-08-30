package ru.spring_students.service;

import ru.spring_students.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> listStudents();

    public Student searchById(Integer idStudent);

    public void keepStudent(Student student);

    public void deleteStudent(Student student);

}
