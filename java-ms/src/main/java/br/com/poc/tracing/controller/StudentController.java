package br.com.poc.tracing.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.poc.tracing.domain.dto.StudentDTO;
import br.com.poc.tracing.domain.model.Student;
import br.com.poc.tracing.service.StudentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/student")
@Log4j2
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
    public ResponseEntity findStudent(@PathVariable("id") Long id) {
        try {
            var response = this.studentService.findStudentById(id);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            log.error("Error de BadRequest" + e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error de Exception" + e.getMessage());
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