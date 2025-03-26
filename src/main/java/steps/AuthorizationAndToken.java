package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorizationAndToken {

    @Step("Авторизация существующего пользователя")
    public Response authorizationExistingUser(String email, String password){
        ApiAuthorizationUserBody json1 = new ApiAuthorizationUserBody(email, password);
        return given()
                .header("Content-type", "application/json")
                .body(json1)
                .when()
                .post("/api/auth/login");
    }

    @Step("Получение accessToken")
    public String getAccessToken(Response authorizationExistingUser){
        BodyResponse bodyResponse = authorizationExistingUser.body().as(BodyResponse.class);
        return bodyResponse.getAccessToken();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String getAccessToken){
        given()
                .header("Authorization", getAccessToken)
                .when()
                .delete("/api/auth/user");
    }

}
