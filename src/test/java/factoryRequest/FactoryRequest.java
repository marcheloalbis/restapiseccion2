package factoryRequest;

public class FactoryRequest {

    public static final String GET ="get";
    public static final String POST ="post";
    public static final String PUT ="put";
    public static final String DELETE ="delete";
    public static final String GETt ="gett";
    public static final String POSTt ="postt";
    public static final String PUTt ="putt";
    public static final String DELETEt ="deletet";

    public static IRequest make (String typeRequest){
         IRequest request;

         switch (typeRequest){
             case GET:
                 request = new RequestGET();
                 break;
             case DELETE:
                 request = new RequestDELETE();
                 break;
             case POST:
                 request = new RequestPOST();
                 break;
             case PUT:
                 request = new RequestPUT();
                 break;
             case GETt:
                 request = new RequestTokenGET();
                 break;
             case DELETEt:
                 request = new RequestTokenDELETE();
                 break;
             case POSTt:
                 request = new RequestTokenPOST();
                 break;
             case PUTt:
                 request = new RequestTokenPUT();
                 break;
             default:
                 request = new RequestGET();
                 break;

         }
        return request;
    }

}
