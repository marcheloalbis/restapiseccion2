package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import util.ConfigAPI;
import util.GetProperties;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;


public class CRUDItemTest {

    @Before
    public void before() throws IOException {
        new GetProperties().leerPropiedades();
    }

    @Test
    public void verifyCRUDforProject(){
        JSONObject body = new JSONObject();
        body.put("Content","Marcelo");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_ITEM,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));
        String id = response.then().extract().path("Id")+"";

        request = new RequestInformation(ConfigAPI.UPDATE_ITEM.replace("ID",id),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));

        request = new RequestInformation(ConfigAPI.READ_ITEM.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.GET).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));

        request = new RequestInformation(ConfigAPI.DELETE_ITEM.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.DELETE).send(request);

        response.then()
                .statusCode(200)
                .body("Deleted", equalTo(true));

    }

}
