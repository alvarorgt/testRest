package com.rest.tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class GetBooking {

    public static Performable withId(int id) {
        return Task.where("{0} retrieves booking with ID " + id,
                actor -> {
                    Response response = RestAssured.get("/booking/" + id);
                    String responseBody = response.asString();
                    int statusCode = response.getStatusCode();

                    actor.remember("bookingResponse", responseBody);
                    actor.remember("responseStatusCode", statusCode);
                }
        );
    }
}