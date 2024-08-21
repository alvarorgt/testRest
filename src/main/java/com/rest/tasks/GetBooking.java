package com.rest.tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class GetBooking {

    public static Performable withId(int id) {
        return Task.where("{0} retrieves booking with ID " + id,
                actor -> {
                    // Perform the HTTP GET request using RestAssured
                    Response response = RestAssured.get("/booking/" + id);
                    String responseBody = response.asString();
                    int statusCode = response.getStatusCode();

                    // Store the response and status code in the actor's memory
                    actor.remember("bookingResponse", responseBody);
                    actor.remember("responseStatusCode", statusCode);
                }
        );
    }
}