package com.rest.steps;

import com.rest.model.Booking;
import com.rest.model.BookingDates;
import com.rest.tasks.CreateBooking;
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

public class CreateBookingSteps {

    private Actor user;
    private List<Map<String, String>> bookingDetails;

    @Given("the booking details are:")
    public void theBookingDetailsAre(List<Map<String, String>> bookingDetails) {
        this.bookingDetails = bookingDetails;
        OnStage.setTheStage(Cast.whereEveryoneCan(actor -> {}));
        user = OnStage.theActorCalled("User");
    }

    @When("the user creates the bookings")
    public void theUserCreatesTheBookings() {
        for (Map<String, String> details : bookingDetails) {
            Booking booking = new Booking(
                    details.get("firstname"),
                    details.get("lastname"),
                    Integer.parseInt(details.get("totalprice")),
                    Boolean.parseBoolean(details.get("depositpaid")),
                    new BookingDates(details.get("checkin"), details.get("checkout")),
                    details.get("additionalneeds")
            );
            user.attemptsTo(CreateBooking.withDetails(booking));
            int bookingId = user.recall("bookingId");
            user.remember("bookingId", bookingId);
        }
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        Integer actualStatusCode = user.recall("responseStatusCode");
        assertThat("The response status code should be " + statusCode, actualStatusCode, equalTo(statusCode));
    }

    @Then("the response firstname should match the request firstname")
    public void theResponseFirstnameShouldMatchTheRequestFirstname() {
        String actualFirstname = user.recall("responseFirstname");
        String expectedFirstname = bookingDetails.get(0).get("firstname");
        assertThat("The response firstname should match the request firstname", actualFirstname, equalTo(expectedFirstname));
    }
}