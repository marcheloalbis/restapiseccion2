package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import util.ConfigAPI;
import util.GetProperties;
import util.SetProperties;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserTest {
    @Before
    public void before() throws IOException {
        new SetProperties().guardarPropiedades("marcelo12345@marcelo.com","12345");
        new GetProperties().leerPropiedades();
    }

    @Test
    public void verifyCRUDforProject(){
        JSONObject body = new JSONObject();
        body.put("FullName","Marcelo");
        body.put("Email","marcelo12345@marcelo.com");
        body.put("Password","12345");

        //create
        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_USER,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Email", equalTo("marcelo12345@marcelo.com"));
        String id = response.then().extract().path("Id")+"";



        //update
        request = new RequestInformation(ConfigAPI.UPDATE_USER.replace("ID",id),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);
        response.then()
                .statusCode(200)
                .body("Email", equalTo("marcelo12345@marcelo.com"));


    }
}
