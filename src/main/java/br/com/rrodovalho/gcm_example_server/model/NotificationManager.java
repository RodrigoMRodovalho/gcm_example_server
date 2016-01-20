package br.com.rrodovalho.gcm_example_server.model;

import br.com.rrodovalho.gcm_example_server.domain.PushMessageContent;
import br.com.rrodovalho.gcm_example_server.domain.PushMessageResponse;
import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.service.UserService;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rrodovalho on 17/01/16.
 */
@Data
@AllArgsConstructor
public class NotificationManager {


    private UserService mUserDAO;
    private PushMessageContent mPayload;
    private String mAPI_Key;
    public static final String GCM_API_URL="https://android.googleapis.com/gcm/send";
    public static final int SUCCESSFUL_SEND_CODE=200;

    //Ref - https://developers.google.com/cloud-messaging/http-server-ref
    public void analyseGCMResponse(String response){

       PushMessageResponse pushMessageResponse = new Gson().fromJson(response,PushMessageResponse.class);

       if(pushMessageResponse.getCanonical_ids()>0 || pushMessageResponse.getFailure()>0){

           int index = 0;
           int rowsAffected = 0;
           for (Map result:pushMessageResponse.getResults()) {

               if(result.containsKey("registration_id")){

                    String newRegistrationID = result.get("registration_id").toString();
                    rowsAffected = mUserDAO.updateUserByRegistrationID(newRegistrationID,mPayload.getRegistration_ids().get(index));
                    if(rowsAffected>0){
                        System.out.println("User updated");
                    }

               }
               else {
                   if(result.containsKey("error")){
                        if(result.get("error").toString().equals("NotRegistered")){
                            String regID = mPayload.getRegistration_ids().get(index);
                            rowsAffected = mUserDAO.deleteUserByRegistrationID(regID);
                            if(rowsAffected>0){
                                System.out.println("User Deleted");
                            }

                        }
                   }
               }
                ++index;
           }
       }

    }

    public String sendNotification(){

        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","key="+ mAPI_Key);

        String json = new Gson().toJson(mPayload);
        System.out.println(json);
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(GCM_API_URL)
                    .headers(headers)
                    .body(json)
                    .asJson();

            if(jsonResponse.getStatus()==SUCCESSFUL_SEND_CODE){

                analyseGCMResponse(jsonResponse.getBody().toString());
                return jsonResponse.getBody().toString();

            }


        } catch (UnirestException e) {
            e.printStackTrace();
        }

        /*System.out.println("Status  "+jsonResponse.getStatus());
        System.out.println("Status Next   " +jsonResponse.getStatusText());
        System.out.println("Body   " +jsonResponse.getBody());
        System.out.println("Raw Body   " +jsonResponse.getRawBody());*/

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
        return null;
    }
}
