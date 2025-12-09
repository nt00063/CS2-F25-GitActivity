package edu.westga.cs1302.project3.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for the ComicSearch class.
 * 
 * Ensures correct behavior when finding comics across multiple collections
 * and validates input requirements.
 */
public class ComicSearchTest {

    @Test
    void findComicShouldReturnMatchingComic() {
        Collection marvel = new Collection("Marvel");
        Collection dc = new Collection("DC");

        Comic spiderman1 = new Comic("Spider-Man", 1);
        Comic spiderman2 = new Comic("Spider-Man", 2);
        Comic batman1 = new Comic("Batman", 1);

        marvel.addComic(spiderman1);
        marvel.addComic(spiderman2);
        dc.addComic(batman1);

        List<Collection> collections = new ArrayList<>();
        collections.add(marvel);
        collections.add(dc);

        ComicSearch search = new ComicSearch();
        Comic result = search.findComicByTitleAndIssue(collections, "Spider-Man", 2);

        assertEquals(spiderman2, result);
    }

    @Test
    void findComicShouldHandleTitleCaseAndWhitespace() {
        Collection marvel = new Collection("Marvel");
        Comic spiderman = new Comic("Spider-Man", 1);
        marvel.addComic(spiderman);

        List<Collection> collections = new ArrayList<>();
        collections.add(marvel);

        ComicSearch search = new ComicSearch();
        Comic result = search.findComicByTitleAndIssue(collections, "  spider-man  ", 1);

        assertEquals(spiderman, result);
    }

    @Test
    void findComicShouldReturnNullWhenNotFound() {
        Collection marvel = new Collection("Marvel");
        marvel.addComic(new Comic("Spider-Man", 1));

        List<Collection> collections = new ArrayList<>();
        collections.add(marvel);

        ComicSearch search = new ComicSearch();
        Comic result = search.findComicByTitleAndIssue(collections, "Batman", 1);

        assertNull(result);
    }

    @Test
    void findComicShouldThrowWhenCollectionsNull() {
        ComicSearch search = new ComicSearch();
        assertThrows(IllegalArgumentException.class, () ->
            search.findComicByTitleAndIssue(null, "Spider-Man", 1)
        );
    }

    @Test
    void findComicShouldThrowWhenTitleInvalid() {
        Collection marvel = new Collection("Marvel");

        List<Collection> collections = new ArrayList<>();
        collections.add(marvel);

        ComicSearch search = new ComicSearch();

        assertThrows(IllegalArgumentException.class, () ->
            search.findComicByTitleAndIssue(collections, null, 1)
        );

        assertThrows(IllegalArgumentException.class, () ->
            search.findComicByTitleAndIssue(collections, "   ", 1)
        );
    }

    @Test
    void findComicShouldThrowWhenIssueNonPositive() {
        Collection marvel = new Collection("Marvel");
        List<Collection> collections = new ArrayList<>();
        collections.add(marvel);

        ComicSearch search = new ComicSearch();

        assertThrows(IllegalArgumentException.class, () ->
            search.findComicByTitleAndIssue(collections, "Spider-Man", 0)
        );

        assertThrows(IllegalArgumentException.class, () ->
            search.findComicByTitleAndIssue(collections, "Spider-Man", -2)
        );
    }
}
