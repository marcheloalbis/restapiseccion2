package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestTokenDELETE implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response=given()
                            .header("Token", ConfigEnv.token)
                            .log()
                            .all()
                          .when()
                            .delete(information.getUrl());

        response.then()
                .log()
                .all();
        return response;
    }
}
