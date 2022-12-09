package com.portfioly.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.portfioly.Activity.LoginActivity;
import com.portfioly.R;
import com.portfioly.models.Mails;
import com.portfioly.models.StaticsData;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaticService {
    private static final String TAG = "statics";
    private Context context;
    RequestQueue queue;
    Map data;
    StaticsData statics;

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

    public StaticsData getstatics() {
        return statics;
    }

    public void setStatic(StaticsData statics) {
        this.statics = statics;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public StaticService(Context context)
    {
        this.context=context;
        queue = Volley.newRequestQueue(context);
    }

    public void getData(Map<String,String> params,VolleyCallBack callBack)
    {
        String url = context.getString(R.string.server)+"auth/user/statics/views";
        callBack.beforeSend();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    Type listType = new TypeToken<StaticsData>(){}.getType();
                    statics = new Gson().fromJson(response, listType);
                    callBack.onSuccess(statics);
                    callBack.onFinish();
                },
                error -> {
                    setStatic(null);
                    String body;
                    System.out.print(error);
                   try{
                       int statusCode = error.networkResponse.statusCode;
                       if(error.networkResponse.data!=null&&(statusCode == 405 )) {
                           if(statusCode==405)
                           {
                               context.startActivity(new Intent(context, LoginActivity.class));
                               ((Activity) context).finish();
                           }
                           else
                               Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                       }
                       else {
                           Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
                       }
                       callBack.onError(statusCode);
                       callBack.onFinish();
                   }
                   catch (Exception e){
                       Toast.error((Activity) getContext(),context.getString(R.string.err_500_title),context.getString(R.string.err_500_message));
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


}
