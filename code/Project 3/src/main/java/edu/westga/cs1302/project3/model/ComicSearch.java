package edu.westga.cs1302.project3.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides functionality to search for a comic by title and issue number
 * across multiple collections using a Map-based index.
 * 
 * A lookup is performed by normalizing the comic's title and combining it
 * with its issue number to form a unique key.
 * 
 * @author Noah Toups
 * @version Fall 2025
 */
public class ComicSearch {

    /**
     * Searches for a comic with the given title and issue number across all
     * provided collections.
     * 
     * @param collections  the collections to search
     * @param title        the title of the comic to find
     * @param issueNumber  the issue number of the comic to find
     * 
     * @return the matching Comic if found, or null otherwise
     * 
     * @precondition collections != null
     * @precondition title != null && !title.isBlank()
     * @precondition issueNumber > 0
     */
    public Comic findComicByTitleAndIssue(Iterable<Collection> collections, String title, int issueNumber) {
        if (collections == null) {
            throw new IllegalArgumentException("Collections cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be positive");
        }

        String normalizedTitle = title.trim().toLowerCase();
        Map<String, Comic> index = new HashMap<>();

        for (Collection collection : collections) {
            if (collection == null) {
                continue;
            }
            for (Comic comic : collection.getComics()) {
                if (comic == null) {
                    continue;
                }

                String key = comic.getTitle().trim().toLowerCase() + "#" + comic.getIssueNumber();
                index.put(key, comic);
            }
        }

        String searchKey = normalizedTitle + "#" + issueNumber;
        return index.get(searchKey);
    }
}
