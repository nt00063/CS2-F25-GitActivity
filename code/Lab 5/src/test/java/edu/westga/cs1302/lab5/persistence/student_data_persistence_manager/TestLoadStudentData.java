package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;

class TestLoadStudentData {

    @Test
    void testLoadReadsCSVCorrectly() throws IOException {
        try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
            writer.write("Alice,92\n");
            writer.write("Bob,87\n");
        }

        Student[] students = StudentDataPersistenceManager.loadStudentData();
        assertEquals(2, students.length);

        assertEquals("Alice", students[0].getName());
        assertEquals(92, students[0].getGrade());

        assertEquals("Bob", students[1].getName());
        assertEquals(87, students[1].getGrade());
    }

    @Test
    void testLoadThrowsForInvalidCSV() throws IOException {
        try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
            writer.write("Alice-92\n");
        }

        assertThrows(IOException.class, () -> {
            StudentDataPersistenceManager.loadStudentData();
        });
    }
}
