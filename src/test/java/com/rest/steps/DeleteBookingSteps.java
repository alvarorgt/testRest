// src/test/java/com/rest/steps/DeleteBookingSteps.java
package com.rest.steps;

import com.rest.model.AuthRequest;
import com.rest.tasks.CreateToken;
import com.rest.tasks.DeleteBooking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteBookingSteps {

    private Actor user;
    private List<Map<String, String>> bookingDetails;
    private String token;

    @When("the user deletes the booking")
    public void theUserDeletesTheBookingWithID() {
        user = OnStage.theActorCalled("User");
        Integer storedBookingId = user.recall("bookingId");
        if (storedBookingId == null) {
            throw new IllegalStateException("Booking ID is not stored in the actor's memory.");
        }
        int bookingId = storedBookingId;
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        user.attemptsTo(CreateToken.withCredentials(authRequest.getUsername(), authRequest.getPassword()));
        token = user.recall("token");
        if (token == null) {
            throw new IllegalStateException("Auth token is not stored in the actor's memory.");
        }
        user.attemptsTo(DeleteBooking.withId(bookingId, token));
    }

    @Then("the delete response status should be {int}")
    public void theDeleteResponseStatusShouldBe(int statusCode) {
        Integer actualStatusCode = user.recall("deleteResponseStatusCode");
        if (actualStatusCode == null) {
            throw new AssertionError("The delete response status code should be " + statusCode + " but was null");
        }
        assertThat("The delete response status code should be " + statusCode, actualStatusCode, equalTo(statusCode));
    }

    @Then("the delete response message should be {string}")
    public void theDeleteResponseMessageShouldBe(String expectedMessage) {
        String actualMessage = user.recall("deleteResponse");
        assertThat("The delete response message should be " + expectedMessage, actualMessage, equalTo(expectedMessage));
    }
}