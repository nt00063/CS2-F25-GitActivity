package edu.westga.cs1302.project3.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CollectionTest {

    @Test
    void constructorShouldStoreNameExactlyWhenAlreadyTrimmed() {
        Collection collection = new Collection("Marvel");
        assertEquals("Marvel", collection.getName());
    }

    @Test
    void constructorShouldTrimWhitespaceAroundName() {
        Collection collection = new Collection("   DC Comics  ");
        assertEquals("DC Comics", collection.getName());
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
    void toStringShouldReturnName() {
        Collection collection = new Collection("Image");
        assertEquals("Image", collection.toString());
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
}
