package rest;


import io.restassured.http.ContentType;
import properties.PropertyHolder;
import rest.models.CreateLinkModel;
import rest.models.DataCreateLink;

import static io.restassured.RestAssured.given;
import static properties.PropertyHolder.getPropValue;

public class LstRest {

    static public String generatedShortLink;

    /**
     * Generate short link using API LST
     * @param link source link
     * @return generated link
     */
    public static String createShortLink(String link) {
        DataCreateLink data = DataCreateLink.builder()
                .data("link")
                .url(link)
                .utm("utm_campaign=[domain]")
                .build();
        CreateLinkModel body = CreateLinkModel.builder()
                .data(data)
                .build();

        generatedShortLink =
                given().log().all()
                        .header("X-AUTH-TOKEN", getPropValue("X-AUTH-TOKEN"))
                        .header("Content-Type", ContentType.JSON)
                        .header("user-agent", PropertyHolder.getPropValue("USER_AGENT"))
                .when()
                        .body(body)
                        .post(getPropValue("LST_CREATE_LINK"))
                .then()
                        .statusCode(200)
                        .extract().path("data.short");
        return generatedShortLink;
    }

    /**
     * delete the link, that saved as static value
     */
    public static void deleteLink() {
        String shortLink = generatedShortLink;
        String codeOfShortLink = shortLink.replaceAll(".*/", "");

        given().log().all()
                .header("X-AUTH-TOKEN", getPropValue("X-AUTH-TOKEN"))
                .header("Content-Type", ContentType.JSON)
                .header("user-agent", PropertyHolder.getPropValue("USER_AGENT"))
        .when()
                .delete(getPropValue("LST_DELETE_LINK") + codeOfShortLink)
        .then()
                .statusCode(200).extract().response();

    }
}
