package helpers.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class OrderChecks {

    @Step("Проверка успешного создания заказа")
    public boolean createdOrderSuccessfully(Response createOrderResponse) {
        return createOrderResponse.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
    }

    @Step("Проверка созданного заказа, а не ингредиентов")
    public boolean createdOrderNotIng(Response createOrderResponse) {
        return createOrderResponse.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("success");
    }

    @Step("Проверка создания плохого хэша")
    public void createdOrderBadHash(Response createOrderResponse) {
        createOrderResponse.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

    @Step("Проверика авторизации пользователя на загрузку заказа")
    public ArrayList<String> downloadOrderAutoUser(Response response) {
        return response.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().path("orders._id");
    }

    @Step("Проверка загрузки заказа, а не авторизацию пользователя")
    public boolean downloadOrderNotAutoUser(Response response) {
        return response.then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .extract().path("success");
    }
}
