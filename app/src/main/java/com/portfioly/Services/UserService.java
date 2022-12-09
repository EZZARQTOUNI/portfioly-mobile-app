package com.portfioly.Services;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;
import com.portfioly.R;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final String TAG = "user";
    private Context context;
    RequestQueue queue;
    Map data;
    User user;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public Map getData() {
        return data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public UserService(Context context)
    {
        this.context=context;
        queue = Volley.newRequestQueue(context);
    }

    public void Login(Map<String,String> params,VolleyCallBack callBack)
    {
        String url = context.getString(R.string.server)+"auth/login";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    Type listType = new TypeToken<User>(){}.getType();
                    user = new Gson().fromJson(response, listType);
                    callBack.onSuccess(user);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 405||statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),"Email or Password is incorrect, Please verify you data");

                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);

        queue.add(stringRequest);

    }

    public void checkUserByToken(Map<String,String> params,VolleyCallBack callBack)
    {
        String url = context.getString(R.string.server)+"auth/check/user";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    Type listType = new TypeToken<User>(){}.getType();
                    user = new Gson().fromJson(response, listType);
                    callBack.onSuccess(user);
                    user.setMobileToken(params.get("token"));
                    Helper.saveUser(getContext(),user);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    try {
                        int statusCode = error.networkResponse.statusCode;
                       // Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                        callBack.onError(statusCode);
                        callBack.onFinish();
                    }
                    catch (Exception e){
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                        callBack.onError(500);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);


        queue.add(stringRequest);

    }
    public void updateProfile(Map<String,String> params,VolleyCallBack callBack)
    {
        String url = context.getString(R.string.server)+"auth/user";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 405||statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),"Some of your fields is incorrect, please check you data!");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public void changePassword(Map<String,String> params,VolleyCallBack callBack) {
        String url = context.getString(R.string.server)+"auth/user/change-password";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 405||statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),"Current password is Incorrect, please check your password");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public void RequestCodeForResset(Map<String,String> params,VolleyCallBack callBack) {
        String url = "https://portfioly.net/api/users/reset-password/send";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),"Invalid Email","Please recheck the email that you provided and resend it");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public void RessetPassword(Map<String,String> params,VolleyCallBack callBack) {
        String url = "https://portfioly.net/api/users/reset-password";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),"Invalid Code","Please Provide a valide code, check your email, we send to you a 6 digits number code");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public void SendMessageOrRequest(Map<String,String> params,VolleyCallBack callBack) {
        String url = "http://portfioly.net/api/user/message";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),"Invalid Code","Please Provide a valide code, check your email, we send to you a 6 digits number code");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }
    public void NewsLetter(Map<String,String> params,VolleyCallBack callBack) {
        String url = "http://portfioly.net/api/user/newsletter";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    callBack.onSuccess(true);
                    callBack.onFinish();
                },
                error -> {
                    setUser(null);
                    String body;
                    System.out.print(error);
                    int statusCode = error.networkResponse.statusCode;
                    if(error.networkResponse.data!=null&&(statusCode == 403 )) {
                        if(statusCode==403)
                        {
                            Toast.error((Activity) getContext(),"Invalid Code","Please Provide a valide code, check your email, we send to you a 6 digits number code");
                        }
                        else
                            Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    else {
                        Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                    }
                    callBack.onError(statusCode);
                    callBack.onFinish();
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

}
