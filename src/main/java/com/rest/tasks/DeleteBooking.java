package com.rest.tasks;

import com.rest.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class DeleteBooking {

    public static Performable withId(int bookingId, String token) {
        return Task.where("{0} deletes booking with ID " + bookingId,
                actor -> {
                    Response response = RestAssured.given()
                            .baseUri(Constants.BASE_URL)
                            .header("Content-Type", "application/json")
                            .header("Cookie", "token=" + token)
                            .delete("/booking/" + bookingId);

                    String responseBody = response.asString();
                    int statusCode = response.getStatusCode();

                    actor.remember("deleteResponse", responseBody);
                    actor.remember("deleteResponseStatusCode", statusCode);
                }
        );
    }
}