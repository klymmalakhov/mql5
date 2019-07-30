package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import rest.models.CreateLinkModel;
import rest.models.DataCreateLink;

import static io.restassured.RestAssured.given;
import static properties.PropertyHolder.getPropValue;

public class LstRest {

    public static String createShortLink(String link) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        DataCreateLink data = DataCreateLink.builder()
                .data("link")
                .url(link)
                .utm("utm_campaign=[domain]")
                .build();
        CreateLinkModel body = CreateLinkModel.builder()
                .data(data)
                .build();

        return
                given()
                        .header("X-AUTH-TOKEN", getPropValue("X-AUTH-TOKEN"))
                        .header("Content-Type", ContentType.JSON)
                .when()
                        .body(body)
                        .post(getPropValue("LST_CREATE_LINK"))
                .then()
                        .statusCode(200)
                        .extract().path("data.short");

    }

    public static void deleteLink(String shortLink) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        String codeOfShortLink = shortLink.replaceAll(".*/", "");

        given().log().all()
                .header("X-AUTH-TOKEN", getPropValue("X-AUTH-TOKEN"))
                .header("Content-Type", ContentType.JSON)
        .when()
                .delete(getPropValue("LST_DELETE_LINK") + codeOfShortLink)
        .then()
                .statusCode(200).extract().response();

    }
}
