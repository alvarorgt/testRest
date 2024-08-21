package com.rest.tasks;

import com.rest.model.AuthRequest;
import com.rest.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateToken implements Task {

    private final String username;
    private final String password;

    public CreateToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static CreateToken withCredentials(String username, String password) {
        return instrumented(CreateToken.class, username, password);
    }

    @Override
    @Step("{0} attempts to create a token with username #username and password #password")
    public <T extends Actor> void performAs(T actor) {
        AuthRequest authRequest = new AuthRequest(username, password);

        Response response = RestAssured.given()
                .baseUri(Constants.BASE_URL)
                .contentType("application/json")
                .body(authRequest)
                .post(Constants.CREATE_TOKEN_ENDPOINT);

        System.out.println("API Response: " + response.asString());

        String token = response.then()
                .extract()
                .path("token");

        actor.remember("token", token);
    }
}