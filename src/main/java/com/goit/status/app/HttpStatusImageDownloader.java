package com.goit.status.app;

import com.goit.status.app.HttpStatusChecker;
import com.goit.status.exception.StatusInternalErrorException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HttpStatusImageDownloader {
    public void downloadStatusImage(int code) throws IOException {
        HttpStatusChecker checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(code);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String fileName = code + ".jpg";
                    try (BufferedInputStream inputStream = new BufferedInputStream(responseBody.byteStream());
                         BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        System.out.println("Image downloaded: " + fileName);
                    }
                }
            } else {
                throw new StatusInternalErrorException("Failed to download image. HTTP status code: " + response.code());
            }
        }
    }
}
