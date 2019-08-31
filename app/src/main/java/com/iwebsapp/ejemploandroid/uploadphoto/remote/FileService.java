package com.iwebsapp.ejemploandroid.uploadphoto.remote;

import com.iwebsapp.ejemploandroid.uploadphoto.model.FileInfo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

    @Multipart
    @POST("user/avatar/5d52344440b9ff084bc70aa5")
    Call<FileInfo> upload(@Part MultipartBody.Part avatar);
}
