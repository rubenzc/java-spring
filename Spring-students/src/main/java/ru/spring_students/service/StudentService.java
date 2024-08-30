package ru.spring_students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring_students.model.Student;
import ru.spring_students.repository.IStudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired

    private IStudentRepository studentRepository;

    @Override
    public List<Student> listStudents(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @Override
    public Student searchById(Integer idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        return student;
    }

    @Override
    public void keepStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }





}
