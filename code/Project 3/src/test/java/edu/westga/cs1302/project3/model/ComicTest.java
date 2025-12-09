package edu.westga.cs1302.project3.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ComicTest {

    @Test
    void constructorShouldStoreTitleAndIssueNumber() {
        Comic comic = new Comic("Spider-Man", 1);

        assertEquals("Spider-Man", comic.getTitle());
        assertEquals(1, comic.getIssueNumber());
    }

    @Test
    void constructorShouldTrimTitle() {
        Comic comic = new Comic("  Batman  ", 10);

        assertEquals("Batman", comic.getTitle());
        assertEquals(10, comic.getIssueNumber());
      }

    @Test
    void constructorShouldNotAllowNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Comic(null, 1));
    }

    @Test
    void constructorShouldNotAllowBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Comic("   ", 1));
    }

    @Test
    void constructorShouldNotAllowNonPositiveIssueNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Comic("X-Men", 0));
        assertThrows(IllegalArgumentException.class, () -> new Comic("X-Men", -5));
    }

    @Test
    void toStringShouldIncludeTitleAndIssue() {
        Comic comic = new Comic("Thor", 3);
        assertEquals("Thor (Issue #3)", comic.toString());
    }

    @Test
    void equalsAndHashCodeShouldDependOnTitleAndIssue() {
        Comic first = new Comic("Flash", 5);
        Comic second = new Comic("Flash", 5);
        Comic differentTitle = new Comic("Green Lantern", 5);
        Comic differentIssue = new Comic("Flash", 6);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, differentTitle);
        assertNotEquals(first, differentIssue);
    }
}
