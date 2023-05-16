package com.goit.status.app;

import com.goit.status.exception.StatusInternalErrorException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpStatusChecker {
    public static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code) throws IOException {
        String url = BASE_URL + code + ".jpg";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return url;
            } else {
                throw new StatusInternalErrorException("Failed to fetch image. HTTP status code: " + response.code());
            }
        }
    }
}
