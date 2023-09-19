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
import Adapters.SliderAdapter;
import Models.FilmDataModel;
import Models.SeriesDataModel;
import Models.SliderDataModel;

public class AllMoviesActivity extends AppCompatActivity {

    private List<FilmDataModel> filmDataModels;
    private RecyclerView movRecyclerView;
    private FilmsAdapter filmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        FirebaseApp.initializeApp(this);

        loadFilmData();
    }

    private void loadFilmData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference FRef = database.getReference("films"); // prepareing to get data from film table firebase
        movRecyclerView = findViewById(R.id.recyclerViewMov);

        //code for config Movies recycle view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        movRecyclerView.setLayoutManager(gridLayoutManager);
        movRecyclerView.setLayoutManager(gridLayoutManager);

        //create new arry adapater list for films thumbs
        filmDataModels = new ArrayList<>();
        filmsAdapter = new FilmsAdapter(filmDataModels);
        movRecyclerView.setAdapter(filmsAdapter);

        FRef.addListenerForSingleValueEvent(new ValueEventListener() { //FRef == film(firebase)
            //call firebase query
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    FilmDataModel dataModel = contentSnapShot.getValue(FilmDataModel.class); // get values from DataModel.class
                    filmDataModels.add(dataModel);
                }
                filmsAdapter.notifyDataSetChanged();
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