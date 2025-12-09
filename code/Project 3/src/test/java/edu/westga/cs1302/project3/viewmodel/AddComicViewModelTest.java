package edu.westga.cs1302.project3.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Collection;
import edu.westga.cs1302.project3.model.Comic;

class AddComicViewModelTest {

    @Test
    void constructorShouldNotAllowNullMainViewModel() {
        assertThrows(IllegalArgumentException.class, () -> new AddComicViewModel(null));
    }

    @Test
    void addComicShouldDelegateToMainViewModelAndClearFields() {
        MainWindowViewModel mainVm = new MainWindowViewModel();
        Collection collection = new Collection("Marvel");
        mainVm.getCollections().add(collection);
        mainVm.selectedCollectionProperty().set(collection);

        AddComicViewModel addVm = new AddComicViewModel(mainVm);
        addVm.titleProperty().set("Spider-Man");
        addVm.issueNumberProperty().set("1");

        addVm.addComic();

        assertEquals(1, collection.getComics().size());
        Comic comic = collection.getComics().get(0);
        assertEquals("Spider-Man", comic.getTitle());
        assertEquals(1, comic.getIssueNumber());

        assertEquals("", addVm.titleProperty().get());
        assertEquals("", addVm.issueNumberProperty().get());
    }

    @Test
    void addComicShouldThrowWhenIssueIsNotANumber() {
        MainWindowViewModel mainVm = new MainWindowViewModel();
        mainVm.getCollections().add(new Collection("Marvel"));
        mainVm.selectedCollectionProperty().set(mainVm.getCollections().get(0));

        AddComicViewModel addVm = new AddComicViewModel(mainVm);
        addVm.titleProperty().set("Spider-Man");
        addVm.issueNumberProperty().set("abc");

        assertThrows(IllegalArgumentException.class, () -> addVm.addComic());
    }

    @Test
    void addComicShouldThrowWhenIssueIsNonPositive() {
        MainWindowViewModel mainVm = new MainWindowViewModel();
        mainVm.getCollections().add(new Collection("Marvel"));
        mainVm.selectedCollectionProperty().set(mainVm.getCollections().get(0));

        AddComicViewModel addVm = new AddComicViewModel(mainVm);
        addVm.titleProperty().set("Spider-Man");
        addVm.issueNumberProperty().set("0");

        assertThrows(IllegalArgumentException.class, () -> addVm.addComic());
    }

    @Test
    void addComicShouldThrowWhenNoCollectionSelected() {
        MainWindowViewModel mainVm = new MainWindowViewModel();
        // no selected collection

        AddComicViewModel addVm = new AddComicViewModel(mainVm);
        addVm.titleProperty().set("Spider-Man");
        addVm.issueNumberProperty().set("1");

        assertThrows(IllegalStateException.class, () -> addVm.addComic());
    }
}
