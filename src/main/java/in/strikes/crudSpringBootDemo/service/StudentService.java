package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * StudentService — business logic layer for student CRUD operations.
 *
 * @Service marks this class as a Spring bean in the service layer.
 * It sits between the controller (handles HTTP requests) and the repository (handles DB queries).
 * Constructor injection ensures the repository dependency is provided by Spring.
 */
@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /** Creates a new student with deleted = false (active record). */
    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }

    /** Fetches a student by ID, or returns null if not found. */
    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findById(id);

        if(studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;
    }

    /** Returns all active students (deleted = false). */
    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;
    }

    /**
     * Updates an existing student's fields.
     * Only updates if the student exists and is not soft-deleted.
     */
    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setDeleted(false);

        return studentRepository.save(studentToSave);
    }

    /** Hard delete: permanently removes the student row from the database. */
    public Boolean deleteStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);

        if(!isStudent) return false;

        studentRepository.deleteById(id);

        return true;
    }

    /** Soft delete: sets deleted = true instead of removing the row. */
    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return false;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }
}
