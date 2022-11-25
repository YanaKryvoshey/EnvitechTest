package com.example.envitechtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class TagsFragment extends Fragment {

    private ArrayList<Tags> tagsArrayList;
private RecyclerView RecyclerView_fragment;


    public TagsFragment() {
        // Required empty public constructor
    }

    public TagsFragment(ArrayList<Tags> tagsArrayList) {
        this.tagsArrayList = tagsArrayList;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tags, container, false);
        RecyclerView_fragment = view.findViewById(R.id.RecyclerView_fragment);
        RecyclerView_fragment.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView_fragment.setHasFixedSize(true);
        TagsAdapter tagsAdapter = new TagsAdapter(getContext(),tagsArrayList);
        RecyclerView_fragment.setAdapter(tagsAdapter);
        tagsAdapter.notifyDataSetChanged();


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Log.d("PTTT","PRESS BACK");
                Intent intent = new Intent(getContext(),MainActivity.class);
                getActivity().finish();
                startActivity(intent);


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()



        return view;
    }


}