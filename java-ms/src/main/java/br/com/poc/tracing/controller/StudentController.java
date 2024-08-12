package br.com.poc.tracing.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.poc.tracing.domain.dto.StudentDTO;
import br.com.poc.tracing.domain.model.Student;
import br.com.poc.tracing.service.StudentService;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        try {
            var response = this.studentService.findAllStudents();
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@Param("id") Long id) {
        try {
            var response = this.studentService.findStudentById(id);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> postMethodName(@RequestBody StudentDTO dto) {
        try {
            var response = this.studentService.create(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}