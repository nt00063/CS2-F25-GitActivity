package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a comic collection.
 * 
 * Each collection has a name and a list of comics.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Collection {

    private final String name;
    private final List<Comic> comics;

    /**
     * Creates a new Collection with the given name.
     * 
     * @param name the name of the collection
     * 
     * @precondition name != null && !name.isBlank()
     * @postcondition getName().equals(name.trim()) && getComics().isEmpty()
     */
    public Collection(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be null or blank");
        }
        this.name = name.trim();
        this.comics = new ArrayList<>();
    }

    /**
     * Gets the name of this collection.
     * 
     * @return the name of this collection
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets an unmodifiable view of the comics in this collection.
     * 
     * @return an unmodifiable list of comics
     */
    public List<Comic> getComics() {
        return Collections.unmodifiableList(this.comics);
    }

    /**
     * Adds the given comic to this collection.
     * 
     * @param comic the comic to add
     * 
     * @throws IllegalArgumentException if comic is null
     * 
     * @precondition comic != null
     * @postcondition getComics().contains(comic)
     */
    public void addComic(Comic comic) {
        if (comic == null) {
            throw new IllegalArgumentException("Comic cannot be null");
        }
        this.comics.add(comic);
    }

    /**
     * Removes the given comic from this collection if it is present.
     * 
     * @param comic the comic to remove
     * 
     * @precondition none
     * @postcondition if comic was in the list, it is removed
     */
    public void removeComic(Comic comic) {
        this.comics.remove(comic);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Collection)) {
            return false;
        }
        Collection otherCollection = (Collection) other;
        return this.name.equals(otherCollection.name);
    }
}
