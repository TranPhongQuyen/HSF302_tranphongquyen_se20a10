package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceDeleteTest {

    @Autowired
    private StudentService studentService;

    @Test
    void testDeleteStudent_Success() {
        // 1. Tạo student mới
        studentService.createStudent("Test User", "test@fpt.edu.vn", 22);

        // 2. Tìm student vừa tạo (ID = 1)
        Student student = studentService.findById(1L);
        assertNotNull(student, "Student phải tồn tại trước khi xóa");
        assertEquals("Test User", student.getFullName());

        // 3. Xóa student
        boolean result = studentService.deleteStudent(1L);
        assertTrue(result, "deleteStudent phải trả về true khi xóa thành công");

        // 4. Kiểm tra student đã bị xóa
        Student deleted = studentService.findById(1L);
        assertNull(deleted, "Student phải là null sau khi xóa");
    }

    @Test
    void testDeleteStudent_NotFound() {
        // Xóa student với ID không tồn tại
        boolean result = studentService.deleteStudent(999L);
        assertFalse(result, "deleteStudent phải trả về false khi không tìm thấy student");
    }
}
