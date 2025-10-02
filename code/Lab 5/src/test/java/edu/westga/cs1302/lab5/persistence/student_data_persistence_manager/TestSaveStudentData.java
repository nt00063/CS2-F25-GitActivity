package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

class TestSaveStudentData {

    @Test
    void testSaveWritesCorrectCSVFormat() throws IOException {
        Student[] students = {
            new Student("Alice", 92),
            new Student("Bob", 87)
        };

        StudentDataPersistenceManager.saveStudentData(students);
        var lines = Files.readAllLines(Paths.get(StudentDataPersistenceManager.FILE_LOCATION));

        assertEquals(2, lines.size());
        assertEquals("Alice,92", lines.get(0));
        assertEquals("Bob,87", lines.get(1));
    }

    @Test
    void testSaveThrowsForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            StudentDataPersistenceManager.saveStudentData(null);
        });
    }
}
