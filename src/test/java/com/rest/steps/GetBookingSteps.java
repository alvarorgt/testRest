package com.rest.steps;

import com.rest.tasks.GetBooking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetBookingSteps {

    private Actor user;
    private List<Map<String, String>> bookingDetails;

    @Given("the booking IDs are stored")
    public void theBookingIDsAreStored() {
        user = OnStage.theActorCalled("User");
        bookingDetails = user.recall("bookingDetails");
        if (bookingDetails == null) {
            throw new IllegalStateException("Booking details are not stored in the actor's memory.");
        }
    }

    @When("the user retrieves the bookings")
    public void theUserRetrievesTheBookings() {
        user = OnStage.theActorCalled("User");
        Integer storedBookingId = user.recall("bookingId");
        if (storedBookingId == null) {
            throw new IllegalStateException("Booking ID is not stored in the actor's memory.");
        }
        int bookingId = storedBookingId;
        user.attemptsTo(GetBooking.withId(bookingId));
    }

    @When("the user retrieves the booking with ID")
    public void theUserRetrievesTheBookingWithID() {
        user = OnStage.theActorCalled("User");
        int storedBookingId = user.recall("bookingId");
        user.attemptsTo(GetBooking.withId(storedBookingId));
    }
}