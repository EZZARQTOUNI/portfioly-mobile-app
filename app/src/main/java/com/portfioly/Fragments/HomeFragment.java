package com.portfioly.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.Activity.ViewActivity;
import com.portfioly.Activity.VisitsActivity;
import com.portfioly.Activity.WebViewActivity;
import com.portfioly.R;
import com.portfioly.Services.HomeService;
import com.portfioly.models.HomeStatics;
import com.portfioly.models.User;
import com.portfioly.models.Visit;
import com.portfioly.utils.Flags;
import com.portfioly.utils.Helper;
import com.portfioly.utils.VolleyCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment {
    LinearLayout views_contaienr;
    MaterialButton previewButton;
    HomeService homeService;
    ScrollView scrollContainer;
    LinearLayout loaderContainer;
    TextView viewsNumber;
    TextView worksNumber;
    TextView skillsNumber;
    TextView servicesNumber;
    TextView allVisits;
    SwipeRefreshLayout RefreshData;
    LinearLayout no_data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Helper.initForumButton(getView().findViewById(R.id.groupe_button),getActivity());
        homeService = new HomeService(getContext());
        views_contaienr = getView().findViewById(R.id.views_container);
        previewButton = getView().findViewById(R.id.preview_button);
        loaderContainer = getView().findViewById(R.id.loader_container);
        scrollContainer = getView().findViewById(R.id.scroll_container);
        viewsNumber = getView().findViewById(R.id.views_number);
        worksNumber = getView().findViewById(R.id.works_number);
        servicesNumber = getView().findViewById(R.id.services_number);
        skillsNumber = getView().findViewById(R.id.skills_number);
        allVisits = getView().findViewById(R.id.all_visits);
        RefreshData = getView().findViewById(R.id.refresh_element);
        no_data = getView().findViewById(R.id.no_data);


        homeService = new HomeService(getContext());
        getData();
        RefreshData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshData();
            }
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

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getData(){
        String token = Helper.getToken(getContext());
        Map<String,String> data = new HashMap<String,String>();
        data.put("token",token);
        homeService.getData(data, new VolleyCallBack() {
            @Override
            public void onSuccess(Object o) {
                Helper.setTimout(()->{
                    HomeStatics h = (HomeStatics) o;
                    int i = 0;
                    for(Visit v : h.getVisits()){
                        if(i==8) break;
                        createViewElement(v);
                        i++;
                    }
                    if(i==0){
                        allVisits.setVisibility(View.GONE);
                        no_data.setVisibility(View.VISIBLE);
                    }
                    else{
                        allVisits.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);
                    }

                    viewsNumber.setText(""+h.getViews());
                    servicesNumber.setText(""+h.getServices());
                    skillsNumber.setText(""+h.getServices());
                    worksNumber.setText(""+h.getWorks());
                    allVisits.setOnClickListener(e->{
                        Intent j = new Intent(getActivity(), VisitsActivity.class);
                        j.putExtra("visits",h.getVisits());
                        startActivity(j);
                    });
                    loaderContainer.setVisibility(View.GONE);
                    scrollContainer.fullScroll(ScrollView.FOCUS_UP);

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
    public void RefreshData(){
        String token = Helper.getToken(getContext());
        Map<String,String> data = new HashMap<String,String>();
        data.put("token",token);
        homeService.getData(data, new VolleyCallBack() {
            @Override
            public void onSuccess(Object o) {
                Helper.setTimout(()->{
                    Helper.setTimout(()->{views_contaienr.removeAllViews();},100);
                    Helper.setTimout(()->{
                        HomeStatics h = (HomeStatics) o;
                        int i = 0;
                        for(Visit v : h.getVisits()){
                            if(i==8) break;
                            createViewElement(v);
                            i++;
                        }
                        viewsNumber.setText(""+h.getViews());
                        servicesNumber.setText(""+h.getServices());
                        skillsNumber.setText(""+h.getServices());
                        worksNumber.setText(""+h.getWorks());
                        allVisits.setOnClickListener(e->{
                            Intent j = new Intent(getActivity(), VisitsActivity.class);
                            j.putExtra("visits",h.getVisits());
                            startActivity(j);
                        });
                        loaderContainer.setVisibility(View.GONE);
                        RefreshData.setRefreshing(false);
                    },150);


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

    public void createViewElement(Visit d){
        LinearLayout viewElement = (LinearLayout) getLayoutInflater().inflate(R.layout.view_element,null);
        ((TextView) viewElement.findViewById(R.id.title_elment)).setText(("FROM "+d.getCountry()).toUpperCase(Locale.ROOT));
        ((TextView) viewElement.findViewById(R.id.time_element)).setText(((d.getDate()).replace(",","")).replace(" PM","PM"));
        ((ImageView) viewElement.findViewById(R.id.flag_element)).setImageResource(Flags.getFlag(getContext(),d.getCountyCode().toLowerCase()));
        ((MaterialButton) viewElement.findViewById(R.id.view_button)).setOnClickListener(e->{
            Intent i =  new Intent(getActivity(), ViewActivity.class);
            i.putExtra("visit", (Serializable) d);
            getActivity().startActivity(i);
        });
        views_contaienr.addView(viewElement);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}