package use_case.MovieSearchByKeyword;

import entity.Movie;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.MovieSearchByKeyword.SearchByNameDataAccessInterface;
import data_access.MovieDataAccessObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchByKeywordInteractorTest {
    @Test
    void executeWithValidKeywordShouldPrepareSuccessView() {
        // Arrange
        SearchByNameDataAccessInterface dataAccessObject = Mockito.mock(SearchByNameDataAccessInterface.class);
        SearchByNameOutputBoundary presenter = Mockito.mock(SearchByNameOutputBoundary.class);
        SearchByNameInteractor interactor = new SearchByNameInteractor(dataAccessObject, presenter);

        // Define input data
        String validKeyword = "action";
        RecommendInputData inputData = new RecommendInputData(validKeyword);

        // Mock the behavior of the data access object to return some recommended movies
        Mockito.when(dataAccessObject.getRecommendedMovies(validKeyword))
                .thenReturn(List.of(new Movie("Movie1"), new Movie("Movie2")));

        // Act
        interactor.execute(inputData);

        // Assert
        Mockito.verify(presenter).prepareSuccessView(Mockito.any());
    }

    @Test
    void executeWithInvalidKeywordShouldPrepareFailView() {
        // Arrange
        SearchByNameDataAccessInterface dataAccessObject = Mockito.mock(SearchByNameDataAccessInterface.class);
        SearchByNameOutputBoundary presenter = Mockito.mock(SearchByNameOutputBoundary.class);
        SearchByNameInteractor interactor = new SearchByNameInteractor(dataAccessObject, presenter);

        // Define input data with an invalid keyword (empty)
        String invalidKeyword = "";
        RecommendInputData inputData = new RecommendInputData(invalidKeyword);

        // Act
        interactor.execute(inputData);

        // Assert
        Mockito.verify(presenter).prepareFailView("Invalid keyword");
    }

    @Test
    void executeWithNoResultsShouldPrepareFailView() {
        // Arrange
        SearchByNameDataAccessInterface dataAccessObject = Mockito.mock(SearchByNameDataAccessInterface.class);
        SearchByNameOutputBoundary presenter = Mockito.mock(SearchByNameOutputBoundary.class);
        SearchByNameInteractor interactor = new SearchByNameInteractor(dataAccessObject, presenter);

        // Define input data with a valid keyword
        String validKeyword = "nonexistent";
        RecommendInputData inputData = new RecommendInputData(validKeyword);

        // Mock the behavior of the data access object to return an empty list of recommended movies
        Mockito.when(dataAccessObject.getRecommendedMovies(validKeyword))
                .thenReturn(List.of());

        // Act
        interactor.execute(inputData);

        // Assert
        Mockito.verify(presenter).prepareFailView("No movies found for the keyword: " + validKeyword);
    }
}