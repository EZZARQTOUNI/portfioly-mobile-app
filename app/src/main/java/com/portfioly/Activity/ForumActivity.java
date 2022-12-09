package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.portfioly.R;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.WebSocketCallBack;
import com.portfioly.utils.WebSocketClient;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class ForumActivity extends AppCompatActivity {
    WebSocket ws;
    LinearLayout loaderContainer;
    LinearLayout messagesContainer;
    MaterialButton sendMessage;
    EditText inputMessage;
    ScrollView scrollContainer;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        loaderContainer = findViewById(R.id.loader_container);
        messagesContainer = findViewById(R.id.messages_container);
        sendMessage = findViewById(R.id.send_message);
        inputMessage = findViewById(R.id.input_Message);
        scrollContainer = findViewById(R.id.scroll_container);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});

        checkWeb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendMessage.setOnClickListener(e->{
            String message = inputMessage.getText().toString();
            if(message.length()>0) sendMessage(message);
        });

    }
    public void checkWeb(){
        User user = Helper.getUser(getBaseContext());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://95.111.231.204:4001/?user="+user.getUsername()+"&&token="+user.getMobileToken()+"&&chat=forum").build();
        WebSocketClient listener = new WebSocketClient(new WebSocketCallBack() {
            @Override
            public void onOpen() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Helper.setTimout(()->{loaderContainer.setVisibility(View.GONE);},3000);
                    }
                });

            }

            @Override
            public void onMessage(String text) {showResponseMessage(text);}

            @Override
            public void onClosing() {}
        });

        ws = client.newWebSocket(request, listener);

    }

    public void  showResponseMessage(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map data = ( new Gson()).fromJson(text, Map.class);
                LinearLayout RE = (LinearLayout) getLayoutInflater().inflate(R.layout.message_type_response,null);
                TextView username = ((TextView) RE.findViewById(R.id.username));
                username.setText("~"+((String) data.get("user")));
                username.setOnClickListener(e->{
                    Intent i = new Intent(ForumActivity.this,WebViewActivity.class);
                    i.putExtra("title", (String) data.get("user"));
                    i.putExtra("url", (String) data.get("user"));
                    startActivity(i);
                });
                ((TextView) RE.findViewById(R.id.message)).setText(((String) data.get("message")));
                messagesContainer.addView(RE);
                scrollToBottom();
            }
        });
    }

    public void  sendMessage(String message){
        ws.send(message);
        LinearLayout RE = (LinearLayout) getLayoutInflater().inflate(R.layout.message_type1,null);
        ((TextView) RE.findViewById(R.id.message)).setText(message);
        messagesContainer.addView(RE);
        inputMessage.setText("");
        scrollToBottom();
    }

    public void scrollToBottom(){

        scrollContainer.post(new Runnable() {
            @Override
            public void run() {
                scrollContainer.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}


