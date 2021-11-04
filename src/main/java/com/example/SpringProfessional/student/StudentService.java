package com.example.SpringProfessional.student;

import com.example.SpringProfessional.student.exceptions.BadRequestException;
import com.example.SpringProfessional.student.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        //check if email is taken
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if(existsEmail){
            throw new BadRequestException("Email " + student.getEmail() +  " taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        //Check if student exist
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("The student with id " + studentId + " doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }
}
