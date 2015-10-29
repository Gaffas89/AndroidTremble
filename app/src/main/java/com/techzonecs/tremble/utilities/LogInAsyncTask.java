//package com.techzonecs.tremble.utilities;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//
///**
// * Created by Bouyabes on 10/28/2015.
// * class to connect to the server with the post method;
// */
//public class LogInAsyncTask extends AsyncTask {
//
//    String password;
//    String sisid;
//
//    public LogInAsyncTask() {
//
//    }
//
//    public LogInAsyncTask(String sisid, String password) {
//
//        this.sisid = sisid;
//        this.password = password;
//
//        execute();
//
//    }
//
//    @Override
//    protected String doInBackground(Object[] params) {
//
//        try {
//
//            URI u=new URI(ConnectionURLString.url+"Login");
//            DefaultHttpClient client=new DefaultHttpClient();
//
//            HttpPost post = new HttpPost(u);
//
//            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
//            arrayList.add(new BasicNameValuePair("id_trainee", sisid));
//            arrayList.add(new BasicNameValuePair("password", password));
//
//            post.setEntity(new UrlEncodedFormEntity(arrayList));
//
//            HttpResponse response=client.execute(post);
//            HttpEntity entity=response.getEntity();
//            String data=EntityUtils.toString(entity);
//
////			    Log.d("test:", data);
//            return data;
//
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }  catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Object result) {
//        super.onPostExecute(result);
//
//        try {
//            JSONObject jsnobject = new JSONObject(result.toString());
//            JSONArray jsonArray = jsnobject.getJSONArray("result_data");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject tempObject = jsonArray.getJSONObject(i);
//                String logInFlag = tempObject.getString("flag");
//
//                Log.d("test:", logInFlag.toString());
//
//                boolean flag = Boolean.parseBoolean(logInFlag);
//                if (flag){
//                    Log.d("RESULT FLAG", "TRUE!!!");
//                }else {
//                    Log.d("RESULT FLAG", "False.");
//                }
//            }
//        }catch (JSONException e){
//
//        }
//
//    }
//}
