package com.portfioly.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.Activity.MailElementActivity;
import com.portfioly.Activity.WebViewActivity;
import com.portfioly.R;
import com.portfioly.Services.EmailService;
import com.portfioly.models.Mails;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.VolleyCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MailsFragment extends Fragment {
    LinearLayout loaderContainer;
    LinearLayout mailsContainer;
    EmailService emailService;
    String[] colorPrimary = {"#2196f3","#00bcd4","#009688","#fff3e0"};
    String[] colorSecon = {"#e3f2fd","#e0f7fa","#e3f2fd","#ff9800"};
    MaterialButton previewButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mails, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loaderContainer = getView().findViewById(R.id.loader_container);
        mailsContainer = getView().findViewById(R.id.mails_container);
        emailService = new EmailService(getContext());
        Helper.initForumButton(getView().findViewById(R.id.groupe_button),getActivity());
        previewButton = getView().findViewById(R.id.preview_button);
        getData();

    }

    public void getData(){
        String token = Helper.getToken(getContext());
        Map<String,String> data = new HashMap<String,String>();
        data.put("token",token);
        emailService.getData(data, new VolleyCallBack() {
            @Override
            public void onSuccess(Object o) {
                Helper.setTimout(()->{
                    ArrayList<Mails> m = (ArrayList<Mails>) o;
                    int i = 0;
                    for(Mails v :m){
                        if(i==8) break;
                        createViewElement(v,i%4);
                        i++;
                    }
                    loaderContainer.setVisibility(View.GONE);

                },800);
            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void beforeSend() {

            }

            @Override
            public void onFinish() {

            }

        });
    }
    public void createViewElement(Mails d,int i){
        LinearLayout viewElement = (LinearLayout) getLayoutInflater().inflate(R.layout.mail_element,null);
        ((TextView) viewElement.findViewById(R.id.full_name)).setText((""+d.getName()));
        ((TextView) viewElement.findViewById(R.id.subject)).setText((""+d.getSubject()));
        ((TextView) viewElement.findViewById(R.id.date)).setText(((d.getTime()).replace(",","")).replace(" PM","PM").replace(" AM","AM"));
        String Message =  d.getMessage();
        if(Message.length()>70)
            Message = Message.substring(0,66) + " ...";
        MaterialButton mb = viewElement.findViewById(R.id.email_button);
        mb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorSecon[i])));
        mb.setIconTint(ColorStateList.valueOf(Color.parseColor(colorPrimary[i])));
        ((TextView) viewElement.findViewById(R.id.message)).setText((Message));
        ((MaterialButton) viewElement.findViewById(R.id.show_mail)).setOnClickListener(e->{
            Intent intent =  new Intent(getActivity(), MailElementActivity.class);
            intent.putExtra("mail", (Serializable) d);
            getActivity().startActivity(intent);
        });

        mailsContainer.addView(viewElement);
    }

    @Override
    public void onStart() {
        super.onStart();

        previewButton.setOnClickListener(e->{
            User u = Helper.getUser(getContext());
            Intent i = new Intent(getActivity(), WebViewActivity.class);
            i.putExtra("title",u.getFirstname()+" "+u.getLastname());
            i.putExtra("url",u.getUsername());
            startActivity(i);
        });

    }


}