package view;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.SearchList.SearchListViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;

class SearchListViewTest {
    @Test
    void propertyChange_SearchListPropertySet_UpdatesView() {
        // Arrange
        SearchListViewModel viewModel = mock(SearchListViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        SearchListView searchListView = new SearchListView(viewModel, viewManagerModel);
        searchListView.listModel = spy(new DefaultListModel<>());

        // Act
        searchListView.propertyChange(new PropertyChangeEvent(this, "searchList", null, null));

        // Assert
        // Verify that updateView() is called
        // (This could be extended based on the actual implementation)
        verify(searchListView, times(1)).updateView();
    }

    @Test
    void propertyChange_ErrorPropertySet_DoesNotUpdateView() {
        // Arrange
        SearchListViewModel viewModel = mock(SearchListViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        SearchListView searchListView = new SearchListView(viewModel, viewManagerModel);
        searchListView.listModel = spy(new DefaultListModel<>());

        // Act
        searchListView.propertyChange(new PropertyChangeEvent(this, "error", null, "Invalid search"));

        // Assert
        // Verify that updateView() is not called for error property change
        verify(searchListView, times(0)).updateView();
    }

    @Test
    void updateView_SearchListNotNull_UpdatesListModelAndRepaintsUI() {
        // Arrange
        SearchListViewModel viewModel = mock(SearchListViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        SearchListView searchListView = new SearchListView(viewModel, viewManagerModel);
        searchListView.listModel = spy(new DefaultListModel<>());

        // Mock the getSearchList() method
        when(viewModel.getSearchList()).thenReturn(new String[]{"Movie1", "Movie2"});

        // Act
        searchListView.updateView();

        // Assert
        // Verify that listModel is cleared and updated
        verify(searchListView.listModel, times(1)).clear();
        verify(searchListView.listModel, times(1)).addAll(Arrays.asList("Movie1", "Movie2"));
        verify(searchListView.searchList, times(1)).setModel(searchListView.listModel);

        // Verify that errorLabel is updated
        verify(searchListView.errorLabel, times(1)).setText(anyString());

        // Verify that UI is repainted
        verify(searchListView, times(1)).revalidate();
        verify(searchListView, times(1)).repaint();
    }

    @Test
    void updateView_SearchListNull_DoesNotUpdateListModel() {
        // Arrange
        SearchListViewModel viewModel = mock(SearchListViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        SearchListView searchListView = new SearchListView(viewModel, viewManagerModel);
        searchListView.listModel = spy(new DefaultListModel<>());

        // Mock the getSearchList() method
        when(viewModel.getSearchList()).thenReturn(null);

        // Act
        searchListView.updateView();

        // Assert
        // Verify that listModel is not cleared or updated
        verify(searchListView.listModel, times(0)).clear();
        verify(searchListView.listModel, times(0)).addAll(anyList());
        verify(searchListView.searchList, times(0)).setModel(searchListView.listModel);

        // Verify that errorLabel is updated
        verify(searchListView.errorLabel, times(1)).setText(anyString());

        // Verify that UI is repainted
        verify(searchListView, times(1)).revalidate();
        verify(searchListView, times(1)).repaint();
    }

}