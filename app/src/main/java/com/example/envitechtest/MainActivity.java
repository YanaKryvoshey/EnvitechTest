package com.example.envitechtest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
private ListView monitor_type_list_View;
private ListView monitor_list_view;
private FragmentContainerView fragment_container_view;

private Dialog mdialog;

    private String url = "https://3294c784-38b0-494b-96ee-d933fa6d7808.mock.pstmn.io/config";
    private ProgressDialog dialog;
    private ArrayList<monitor> monitorList;
    private  ArrayList<legends> legendsTypeList;
    private ArrayList<monitorType> monitorTypeList;
    private int firstposition,secondposition;


    // private Spinner main_MonitorType_Spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

mdialog = new Dialog(this);
        
        
        
        findViews();


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
        monitor_type_list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstposition = position;
                Toast.makeText(getApplicationContext(), monitorTypeList.get(position).Name, Toast.LENGTH_SHORT).show();
                showMonitorList(monitorTypeList.get(position));
            }
        });

        monitor_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondposition = position;
                showLegends();


            }
        });
    }

    private void showLegends() {
        ArrayList al = new ArrayList();

        for(int i = 0; i < legendsTypeList.size(); ++i) {

            if (legendsTypeList.get(i).Id == monitorTypeList.get(firstposition).LegendId){
                //legendsTypeList.get(i).getTags();
                openTagsFragment(new TagsFragment(legendsTypeList.get(i).tags));
            }
        }

    }

    private void openTagsFragment(TagsFragment tagsFragment) {


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_layout,tagsFragment);
fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void showMonitorList(monitorType monitorType) {
        ArrayList al = new ArrayList();

        for(int i = 0; i < monitorList.size(); ++i) {

            if (monitorList.get(i).MonitorTypeId == monitorType.Id){
                al.add(monitorList.get(i).Name);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
        monitor_list_view.setAdapter(adapter);

    }



    private void findViews() {

        monitor_type_list_View =  findViewById(R.id.monitor_type_list_view);
        monitor_list_view = findViewById(R.id.monitor_list_view);

    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);

            Log.d("pttt",object.toString());


            JSONArray predsJsonArray = object.getJSONArray("MonitorType");
            getMonitorTypeFromJson(predsJsonArray);

            predsJsonArray = object.getJSONArray("Legends");
            getLegendsFromJson(predsJsonArray);

            predsJsonArray = object.getJSONArray("Monitor");
            getMonitorFromJson(predsJsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
        if (monitorTypeList != null){
            fillTheSpinner();
        }

    }

    private void fillTheSpinner() {

        ArrayList al = new ArrayList();

        for(int i = 0; i < monitorTypeList.size(); ++i) {
            al.add(monitorTypeList.get(i).Name);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
        monitor_type_list_View.setAdapter(adapter);


    }

    private void getMonitorFromJson(JSONArray predsJsonArray) throws JSONException {
        monitorList = new ArrayList<>();
        for (int i = 0; i < predsJsonArray.length(); i++) {
            monitor newMonitor = new monitor();
            newMonitor.Id = predsJsonArray.getJSONObject(i).getInt("Id");
            newMonitor.Name = predsJsonArray.getJSONObject(i).getString("Name");
            newMonitor.Desc = predsJsonArray.getJSONObject(i).getString("Desc");
            newMonitor.MonitorTypeId = predsJsonArray.getJSONObject(i).getInt("MonitorTypeId");
            monitorList.add(newMonitor);
        }
    }

    private void getLegendsFromJson(JSONArray predsJsonArray)throws JSONException {
        legendsTypeList=new ArrayList<>();
        for (int i = 0; i < predsJsonArray.length(); i++) {
            legends newLegends = new legends();
            newLegends.Id = predsJsonArray.getJSONObject(i).getInt("Id");
            newLegends.tags = new ArrayList<>();
            JSONArray tags=predsJsonArray.getJSONObject(i).getJSONArray("tags");
            for (int j =0; j < tags.length() ; j++){
                Tags newTag = new Tags();
                newTag.Label = tags.getJSONObject(j).getString("Label");
                newTag.Color = tags.getJSONObject(j).getString("Color");
                newLegends.tags.add(newTag);
            }
            legendsTypeList.add(newLegends);
        }
    }

    private void getMonitorTypeFromJson(JSONArray predsJsonArray) throws JSONException{
         monitorTypeList=new ArrayList<>();
        for (int i = 0; i < predsJsonArray.length(); i++) {
            monitorType newMonitorType = new monitorType();
            newMonitorType.Id = predsJsonArray.getJSONObject(i).getInt("Id");
            newMonitorType.Name = predsJsonArray.getJSONObject(i).getString("Name");
            newMonitorType.LegendId = predsJsonArray.getJSONObject(i).getInt("LegendId");
            newMonitorType.description = predsJsonArray.getJSONObject(i).getString("description");
            monitorTypeList.add(newMonitorType);
            Log.d("pttt",newMonitorType.Id + newMonitorType.Name + newMonitorType.LegendId +newMonitorType.description);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("pttt","MainActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("pttt","MainActivity onStart");
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.d("pttt","MainActivity onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("pttt","MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pttt","MainActivity onPause");
        //onDestroy();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("pttt","MainActivity onDestroy");
    }
}
