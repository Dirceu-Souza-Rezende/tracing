package br.com.poc.tracing.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import br.com.poc.tracing.domain.dto.StudentDTO;
import br.com.poc.tracing.domain.model.Student;
import br.com.poc.tracing.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student findStudentById(Long id) throws BadRequestException {
        System.out.println("findStudentById " + id);
        var student = this.studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }

        throw new BadRequestException("Student not found");
    }

    public Student create(StudentDTO dto) {
        var student = new Student(dto.name());
        return this.studentRepository.save(student);
    }
}
