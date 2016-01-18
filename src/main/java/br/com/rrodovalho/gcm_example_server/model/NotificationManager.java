package br.com.rrodovalho.gcm_example_server.model;

import br.com.rrodovalho.gcm_example_server.domain.PushMessageContent;
import br.com.rrodovalho.gcm_example_server.domain.PushMessageResponse;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rrodovalho on 17/01/16.
 */
public class NotificationManager {

    private PushMessageContent mPayload;
    private String mAPI_Key;
    public static final String GCM_API_URL="https://android.googleapis.com/gcm/send";
    public static final int SUCCESSFUL_SEND_CODE=200;


    public NotificationManager(PushMessageContent payload, String API_Key) {
        mPayload = payload;
        mAPI_Key = API_Key;
    }

    public PushMessageContent getPayload() {
        return mPayload;
    }

    public void setPayload(PushMessageContent payload) {
        mPayload = payload;
    }

    public String getAPI_Key() {
        return mAPI_Key;
    }

    public void setAPI_Key(String API_Key) {
        mAPI_Key = API_Key;
    }

    //Ref - https://developers.google.com/cloud-messaging/http-server-ref
    public void analyseGCMResponse(String response){

       PushMessageResponse pushMessageResponse = new Gson().fromJson(response,PushMessageResponse.class);

       if(pushMessageResponse.getCanonical_ids()>0 || pushMessageResponse.getFailure()>0){

           int index = 0;
           for (Map result:pushMessageResponse.getResults()) {

               if(result.containsKey("registration_id")){

                    String newRegistrationID = result.get("registration_id").toString();
                    //TODO make this call works
                   userDAO.updateUser(newRegistrationID,mPayload.getRegistration_ids().get(index));

               }
               else {
                   if(result.containsKey("error")){
                        if(result.get("error").toString().equals("Not Registered")){

                            //TODO make this call works
                            userDao.deleteUser(mPayload.getRegistration_ids().get(index));

                        }
                   }
               }
                ++index;
           }
       }

    }

    public void sendNotification(){

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","key="+ mAPI_Key);

        String json = new Gson().toJson(mPayload);

        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(GCM_API_URL)
                    .headers(headers)
                    .body(json)
                    .asJson();

            if(jsonResponse.getStatus()==SUCCESSFUL_SEND_CODE){

                analyseGCMResponse(jsonResponse.getBody().toString());

            }


        } catch (UnirestException e) {
            e.printStackTrace();
        }

        System.out.println("Status  "+jsonResponse.getStatus());
        System.out.println("Status Next   " +jsonResponse.getStatusText());
        System.out.println("Body   " +jsonResponse.getBody());
        System.out.println("Raw Body   " +jsonResponse.getRawBody());

        /*Future<HttpResponse<JsonNode>> jsonResponse = Unirest.post(GCM_API_URL)
                .headers(headers)
                .body(json)
                .asJsonAsync(new Callback<JsonNode>() {

                public void failed(UnirestException e) {
                    System.out.println("The request has failed");
                }

                public void completed(HttpResponse<JsonNode> response) {

                    analyseGCMResponse(response);
                }

                public void cancelled() {
                    System.out.println("The request has been cancelled");
                }
            });*/

    }
}
