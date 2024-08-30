package ru.spring_students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring_students.model.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
}
