package use_case.login;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import usecase_adaptor.login.LoginPresenter;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    public void successTest() {
        User users = new CommonUserFactory().
                create("user", "password",
                        LocalDateTime.of(1,1,1,1,1));

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./ShareTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userDataAccessObject.save(users);

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("user", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("not expect to fail");
            }
        };

        LoginInputData inputData = new LoginInputData("user", "password");

        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, presenter);
        interactor.execute(inputData);
    }


    @Test
    public void failTest() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./ShareTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("not expect to success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("name: Account does not exist.", error);
            }
        };

        LoginInputData inputData = new LoginInputData("name", "password");
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, presenter);
        interactor.execute(inputData);

    }

}