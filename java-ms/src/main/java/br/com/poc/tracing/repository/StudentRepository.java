package br.com.poc.tracing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poc.tracing.domain.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
