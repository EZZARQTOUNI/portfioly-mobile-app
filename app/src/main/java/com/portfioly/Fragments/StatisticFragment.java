package com.portfioly.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.button.MaterialButton;
import com.portfioly.Activity.WebViewActivity;
import com.portfioly.R;
import com.portfioly.Services.StaticService;
import com.portfioly.models.SimpleData;
import com.portfioly.models.StaticsData;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.VolleyCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticFragment extends Fragment {
    LineChart lineareChart;
    PieChart pieChart;
    StaticsData data;
    StaticService staticService;
    BarChart  countriesChart;
    LinearLayout loaderContainer;
    MaterialButton previewButton;
    PieChart platform_view;

    ArrayList<String> countryLabel =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineareChart = getView().findViewById(R.id.linear_chart);
        staticService = new StaticService(getContext());
        loaderContainer = getView().findViewById(R.id.loader_container);
        pieChart = getView().findViewById(R.id.pieChart_view);
        countriesChart = getView().findViewById(R.id.countries);
        String token = Helper.getToken(getContext());
        platform_view = getView().findViewById(R.id.platform_view);
        Map<String,String> m = new HashMap<>();
        m.put("token",token);
        initLineChart();
        Helper.initForumButton(getView().findViewById(R.id.groupe_button),getActivity());
        previewButton = getView().findViewById(R.id.preview_button);
        staticService.getData(m, new VolleyCallBack() {
            @Override
            public void onSuccess(Object o) {
                data = (StaticsData) o;
                setchartData((data.getViews()));
                showCountries(data.getBrowsersData());
                initBarChart(data.getCountry());
                showPlatformes(data.getPlarformData());
                loaderContainer.setVisibility(View.GONE);

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

    public ArrayList<Entry> getDataChart(List<Double> d){
        ArrayList<Entry> data = new ArrayList<Entry>();
        int n =d.size();
        if(n>0)
            for(int i =0;i<n;i++){
                double v  =d.get(i);
                data.add(new Entry(i,(float) v ));
            }
        return data;
    }

    public void initLineChart(){
        lineareChart.setBackgroundColor(Color.WHITE);
        lineareChart.getDescription().setEnabled(false);
        lineareChart.setDragEnabled(false);
        lineareChart.setScaleEnabled(false);
        lineareChart.setPinchZoom(false);
        lineareChart.setDrawGridBackground(false);
        lineareChart.setMaxHighlightDistance(250);
        XAxis x = lineareChart.getXAxis();
        //x.setEnabled(false);
        YAxis y = lineareChart.getAxisLeft();
        y.setTextColor(getResources().getColor(R.color.text_primary));
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        lineareChart.getAxisRight().setEnabled(false);
        lineareChart.getLegend().setEnabled(false);
        lineareChart.invalidate();
    }
    public void setchartData(ArrayList<SimpleData> views){
        ArrayList<Entry> d = new ArrayList<>();
        ArrayList<String> visitLabel = new ArrayList<>();
        int n = views.size();
        int j= 0;
        for(int i = n-1;i>-1;i--) {
            if(j==10) break;
            visitLabel.add(views.get(i).getName());
            d.add(new Entry(j,(float) views.get(i).getCount().floatValue()));
            j++;
        }
        lineareChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return visitLabel.get((int) value);
            }
        });

        LineDataSet set1;
        if (lineareChart.getData() != null &&
                lineareChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineareChart.getData().getDataSetByIndex(0);
            set1.setValues(d);

            lineareChart.getData().notifyDataChanged();
            lineareChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(d, "Views");
            setLineData(set1,getResources().getColor(R.color.primary));
        }
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(set1);

        LineData data = new LineData(lineDataSets);
        data.setDrawValues(false);
        lineareChart.setData(data);
        lineareChart.invalidate();

    }
    public  void initBarChart(ArrayList<SimpleData> d){
        ArrayList<BarEntry> entries = new ArrayList();
        int i = 0;
        for(SimpleData e : d){
            entries.add(new BarEntry(i, e.getCount().floatValue()));
            i++;
            countryLabel.add(e.getName());
        }
        XAxis xAxis = countriesChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return countryLabel.get((int) value);
            }
        });

        countriesChart.getXAxis().setLabelCount(12);
        countriesChart.getXAxis().setGranularityEnabled(true);
        countriesChart.getAxisRight().setEnabled(false);
        countriesChart.getAxisLeft().setEnabled(false);
        countriesChart.getDescription().setEnabled(true);
        countriesChart.setDrawValueAboveBar(false);
        countriesChart.enableScroll();

        BarDataSet set1 = new BarDataSet(entries, "Countries");
        //set1.setColors(Helper.generateColors());
        ArrayList<IBarDataSet> dSets = new ArrayList<>();
        dSets.add(set1);

        BarData data = new BarData(dSets);
        countriesChart.setData(data);
        countriesChart.invalidate();
        countriesChart.getXAxis().setDrawGridLines(false);
        countriesChart.getDescription().setEnabled(false);
        countriesChart.setNoDataTextColor(Color.parseColor("#0066ff"));
        YAxis axisLeft = countriesChart.getAxisLeft();
        axisLeft.setGranularity(10f);
        axisLeft.setAxisMinimum(0);

        YAxis axisRight = countriesChart.getAxisRight();
        axisRight.setGranularity(10f);
        axisRight.setAxisMinimum(0);
    }


    public void setLineData(LineDataSet setl,int a){
        setl.setDrawCircles(true);
        setl.setFillAlpha(0);
        setl.setCircleColor(getResources().getColor(R.color.white));
        setl.setCircleHoleColor(a);
        setl.setDrawHighlightIndicators(false);
        setl.setColor(a);
        setl.setDrawHorizontalHighlightIndicator(false);
        setl.setDrawValues(true);
        setl.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) { return lineareChart.getAxisLeft().getAxisMinimum(); }
        });
        setl.setFillColor(getResources().getColor(R.color.primary));
        setl.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        setl.setDrawFilled(true);
        setl.setLineWidth(2.8f);
    }
    private void showCountries(Map<String, Integer> d){
        String label = "Browsers";
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Integer> colors = Helper.generateColors(d.size());
        for(String type: d.keySet()){
            pieEntries.add(new PieEntry(d.get(type).floatValue(), type));
        }
        //pieChart.setDrawSliceText(false);
        pieChart.setDrawCenterText(false);
        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieChart.setUsePercentValues(false);
        //pieChart.setHoleColor(R.color.text_primary);
        //pieChart.setCenterTextColor(R.color.text_primary);
        //pieChart.setEntryLabelColor(R.color.text_primary);
        Description a = new Description();
        a.setText("");
        pieChart.setDescription(a);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void showPlatformes(Map<String, Integer> d){
        String label = "Platforms";
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Integer> colors = Helper.generateColors(d.size());
        for(String type: d.keySet()){
            pieEntries.add(new PieEntry(d.get(type).floatValue(), type));
        }
        platform_view.setDrawCenterText(false);
        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        platform_view.setUsePercentValues(false);

        Description a = new Description();
        a.setText("");
        platform_view.setDescription(a);
        platform_view.setData(pieData);
        platform_view.invalidate();
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