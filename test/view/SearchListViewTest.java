package view;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.SearchList.SearchListViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;

class SearchListViewTest {
    @Test
    void updateViewWithValidSearchListShouldUpdateListModel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            // Creating mock objects
            SearchListViewModel searchListViewModel = Mockito.mock(SearchListViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            SearchListView searchListView = new SearchListView(searchListViewModel, viewManagerModel);

            // Mocking the behavior of searchListViewModel
            String[] searchList = {"Movie1", "Movie2", "Movie3"};
            when(searchListViewModel.getSearchList()).thenReturn(searchList);
            when(searchListViewModel.getError()).thenReturn(null);

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "searchList", null, searchList);

            // Act
            searchListView.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = searchListView.listModel;
            assertEquals(searchList.length, listModel.size());
            for (int i = 0; i < searchList.length; i++) {
                assertEquals(searchList[i], listModel.get(i));
            }

            // Verify that the error label is updated
            assertNull(searchListView.errorLabel.getText());
        });
    }

    @Test
    void updateViewWithNullSearchListShouldNotUpdateListModel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            // Creating mock objects
            SearchListViewModel searchListViewModel = Mockito.mock(SearchListViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            SearchListView searchListView = new SearchListView(searchListViewModel, viewManagerModel);

            // Mocking the behavior of searchListViewModel
            when(searchListViewModel.getSearchList()).thenReturn(null);
            when(searchListViewModel.getError()).thenReturn("Error message");

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "searchList", null, null);

            // Act
            searchListView.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = searchListView.listModel;
            assertEquals(0, listModel.size()); // List model should not be updated

            // Verify that the error label is updated
            assertEquals("Error message", searchListView.errorLabel.getText());
        });
    }

    @Test
    void updateViewWithErrorShouldUpdateErrorLabel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            // Creating mock objects
            SearchListViewModel searchListViewModel = Mockito.mock(SearchListViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            SearchListView searchListView = new SearchListView(searchListViewModel, viewManagerModel);

            // Mocking the behavior of searchListViewModel
            when(searchListViewModel.getSearchList()).thenReturn(new String[]{"Movie1", "Movie2"});
            when(searchListViewModel.getError()).thenReturn("Error message");

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "error", null, "Error message");

            // Act
            searchListView.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = searchListView.listModel;
            assertEquals(2, listModel.size()); // List model should be updated

            // Verify that the error label is updated
            assertEquals("Error message", searchListView.errorLabel.getText());
        });
    }

}