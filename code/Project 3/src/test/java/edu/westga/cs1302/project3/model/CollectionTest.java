package edu.westga.cs1302.project3.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CollectionTest {

    @Test
    void constructorShouldStoreTrimmedNameAndInitializeEmptyComics() {
        Collection collection = new Collection("  Marvel  ");
        assertEquals("Marvel", collection.getName());

        List<Comic> comics = collection.getComics();
        assertTrue(comics.isEmpty());
    }

    @Test
    void constructorShouldNotAllowNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Collection(null);
        });
    }

    @Test
    void constructorShouldNotAllowBlankName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Collection("   ");
        });
    }

    @Test
    void toStringReturnsName() {
        Collection collection = new Collection("DC");
        assertEquals("DC", collection.toString());
    }

    @Test
    void equalsAndHashCodeShouldDependOnName() {
        Collection first = new Collection("Vertigo");
        Collection second = new Collection("Vertigo");
        Collection different = new Collection("Dark Horse");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, different);
    }

    @Test
    void addComicShouldAddComicToCollection() {
        Collection collection = new Collection("Marvel");
        Comic comic = new Comic("Spider-Man", 1);

        collection.addComic(comic);

        assertEquals(1, collection.getComics().size());
        assertEquals(comic, collection.getComics().get(0));
    }

    @Test
    void addComicShouldNotAllowNullComic() {
        Collection collection = new Collection("Marvel");

        assertThrows(IllegalArgumentException.class, () -> collection.addComic(null));
    }

    @Test
    void removeComicShouldRemoveComicIfPresent() {
        Collection collection = new Collection("Marvel");
        Comic comic1 = new Comic("Spider-Man", 1);
        Comic comic2 = new Comic("X-Men", 2);

        collection.addComic(comic1);
        collection.addComic(comic2);

        collection.removeComic(comic1);

        assertEquals(1, collection.getComics().size());
        assertEquals(comic2, collection.getComics().get(0));
    }

    @Test
    void removeComicShouldDoNothingIfComicNotPresent() {
        Collection collection = new Collection("Marvel");
        Comic comic1 = new Comic("Spider-Man", 1);
        Comic comic2 = new Comic("X-Men", 2);

        collection.addComic(comic1);

        collection.removeComic(comic2); // not present

        assertEquals(1, collection.getComics().size());
        assertEquals(comic1, collection.getComics().get(0));
    }
}
