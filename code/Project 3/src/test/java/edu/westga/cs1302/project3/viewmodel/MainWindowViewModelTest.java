package edu.westga.cs1302.project3.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Collection;

class MainWindowViewModelTest {

    @Test
    void constructorShouldInitializeEmptyState() {
        MainWindowViewModel viewModel = new MainWindowViewModel();

        assertEquals("", viewModel.newCollectionNameProperty().get());
        assertTrue(viewModel.getCollections().isEmpty());
        assertEquals(null, viewModel.selectedCollectionProperty().get());
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
    void removeSelectedCollectionShouldRemoveFromListAndClearSelection() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("Marvel");
        viewModel.addCollection();
        viewModel.newCollectionNameProperty().set("DC");
        viewModel.addCollection();

        Collection toRemove = viewModel.getCollections().get(0);
        viewModel.selectedCollectionProperty().set(toRemove);

        viewModel.removeSelectedCollection();

        assertEquals(1, viewModel.getCollections().size());
        assertEquals("DC", viewModel.getCollections().get(0).getName());
        assertEquals(null, viewModel.selectedCollectionProperty().get());
    }

    @Test
    void removeSelectedCollectionShouldDoNothingWhenNoSelection() {
        MainWindowViewModel viewModel = new MainWindowViewModel();
        viewModel.newCollectionNameProperty().set("Marvel");
        viewModel.addCollection();

        viewModel.removeSelectedCollection();

        assertEquals(1, viewModel.getCollections().size());
    }
}
