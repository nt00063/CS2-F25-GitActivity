package edu.westga.cs1302.project3.model;

/**
 * Represents a single comic book with a title and issue number.
 * 
 * @author CS
 * @version Fall 2025
 */
public class Comic {

    private final String title;
    private final int issueNumber;

    /**
     * Creates a new Comic with the given title and issue number.
     * 
     * @param title       the title of the comic
     * @param issueNumber the issue number of the comic
     * 
     * @precondition title != null && !title.isBlank() && issueNumber > 0
     * @postcondition getTitle().equals(title.trim())
     *                && getIssueNumber() == issueNumber
     */
    public Comic(String title, int issueNumber) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be positive");
        }
        this.title = title.trim();
        this.issueNumber = issueNumber;
    }

    /**
     * Gets the title of this comic.
     * 
     * @return the title of this comic
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the issue number of this comic.
     * 
     * @return the issue number of this comic
     */
    public int getIssueNumber() {
        return this.issueNumber;
    }

    @Override
    public String toString() {
        return this.title + " (Issue #" + this.issueNumber + ")";
    }

    @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.issueNumber;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Comic)) {
            return false;
        }
        Comic otherComic = (Comic) other;
        return this.issueNumber == otherComic.issueNumber
                && this.title.equals(otherComic.title);
    }
}
