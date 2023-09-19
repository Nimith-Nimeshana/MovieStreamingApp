package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.FilmsAdapter;
import Adapters.SeriesAdapter;
import Models.FilmDataModel;
import Models.SeriesDataModel;

public class AllSeriesActivity extends AppCompatActivity {

    private List<SeriesDataModel> seriesDataModels;
    private RecyclerView tvShowsRV;
    private SeriesAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_series);

        FirebaseApp.initializeApp(this);

        loadSeriesData();
    }

    private void loadSeriesData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Sref = database.getReference("series"); // firebase database call
        tvShowsRV = findViewById(R.id.recyclerViewTV);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        tvShowsRV.setLayoutManager(gridLayoutManager);
        tvShowsRV.setLayoutManager(gridLayoutManager);

        seriesDataModels = new ArrayList<>();
        seriesAdapter = new SeriesAdapter(seriesDataModels);
        tvShowsRV.setAdapter(seriesAdapter);

        Sref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot: snapshot.getChildren()){
                    SeriesDataModel newSeriesDataModel = contentSnapShot.getValue(SeriesDataModel.class);
                    seriesDataModels.add(newSeriesDataModel);

                }
                seriesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}