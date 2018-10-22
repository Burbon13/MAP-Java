package service;

import domain.Student;
import repository.HomeworkRepository;
import repository.StudentRepository;

import java.util.Collection;

public class Service {
    private HomeworkRepository homeworkRepository;
    private StudentRepository studentRepository;

    public Service(HomeworkRepository homeworkRepository, StudentRepository studentRepository) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
    }

    public boolean addStudent(int studentID, String name, int group, String email, String labTeacher) {
        return studentRepository.save(new Student(studentID, name, group, email, labTeacher))  == null;
    }

    public boolean updateStudent(int studentID, String name, int group, String email, String labTeacher) {
        return studentRepository.update(new Student(studentID, name, group, email, labTeacher)) == null;
    }

    public boolean deleteStudent(int studentID) {
        return studentRepository.delete(studentID) != null;
    }

    public Student getStudent(int studentID ) {
        return studentRepository.findOne(studentID);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
