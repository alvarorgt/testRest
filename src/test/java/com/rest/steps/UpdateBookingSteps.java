package com.rest.steps;

import com.rest.model.Booking;
import com.rest.model.BookingDates;
import com.rest.tasks.CreateBooking;
import com.rest.tasks.GetBooking;
import com.rest.tasks.UpdateBooking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UpdateBookingSteps {
    private Actor user;
    private List<Map<String, String>> updatedDetails;
    private String token;

    @Given("the user has a valid token")
    public void theUserHasAValidToken() {
        token = user.recall("authToken");
        if (token == null) {
            throw new IllegalStateException("Auth token is not stored in the actor's memory.");
        }
    }

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
