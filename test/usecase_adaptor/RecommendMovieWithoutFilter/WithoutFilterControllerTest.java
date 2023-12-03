package usecase_adaptor.RecommendMovieWithoutFilter;

import static org.mockito.ArgumentMatchers.argThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputBoundary;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputData;

class WithoutFilterControllerTest {
    @Test
    void execute_CallsInteractorWithCorrectInput() {
        // Arrange
        WithoutFilterInputBoundary withoutFilterInteractor = mock(WithoutFilterInputBoundary.class);
        WithoutFilterController controller = new WithoutFilterController(withoutFilterInteractor);
        String username = "user123";

        // Act
        controller.execute(username);

        // Assert
        // Verify that the interactor's execute method is called with the correct input data
        verify(withoutFilterInteractor, times(1)).execute(argThat(inputData ->
                inputData instanceof WithoutFilterInputData &&
                        ((WithoutFilterInputData) inputData).getUsername().equals(username)
        ));
    }
}





