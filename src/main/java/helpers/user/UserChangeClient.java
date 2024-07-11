package helpers.user;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.user.UserChange;

public class UserChangeClient extends Client {
    public static final String USER_CHANGE_PATH = "auth/user";

    @Step("Изменить пользователя авторизации по электронной почте")
    public Response userChangeAuto(String token, UserChange userChange) {
    return spec().auth().oauth2(token.replace("Bearer ","")).body(userChange)
            .patch(USER_CHANGE_PATH);
    }

    @Step("Изменить адрес электронной почты, а не пользователя авторизации")
    public Response userChangeNotAuto(UserChange userChange) {
        return spec().body(userChange).patch(USER_CHANGE_PATH);
    }
}