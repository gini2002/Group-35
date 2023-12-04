package use_case.signup;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupInteractorTest {

    private SignupUserDataAccessInterface userDataAccessObjectMock;
    private SignupOutputBoundary userPresenterMock;
    private UserFactory userFactoryMock;
    private SignupInteractor signupInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessObjectMock = mock(SignupUserDataAccessInterface.class);
        userPresenterMock = mock(SignupOutputBoundary.class);
        userFactoryMock = mock(UserFactory.class);

        signupInteractor = new SignupInteractor(userDataAccessObjectMock, userPresenterMock, userFactoryMock);
    }

    @Test
    void execute_UserDoesNotExistAndPasswordsMatch_SuccessViewPrepared() {
        // Arrange
        SignupInputData inputMock = mock(SignupInputData.class);
        when(inputMock.getUsername()).thenReturn("testUser");
        when(inputMock.getPassword()).thenReturn("password");
        when(inputMock.getRepeatPassword()).thenReturn("password");

        when(userDataAccessObjectMock.existsByName("testUser")).thenReturn(false);

        LocalDateTime now = LocalDateTime.now();
        User userMock = mock(User.class);
        when(userMock.getName()).thenReturn("testUser"); // Mock the behavior for getName()

        // Ensure that userFactoryMock.create returns the userMock
        when(userFactoryMock.create(eq("testUser"), eq("password"), any(LocalDateTime.class)))
                .thenReturn(userMock);

        // Act
        signupInteractor.execute(inputMock);

        // Assert
        verify(userPresenterMock).prepareSuccessView(argThat(outputData ->
                outputData != null && "testUser".equals(outputData.getUsername())));
        verify(userPresenterMock, never()).prepareFailView(any());
    }

    @Test
    void execute_UserAlreadyExists_FailViewPrepared() {
        // Arrange
        SignupInputData inputMock = mock(SignupInputData.class);
        when(inputMock.getUsername()).thenReturn("existingUser");

        when(userDataAccessObjectMock.existsByName("existingUser")).thenReturn(true);

        // Act
        signupInteractor.execute(inputMock);

        // Assert
        verify(userPresenterMock).prepareFailView("User already exists.");
        verify(userPresenterMock, never()).prepareSuccessView(Mockito.any());
    }

    @Test
    void execute_PasswordsDoNotMatch_FailViewPrepared() {
        // Arrange
        SignupInputData inputMock = mock(SignupInputData.class);
        when(inputMock.getUsername()).thenReturn("testUser");
        when(inputMock.getPassword()).thenReturn("password");
        when(inputMock.getRepeatPassword()).thenReturn("differentPassword");

        when(userDataAccessObjectMock.existsByName("testUser")).thenReturn(false);

        // Act
        signupInteractor.execute(inputMock);

        // Assert
        verify(userPresenterMock).prepareFailView("Passwords don't match.");
        verify(userPresenterMock, never()).prepareSuccessView(Mockito.any());
    }

}