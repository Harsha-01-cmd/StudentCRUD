package in.strikes.crudSpringBootDemo.controller;

import in.strikes.crudSpringBootDemo.entity.Student;
import in.strikes.crudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

/**
 * StudentController — REST API endpoints for student CRUD operations.
 *
 * @RestController combines @Controller + @ResponseBody: each method returns
 * an object that Spring automatically serializes to JSON.
 * @RequestMapping sets the base path for all endpoints in this class.
 *
 * The controller receives HTTP requests, delegates to the service layer for
 * business logic, and wraps the result in ResponseEntity for HTTP status codes.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /** POST /api/students/create — creates a new student record. */
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student createdStudent = studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    /** GET /api/students/get?id=1 — fetches a single student by ID. */
    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id) {
            Student studentResp = studentService.getStudent(id);

            if(studentResp == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(studentResp);
    }

    /** GET /api/students/getAll — returns all active students. */
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();

        if(studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }

    /** PUT /api/students/update?id=1 — updates an existing student's fields. */
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,
                                                 @RequestBody Student studentReq) {
        Student studentResp = studentService.updateStudent(id, studentReq);

        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    /** DELETE /api/students/delete?id=1 — permanently removes a student. */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }

    /** PATCH /api/students/delete-soft?id=1 — soft deletes a student (sets deleted = true). */
    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }
}
