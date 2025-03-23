package apiCreateUser;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserSteps {
    private final String baseURI;
    private static final String USER_CREATION_API = "/api/auth/register";
    private static final String DELETE_USER = "/api/auth/user";

    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(6,8);
    private final String name = faker.name().firstName();

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserSteps(String baseURI) {
        this.baseURI = baseURI;
    }

    @Step("Отправляем POST запрос на создание курьера")
    public Response createUser() {
        UserJson user = new UserJson(email, password, name);
        return given()
                .baseUri(baseURI)
                .log().all()
                .header("Content-type","application/json")
                .and()
                .body(user)
                .when()
                .post(USER_CREATION_API);
    }

    @Step("Получаем Access Token и удаляем пользователя")
    public void getTokenAndDeleteUser(Response creation) {
        String token = creation.then().extract().jsonPath().getString("accessToken");

        Response delete = given()
                .baseUri(baseURI)
                .log().all()
                .header("Authorization",token)
                .when()
                .delete(DELETE_USER);
    }
}
