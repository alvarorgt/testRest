package com.rest.tasks;

import com.rest.model.Booking;
import com.rest.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBooking implements Task {

    private final Booking booking;

    public CreateBooking(Booking booking) {
        this.booking = booking;
    }

    public static CreateBooking withDetails(Booking booking) {
        return instrumented(CreateBooking.class, booking);
    }

    @Override
    @Step("{0} attempts to create a booking")
    public <T extends Actor> void performAs(T actor) {
        Response response = RestAssured.given()
                .baseUri(Constants.BASE_URL)
                .contentType("application/json")
                .body(booking)
                .post(Constants.BOOKING_ENDPOINT);

        int bookingId = response.jsonPath().getInt("bookingid");
        actor.remember("bookingId", bookingId);

        int statusCode = response.getStatusCode();
        actor.remember("responseStatusCode", statusCode);

        String responseFirstname = response.jsonPath().getString("booking.firstname");
        actor.remember("responseFirstname", responseFirstname);
    }
}