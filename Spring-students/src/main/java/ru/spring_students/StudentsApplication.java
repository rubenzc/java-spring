package ru.spring_students;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.spring_students.model.Student;
import ru.spring_students.service.StudentService;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentsApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Initializing app...");
		SpringApplication.run(StudentsApplication.class, args);
		logger.info("App finished!!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Executing Spring run method" + nl);

		var exit = false;
		var console = new Scanner(System.in);

		while(!exit) {
			showMenu();
			exit = executeOptions(console);
			logger.info(nl);
		}

	}

	private void showMenu(){
		logger.info(nl);
		logger.info("""
				***STUDENTS CRUD APP***
				1. List students
				2. Search student
				3. Add student
				4. Update student
				5. Delete student
				6. Exit
				Choose an option:""");
	}

	private boolean executeOptions(Scanner console){

		var option = Integer.parseInt(console.nextLine());
		var exit = false;

		switch (option){

			case 1 -> { //List students
				logger.info("List students");
				List<Student> students = studentService.listStudents();
				students.forEach(student -> logger.info(student.toString()));
			}

			case 2 -> { //Search student
				logger.info("Search student");
				logger.info("Insert ID Student");
				var idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.searchById(idStudent);

				if(student != null) {
					logger.info("Student found: " + student + nl);
				} else {
					logger.info("Student " + idStudent + " not found");
				}
			}

			case 3 -> { //Add student
				logger.info("Add student");
				logger.info("Add student name");
				var name = console.nextLine();
				logger.info("Add student surname");
				var surname = console.nextLine();
				logger.info("Add student mail");
				var mail = console.nextLine();
				logger.info("Add student phone");
				var phone = console.nextLine();

				Student student = new Student();
				student.setName(name);
				student.setSurname(surname);
				student.setMail(mail);
				student.setPhone(phone);
				studentService.keepStudent(student);

				logger.info("Student added");
			}

			case 4 -> {//Updte student

				logger.info("Update student");
				logger.info("Insert ID Student to update");
				var idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.searchById(idStudent);

				logger.info("Add student name");
				var name = console.nextLine();
				logger.info("Add student surname");
				var surname = console.nextLine();
				logger.info("Add student mail");
				var mail = console.nextLine();
				logger.info("Add student phone");
				var phone = console.nextLine();

				student.setName(name);
				student.setSurname(surname);
				student.setMail(mail);
				student.setPhone(phone);
				studentService.keepStudent(student);

				logger.info("Student " + idStudent + " updated");

			}

			case 5 -> { //Delete student

				logger.info("Delete student");
				logger.info("Insert ID Student to delete");
				var idStudent = Integer.parseInt(console.nextLine());

				var deletedStudent = studentService.searchById(idStudent);
				studentService.deleteStudent(deletedStudent);

				logger.info("Student " + idStudent + " deleted");

			}

			case 6 -> {
				logger.info("See u!!" + nl);
				exit = true;
			}

			default -> logger.info("Not recognized option: " + option + nl);

		}

		return exit;

	}

}
