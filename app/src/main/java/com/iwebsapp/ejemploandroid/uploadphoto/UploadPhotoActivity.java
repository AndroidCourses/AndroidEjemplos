package com.iwebsapp.ejemploandroid.uploadphoto;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.uploadphoto.model.FileInfo;
import com.iwebsapp.ejemploandroid.uploadphoto.remote.APIUtils;
import com.iwebsapp.ejemploandroid.uploadphoto.remote.FileService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPhotoActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FileService fileService;
    Button btnUpload, btnChooseFile;
    String imagePath;
    ImageView imgProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        fileService = APIUtils.getFileService();

        btnChooseFile = findViewById(R.id.btnChooseFile);
        btnUpload = findViewById(R.id.btnUpload);
        imgProfile = findViewById(R.id.imgProfile);

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(imagePath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);

                Call<FileInfo> call = fileService.upload(body);
                call.enqueue(new Callback<FileInfo>() {
                    @Override
                    public void onResponse(@Nullable Call<FileInfo> call, @Nullable Response<FileInfo> response) {
                        if (response != null) {
                            if (response.isSuccessful()){
                                Log.d(TAG, "carga exitosa: " + response.message());
                            }else {
                                Log.d(TAG, "Error:  " + response.errorBody());
                            }
                        }
                    }
                    @Override
                    public void onFailure(@Nullable Call<FileInfo> call, @Nullable Throwable t) {
                        if (t != null) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    }
                });

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //imgavatar.setImageURI(miPath);
        if (resultCode == RESULT_OK){
            if (data == null){
                Toast.makeText(this, "Unable to choose image", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri imageUri = data.getData();
            imagePath = getRealmPathFromUri(imageUri);
            imgProfile.setImageURI(imageUri);
        }
    }

    private String getRealmPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

}
