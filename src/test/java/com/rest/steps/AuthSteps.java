package com.rest.steps;

import com.rest.config.AuthConfig;
import com.rest.model.AuthRequest;
import com.rest.tasks.CreateToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@Component
@PropertySource("classpath:configuration/Application.properties")
public class AuthSteps {

    private Actor user;

    @Autowired
    private AuthConfig authConfig;

    @Given("the user wants to authenticate")
    public void theUserWantsToAuthenticate() {
        OnStage.setTheStage(Cast.whereEveryoneCan(actor -> {}));
        user = OnStage.theActorCalled("User");
    }

    @When("the user sends valid credentials")
    public void theUserSendsValidCredentials() {
        user = OnStage.theActorCalled("User");
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        user.attemptsTo(CreateToken.withCredentials(authRequest.getUsername(), authRequest.getPassword()));
    }

    @Then("the user should receive a valid token")
    public void theUserShouldReceiveAValidToken() {
        String token = user.recall("token");
        assertThat(token, notNullValue());
    }
}