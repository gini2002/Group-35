package usecase_adaptor.MainMenu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuViewModelTest {

    @Test
    void setState() {
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        MainMenuState state = new MainMenuState();
        state.setUserName("name");
        mainMenuViewModel.setState(state);
        assertEquals("name", mainMenuViewModel.getState().getUserName());
    }

    @Test
    void setLoggedInUser() {
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        mainMenuViewModel.setLoggedInUser("name");
        assertEquals("name", mainMenuViewModel.getLoggedInUser());
    }

    @Test
    void getState() {
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        MainMenuState state = new MainMenuState();
        mainMenuViewModel.setState(state);
        assertEquals(state, mainMenuViewModel.getState());
    }

    @Test
    void getLoggedInUser() {
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        mainMenuViewModel.setLoggedInUser("");
        assertEquals("", mainMenuViewModel.getLoggedInUser());
    }

    @Test
    void firePropertyChanged() {
        //TODO
    }

    @Test
    void addPropertyChangeListener() {
        //TODO
    }
}