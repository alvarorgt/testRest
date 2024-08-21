package com.rest.tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.List;
import java.util.Map;

public class UpdateBooking {

    public static Performable withDetails(int bookingId, List<Map<String, String>> bookingDetails, String token) {
        return Task.where("{0} updates booking with ID " + bookingId,
                actor -> {
                    Response response = RestAssured.given()
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .header("Authorization", "Bearer " + token)
                            .body(bookingDetails)
                            .put("/booking/" + bookingId);

                    String responseBody = response.asString();
                    int statusCode = response.getStatusCode();
                    String responseFirstname = response.jsonPath().getString("firstname");

                    actor.remember("updateResponse", responseBody);
                    actor.remember("updateResponseStatusCode", statusCode);
                    actor.remember("updateResponseFirstname", responseFirstname);
                }
        );
    }
}