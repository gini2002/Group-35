package usecase_adaptor.MovieSearchByKeyword;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import use_case.MovieSearchByKeyword.RecommendInputBoundary;
import use_case.MovieSearchByKeyword.RecommendInputData;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;

import static org.mockito.Mockito.*;

class SearchByNameControllerTest {
    @Test
    void execute_CallsInteractorWithCorrectInput() {
        // Arrange
        RecommendInputBoundary searchByNameInteractor = mock(RecommendInputBoundary.class);
        SearchByNameController controller = new SearchByNameController(searchByNameInteractor);
        String keyword = "action";

        // Act
        controller.execute(keyword);

        // Assert
        // Verify that the interactor's execute method is called with the correct input data
        verify(searchByNameInteractor, times(1)).execute(argThat(inputData ->
                inputData instanceof RecommendInputData &&
                        ((RecommendInputData) inputData).getKeyword().equals(keyword)
        ));
    }
}