package edu.westga.cs1302.project3.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Collection;
import edu.westga.cs1302.project3.model.Comic;

/**
 * Tests for the MainWindowViewModel class.
 */
public class MainWindowViewModelTest {

    @Test
    void constructorShouldInitializeEmptyState() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        assertEquals("", viewModel.newCollectionNameProperty().get());
        assertTrue(viewModel.getCollections().isEmpty());
        assertEquals(null, viewModel.selectedCollectionProperty().get());
        assertTrue(viewModel.getComicsForSelectedCollection().isEmpty());
        assertEquals(null, viewModel.selectedComicProperty().get());
        assertEquals("", viewModel.searchTitleProperty().get());
        assertEquals("", viewModel.searchIssueNumberProperty().get());
    }

    @Test
    void addCollectionShouldAddCollectionWithGivenName() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("Marvel");

        viewModel.addCollection();

        assertEquals(1, viewModel.getCollections().size());
        assertEquals("Marvel", viewModel.getCollections().get(0).getName());
        assertEquals("", viewModel.newCollectionNameProperty().get());
    }

    @Test
    void addCollectionShouldTrimName() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("  DC  ");

        viewModel.addCollection();

        assertEquals(1, viewModel.getCollections().size());
        assertEquals("DC", viewModel.getCollections().get(0).getName());
    }

    @Test
    void addCollectionShouldNotAllowBlankName() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("   ");

        assertThrows(IllegalArgumentException.class, () -> viewModel.addCollection());
    }

    @Test
    void removeSelectedCollectionShouldRemoveAndClearComics() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("Marvel");
        viewModel.addCollection();

        Collection collection = viewModel.getCollections().get(0);
        viewModel.selectedCollectionProperty().set(collection);

        collection.addComic(new Comic("Spider-Man", 1));
        viewModel.refreshComicsForSelectedCollection();

        viewModel.removeSelectedCollection();

        assertTrue(viewModel.getCollections().isEmpty());
        assertTrue(viewModel.getComicsForSelectedCollection().isEmpty());
        assertEquals(null, viewModel.selectedCollectionProperty().get());
        assertEquals(null, viewModel.selectedComicProperty().get());
    }

    @Test
    void refreshComicsForSelectedCollectionShouldPopulateFromSelectedCollection() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        collection.addComic(new Comic("Spider-Man", 1));
        collection.addComic(new Comic("X-Men", 2));

        viewModel.getCollections().add(collection);
        viewModel.selectedCollectionProperty().set(collection);

        viewModel.refreshComicsForSelectedCollection();

        assertEquals(2, viewModel.getComicsForSelectedCollection().size());
        assertEquals("Spider-Man", viewModel.getComicsForSelectedCollection().get(0).getTitle());
        assertEquals("X-Men", viewModel.getComicsForSelectedCollection().get(1).getTitle());
    }

    @Test
    void refreshComicsForSelectedCollectionShouldClearWhenNoSelection() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        collection.addComic(new Comic("Spider-Man", 1));

        viewModel.getCollections().add(collection);
        viewModel.selectedCollectionProperty().set(collection);
        viewModel.refreshComicsForSelectedCollection();

        viewModel.selectedCollectionProperty().set(null);
        viewModel.refreshComicsForSelectedCollection();

        assertTrue(viewModel.getComicsForSelectedCollection().isEmpty());
    }

    @Test
    void addComicToSelectedCollectionShouldAddComic() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        viewModel.getCollections().add(collection);
        viewModel.selectedCollectionProperty().set(collection);

        viewModel.addComicToSelectedCollection("Spider-Man", 1);

        assertEquals(1, collection.getComics().size());
        assertEquals(1, viewModel.getComicsForSelectedCollection().size());
        assertEquals("Spider-Man", viewModel.getComicsForSelectedCollection().get(0).getTitle());
    }

    @Test
    void addComicToSelectedCollectionShouldThrowWhenNoCollectionSelected() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        assertThrows(IllegalStateException.class,
                () -> viewModel.addComicToSelectedCollection("Spider-Man", 1));
    }

    @Test
    void removeSelectedComicShouldRemoveFromCollectionAndList() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        Comic comic1 = new Comic("Spider-Man", 1);
        Comic comic2 = new Comic("X-Men", 2);

        collection.addComic(comic1);
        collection.addComic(comic2);

        viewModel.getCollections().add(collection);
        viewModel.selectedCollectionProperty().set(collection);
        viewModel.refreshComicsForSelectedCollection();

        viewModel.selectedComicProperty().set(comic1);
        viewModel.removeSelectedComic();

        assertEquals(1, collection.getComics().size());
        assertEquals(1, viewModel.getComicsForSelectedCollection().size());
        assertEquals("X-Men", viewModel.getComicsForSelectedCollection().get(0).getTitle());
    }

    @Test
    void removeSelectedComicShouldDoNothingWhenNoComicSelected() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        Comic comic1 = new Comic("Spider-Man", 1);

        collection.addComic(comic1);
        viewModel.getCollections().add(collection);
        viewModel.selectedCollectionProperty().set(collection);
        viewModel.refreshComicsForSelectedCollection();

        viewModel.removeSelectedComic();

        assertEquals(1, collection.getComics().size());
        assertEquals(1, viewModel.getComicsForSelectedCollection().size());
    }

    @Test
    void searchComicShouldReturnMatchingComic() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        Collection marvel = new Collection("Marvel");
        marvel.addComic(new Comic("Spider-Man", 1));
        marvel.addComic(new Comic("Spider-Man", 2));

        Collection dc = new Collection("DC");
        dc.addComic(new Comic("Batman", 1));

        viewModel.getCollections().add(marvel);
        viewModel.getCollections().add(dc);

        viewModel.searchTitleProperty().set("Spider-Man");
        viewModel.searchIssueNumberProperty().set("2");

        Comic result = viewModel.searchComic();

        assertEquals("Spider-Man", result.getTitle());
        assertEquals(2, result.getIssueNumber());
    }

    @Test
    void searchComicShouldReturnNullWhenNotFound() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        Collection marvel = new Collection("Marvel");
        marvel.addComic(new Comic("Spider-Man", 1));

        viewModel.getCollections().add(marvel);

        viewModel.searchTitleProperty().set("Batman");
        viewModel.searchIssueNumberProperty().set("1");

        Comic result = viewModel.searchComic();

        assertNull(result);
    }

    @Test
    void searchComicShouldThrowWhenIssueIsNotANumber() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        Collection marvel = new Collection("Marvel");
        marvel.addComic(new Comic("Spider-Man", 1));

        viewModel.getCollections().add(marvel);

        viewModel.searchTitleProperty().set("Spider-Man");
        viewModel.searchIssueNumberProperty().set("abc");

        assertThrows(IllegalArgumentException.class, () -> viewModel.searchComic());
    }

    @Test
    void searchComicShouldThrowWhenIssueIsNonPositive() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        Collection marvel = new Collection("Marvel");
        marvel.addComic(new Comic("Spider-Man", 1));

        viewModel.getCollections().add(marvel);

        viewModel.searchTitleProperty().set("Spider-Man");
        viewModel.searchIssueNumberProperty().set("0");

        assertThrows(IllegalArgumentException.class, () -> viewModel.searchComic());
    }
}
