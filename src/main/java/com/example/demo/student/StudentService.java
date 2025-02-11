package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail= studentRepository.findByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email is already in use");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists=studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("Student not found"));
        if(name!=null && !name.isEmpty()){
            student.setName(name);
        }
        if(email!=null && !email.isEmpty()){
            Optional<Student> studentByEmail = studentRepository.findByEmail(email);
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }

}
