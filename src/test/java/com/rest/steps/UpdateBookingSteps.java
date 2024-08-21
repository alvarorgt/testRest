package com.rest.steps;

import com.rest.tasks.UpdateBooking;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateBookingSteps {
    private Actor user;
    private List<Map<String, String>> updatedDetails;
    private String token;

    @When("the user updates the booking with the following details:")
    public void theUserUpdatesTheBookingWithID(List<Map<String, String>> updatedDetails) {
        this.updatedDetails = updatedDetails;
        user = OnStage.theActorCalled("User");
        Integer storedBookingId = user.recall("bookingId");
        if (storedBookingId == null) {
            throw new IllegalStateException("Booking ID is not stored in the actor's memory.");
        }
        int bookingId = storedBookingId;
        user.attemptsTo(UpdateBooking.withDetails(bookingId, updatedDetails, token));
    }

}
