package com.rest.steps;

import com.rest.model.AuthRequest;
import com.rest.tasks.CreateToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class AuthSteps {

    private Actor user;

    @Given("the user wants to authenticate")
    public void theUserWantsToAuthenticate() {
        OnStage.setTheStage(Cast.whereEveryoneCan(actor -> {}));
        user = OnStage.theActorCalled("User");
    }

    @When("the user sends valid credentials")
    public void theUserSendsValidCredentials() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        user.attemptsTo(CreateToken.withCredentials(authRequest.getUsername(), authRequest.getPassword()));
    }

    @Then("the user should receive a valid token")
    public void theUserShouldReceiveAValidToken() {
        String token = user.recall("token");
        assertThat(token, notNullValue());
    }
}