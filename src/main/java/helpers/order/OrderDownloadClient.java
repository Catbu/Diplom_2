package helpers.order;

import helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrderDownloadClient extends Client {
    public static final String ORDER_DOWNLOAD_PATH = "orders";

    @Step("Создать пользователя авторизации заказа")
    public Response orderAutoDownload(String token) {
        return spec().auth().oauth2(token.replace("Bearer ",""))
                .get(ORDER_DOWNLOAD_PATH);
    }

    @Step("Порядок загрузки без авторизации пользователя")
    public Response orderNotAutoDownload() {
        return spec().get(ORDER_DOWNLOAD_PATH);
    }
}
