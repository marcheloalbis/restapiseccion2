package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestTokenPUT implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response=given()
                            .header("Token", ConfigEnv.token)
                            .body(information.getBody())
                            .log()
                            .all()
                          .when()
                            .put(information.getUrl());

        response.then()
                .log()
                .all();

        return response;
    }
}
