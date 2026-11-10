package in.strikes.crudSpringBootDemo.repository;

import in.strikes.crudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * StudentRepository — data access layer for Student entities.
 *
 * Extending JpaRepository gives us save(), findById(), deleteById(), etc. for free.
 * Spring creates a proxy implementation at runtime — no need to write the class body.
 *
 * Custom methods use Spring Data's method name query derivation:
 * Spring reads the method name and generates the SQL WHERE clause automatically.
 */
//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Finds a student by ID only if deleted = false (excludes soft-deleted records).
    // Generated SQL: SELECT * FROM student WHERE id = ? AND deleted = false
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    // Finds all students where deleted = false.
    // Generated SQL: SELECT * FROM student WHERE deleted = false
    List<Student> findByDeletedIsFalse();

}

