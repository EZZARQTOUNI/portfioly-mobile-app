package com.portfioly.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.Activity.AboutActivity;
import com.portfioly.Activity.ContactUsActivity;
import com.portfioly.Activity.EditPasswordActivity;
import com.portfioly.Activity.EditProfileActivity;
import com.portfioly.Activity.FeedBackActivity;
import com.portfioly.Activity.LoginActivity;
import com.portfioly.Activity.MailNewsLetterActivity;
import com.portfioly.Activity.MySubscriptionActivity;
import com.portfioly.Activity.StorageActivity;
import com.portfioly.Activity.SubscriptionsActivity;
import com.portfioly.Activity.WebViewActivity;
import com.portfioly.R;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;

import java.util.Locale;

public class ProfilFragment extends Fragment {
    User user;
    TextView fullName;
    TextView userName;
    MaterialButton editProfile;
    LinearLayout update_pr;
    LinearLayout my_portfolio;
    LinearLayout change_password;
    LinearLayout online_services;
    LinearLayout send_feedback;
    LinearLayout subscribe;
    LinearLayout about;
    CardView storage;
    CardView subscribtion;
    MaterialButton previewButton;
    LinearLayout logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = Helper.getUser(getContext());
        fullName = getView().findViewById(R.id.full_name);
        userName = getView().findViewById(R.id.username);
        Helper.initForumButton(getView().findViewById(R.id.groupe_button),getActivity());
        previewButton = getView().findViewById(R.id.preview_button);
        fullName.setText((user.getFirstname()+" "+user.getLastname()).toUpperCase(Locale.ROOT));
        userName.setText("@"+user.getUsername());
        editProfile = getView().findViewById(R.id.edit_profile);
        update_pr = getView().findViewById(R.id.update_pr);
        my_portfolio = getView().findViewById(R.id.my_portfolio);
        change_password = getView().findViewById(R.id.change_password);
        online_services = getView().findViewById(R.id.online_services);
        send_feedback = getView().findViewById(R.id.send_feedback);
        subscribe = getView().findViewById(R.id.subscribe);
        about = getView().findViewById(R.id.about);
        storage = getView().findViewById(R.id.storage);
        subscribtion = getView().findViewById(R.id.subscribtion);
        logout = getView().findViewById(R.id.logout);

        logout.setOnClickListener(e->{
            Helper.removeUser(getContext());
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        });
        storage.setOnClickListener(e->{
            Intent i = new Intent(getContext(), StorageActivity.class);
            startActivity(i);
        });
        subscribtion.setOnClickListener(e->{
            Intent i = new Intent(getContext(), MySubscriptionActivity.class);
            startActivity(i);
        });
        about.setOnClickListener(e->{
            Intent i = new Intent(getContext(), AboutActivity.class);
            startActivity(i);
        });
        subscribe.setOnClickListener(e->{
            Intent i = new Intent(getContext(), MailNewsLetterActivity.class);
            startActivity(i);
        });
        send_feedback.setOnClickListener(e->{
            Intent i = new Intent(getContext(), FeedBackActivity.class);
            startActivity(i);
        });
        online_services.setOnClickListener(e->{
            Intent i = new Intent(getContext(), ContactUsActivity.class);
            startActivity(i);
        });
        editProfile.setOnClickListener(e->{
            Intent i = new Intent(getContext(), EditProfileActivity.class);
            startActivity(i);
        });
        update_pr.setOnClickListener(e->{
            Intent i = new Intent(getContext(), EditProfileActivity.class);
            startActivity(i);
        });
        my_portfolio.setOnClickListener(e->{
            Intent i = new Intent(getActivity(), WebViewActivity.class);
            i.putExtra("title",user.getFirstname()+" "+user.getLastname());
            i.putExtra("url",user.getUsername());
            startActivity(i);
        });
        change_password.setOnClickListener(e->{
            startActivity(new Intent(getContext(), EditPasswordActivity.class));
        });
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