package use_case.DeleteWatchlist;

//import data_access.DeleteWatchlistDataAccessInterface;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DeleteWatchlistInteractorTest {

    @Test
    public void successTest() {
        // Arrange
        DeleteWatchlistDataAccessInterface dataAccessObject = mock(DeleteWatchlistDataAccessInterface.class);
        DeleteWatchlistOutputBoundary successPresenter = mock(DeleteWatchlistOutputBoundary.class);
        DeleteWatchlistInteractor interactor = new DeleteWatchlistInteractor(successPresenter, dataAccessObject);

        User user = new CommonUserFactory().create("user",
                "password", LocalDateTime.of(1,1,1,1,1));
        Movie movie = new Movie("Movie1", 1);
        user.addMovieToWatchlist(movie); // Assuming User has this method
        when(dataAccessObject.getUser("user")).thenReturn(user);

        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, "user");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(successPresenter).PrepareSuccessView(any(DeleteWatchlistOutputData.class));

        ArgumentCaptor<DeleteWatchlistOutputData> argument = ArgumentCaptor.forClass(DeleteWatchlistOutputData.class);
        verify(successPresenter).PrepareSuccessView(argument.capture());

        // Additional Assertion to check the message
        assertEquals("delete Movie1 successfully", argument.getValue().getMessage());
    }

    @Test
    public void failTestMovieNotInWatchlist() {
        // Arrange
        DeleteWatchlistDataAccessInterface dataAccessObject = mock(DeleteWatchlistDataAccessInterface.class);
        DeleteWatchlistOutputBoundary failPresenter = mock(DeleteWatchlistOutputBoundary.class);
        DeleteWatchlistInteractor interactor = new DeleteWatchlistInteractor(failPresenter, dataAccessObject);

        User user = new CommonUserFactory().create("user",
                "password", LocalDateTime.of(1,1,1,1,1));
        Movie movie = new Movie("Movie1", 1);
        when(dataAccessObject.getUser("user")).thenReturn(user);

        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, "user");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(failPresenter).PrepareFailView(movie.getName() + " doesn't exist");
    }

//    @Test
//    public void failTestUserNotFound() {
//        // Arrange
//        DeleteWatchlistDataAccessInterface dataAccessObject = mock(DeleteWatchlistDataAccessInterface.class);
//        DeleteWatchlistOutputBoundary failPresenter = mock(DeleteWatchlistOutputBoundary.class);
//        DeleteWatchlistInteractor interactor = new DeleteWatchlistInteractor(failPresenter, dataAccessObject);
//
//        Movie movie = new Movie("Movie1", 1);
//        when(dataAccessObject.getUser("user")).thenReturn(null);
//
//        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, "user");
//
//        // Act
//        interactor.execute(inputData);
//
//        // Assert
//        verify(failPresenter).PrepareFailView("User not found");
//    }
}
