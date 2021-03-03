package com.marufh.emailclient.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class EmailClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String BASE_URL = "http://localhost:6060/api/send/mail/";
    private static final String GCLOUD_URL = "gcloud-pubsub";
    private static final String AWS_URL = "aws-sqs";

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmailToGcloudPubSub(BaseEmail baseEmail)  {
        log.info("sending email using gcloud pubsub");
        RequestBody body = null;
        Request request = null;
        try {
            body = RequestBody.create(objectMapper.writeValueAsString(baseEmail), JSON);
            request = new Request.Builder()
                    .url(BASE_URL + GCLOUD_URL)
                    .post(body)
                    .build();
            client.newCall(request).execute();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailToAwsSqs(BaseEmail baseEmail)  {
        log.info("sending email using sqs");
        RequestBody body = null;
        Request request = null;

        try {
            String json = objectMapper.writeValueAsString(baseEmail);
            log.info(json);
            body = RequestBody.create(json, JSON);
            request = new Request.Builder()
                    .url(BASE_URL + AWS_URL)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            log.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
