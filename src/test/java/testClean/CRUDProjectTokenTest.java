package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import util.ConfigAPI;
import util.ConfigEnv;
import util.GetProperties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.*;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.equalTo;


public class CRUDProjectTokenTest {

    @Before
    public void before() throws IOException {
        new GetProperties().leerPropiedades();
    }

    @Test
    public void verifyCRUDforProject()  throws IOException{
        JSONObject body = new JSONObject();
        body.put("Content","Marcelo");
        body.put("Icon",3);

        //obtener token
        RequestInformation request = new RequestInformation(ConfigAPI.GET_TOKEN,"");
        Response response = FactoryRequest.make(FactoryRequest.GET).send(request);
        response.then()
                .statusCode(200)
                .body("UserEmail", equalTo("marcelo12345@marcelo.com"));

        String token = response.then().extract().path("TokenString")+"";

        //set token en variable para utilizar en auth2
        Properties properties = new Properties();
        FileOutputStream fileOutputStream = new FileOutputStream("token.properties");
        properties.setProperty("token", token);
        properties.store(fileOutputStream,"Sample way of creating Properties file from Java program");
        ConfigEnv.token=properties.getProperty("token");
        fileOutputStream.close();

        //preguntar si esta autenticado
        request = new RequestInformation(ConfigAPI.GET_AUTH,"");
        response = FactoryRequest.make(FactoryRequest.GET).send(request);
        response.then()
                .statusCode(200);

        //crear proyecto con token
        request = new RequestInformation(ConfigAPI.CREATE_PROJECT,body.toString());
        response = FactoryRequest.make(FactoryRequest.POSTt).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));
        String id = response.then().extract().path("Id")+"";

        //update proyecto
        request = new RequestInformation(ConfigAPI.UPDATE_PROJECT.replace("ID",id),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUTt).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));

        //leer proyecto
        request = new RequestInformation(ConfigAPI.READ_PROJECT.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.GETt).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Marcelo"));

        //delete proyecto con token
        request = new RequestInformation(ConfigAPI.DELETE_PROJECT.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.DELETEt).send(request);

        response.then()
                .statusCode(200)
                .body("Deleted", equalTo(true));



    }

}
