package helpers.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;

public class UserChecks {

    @Step("Проверка, что пользователь создан успешно")
    public String createdUserSuccessfully(ValidatableResponse createUserResponse) {
        return createUserResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("accessToken");
    }

    @Step("Проверка существования пользователя")
    public String createdExistsUser(ValidatableResponse existUserMessage) {
        return existUserMessage
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");
    }

    @Step("Проверка пользователя, а не имя")
    public String createdNotNameUser(ValidatableResponse notNameUserMessage) {
        return notNameUserMessage
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");
    }

    @Step("Проверка входа пользователя успешно")
    public boolean loginSuccessfully(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step("Не удалось проверить вход пользователя")
    public boolean loginFailed(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("success");
    }

    @Step("Проверка авторизации пользователя")
    public boolean userAutoChange(Response response) {
        return response.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step("Проверка пользователя без авторизации")
    public String userNotAutoChange(Response response) {
        return response.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract()
                .path("message");
    }
}
