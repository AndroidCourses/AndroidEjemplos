package com.iwebsapp.ejemploandroid.uploadphoto.remote;

public class APIUtils {
    public APIUtils() {
    }

    private static final String API_URL = "https://axio-parners-dev.tk/v1/";

    public static FileService getFileService(){
        return RetrofitClient.getClient(API_URL).create(FileService.class);
    }
}
