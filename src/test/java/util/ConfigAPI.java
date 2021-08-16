package util;

public class ConfigAPI {

    public static final String CREATE_USER=ConfigEnv.host+"/api/user.json";
    public static final String UPDATE_USER=ConfigEnv.host+"/api/user/ID.json";

    public static final String GET_TOKEN=ConfigEnv.host+"/api/authentication/token.json";
    public static final String GET_AUTH=ConfigEnv.host+"/api/authentication/isauthenticated.json";

    public static final String CREATE_PROJECT=ConfigEnv.host+"/api/projects.json";
    public static final String UPDATE_PROJECT=ConfigEnv.host+"/api/projects/ID.json";
    public static final String READ_PROJECT=ConfigEnv.host+"/api/projects/ID.json";
    public static final String DELETE_PROJECT=ConfigEnv.host+"/api/projects/ID.json";


    public static final String CREATE_ITEM=ConfigEnv.host+"/api/items.json";
    public static final String UPDATE_ITEM=ConfigEnv.host+"/api/items/ID.json";
    public static final String READ_ITEM=ConfigEnv.host+"/api/items/ID.json";
    public static final String DELETE_ITEM=ConfigEnv.host+"/api/items/ID.json";



}
