package com.example.musicapp.Services;

public class APIService {

    //private static String base_url = "https://dungnbn.000webhostapp.com/server/";
    private static String base_url = "https://file-ma.000webhostapp.com/server/";

    public static Dataservice getService() {
        return APIREtrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
