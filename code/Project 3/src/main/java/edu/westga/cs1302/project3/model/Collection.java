package edu.westga.cs1302.project3.model;

/**
 * Represents a comic collection.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Collection {

    private final String name;

    /**
     * Creates a new Collection with the given name.
     * 
     * @param name the name of the collection
     * 
     * @precondition name != null && !name.isBlank()
     * @postcondition getName().equals(name.trim())
     */
    public Collection(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be null or blank");
        }
        this.name = name.trim();
    }

    /**
     * Gets the name of this collection.
     * 
     * @return the name of this collection
     */
    public String getName() {
        return this.name;
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
