package usecase_adaptor.MainMenu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuStateTest {

    @Test
    void setUserName() {
        MainMenuState state = new MainMenuState();
        state.setUserName("i");
        assertEquals("i", state.getUserName());
    }

    @Test
    void getUserName() {
        MainMenuState state = new MainMenuState();
        state.setUserName("100");
        assertEquals("100", state.getUserName());
    }
}