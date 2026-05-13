package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentService service) {
        return args -> {
            service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
            service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);
            System.out.println("=== All students after create ===");
            service.printAll();

            // Delete student with ID = 1
            System.out.println("\n=== Deleting student with ID = 1 ===");
            service.deleteStudent(1L);

            System.out.println("\n=== All students after delete ===");
            service.printAll();
        };
    }
}
