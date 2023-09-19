package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import Adapters.FilmsAdapter;
import Adapters.SeriesAdapter;
import Adapters.SliderAdapter;
import Models.FilmDataModel;
import Models.SeriesDataModel;
import Models.SliderDataModel;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<SliderDataModel> dataModels;
    private List<FilmDataModel> filmDataModels; // model call movie data
    private List<SeriesDataModel> seriesDataModels; // model class series data

    private SliderAdapter sliderAdapter;
    private RecyclerView filmRecyclerView;
    private RecyclerView seriesRecycleView;
    private FilmsAdapter filmsAdapter;
    private SeriesAdapter seriesAdapter;
    TextView mSeeAll, tSeeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeeAll = findViewById(R.id.movSeeAll);
        tSeeAll = findViewById(R.id.seriesSeeAll);

        tSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllSeriesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        mSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllMoviesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("@flix");

        FirebaseApp.initializeApp(this);
        // config slider view for translions movies
        SliderView sliderView = findViewById(R.id.sliderview);
        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION); // transition animation style (change by "SIMPLETRANSFORMATION")
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        //auto cycle config
        sliderView.setScrollTimeInSec(6);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        //I crate this is the method for sliding movie items
        renewMovieItems(sliderView);

        // I crate this method for loading thumbnails via firebase
        loadThumbsFirebase();
        loadFilmData()

        ; //Main method for call firebase film data ""loadData(); earlier""
    }

    //private void loadData() {
       // loadFilmData(); // I crate this method for load the "film" data from firebase call
        //loadMovieData();
   //}

   // private void loadMovieData() {

   // }

    private void loadFilmData() { // I crate this method for load the "film" data from firebase
        DatabaseReference FRef = database.getReference("films"); // prepareing to get data from film table firebase
        filmRecyclerView = findViewById(R.id.recyclerViewFilm);

        //code for config Movies recycle view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL); //vertical

        layoutManager.setReverseLayout(true); // use reverse layout cause I need to add new movies to firebase
        layoutManager.setStackFromEnd(true);
        filmRecyclerView.setLayoutManager(layoutManager);

        //create new arry adapater list for films thumbs
        filmDataModels = new ArrayList<>();
        filmsAdapter = new FilmsAdapter(filmDataModels);
        filmRecyclerView.setAdapter(filmsAdapter);

        FRef.addListenerForSingleValueEvent(new ValueEventListener() { //FRef == film(firebase)
            //call firebase query
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot:snapshot.getChildren()){
                    FilmDataModel dataModel = contentSnapShot.getValue(FilmDataModel.class); // get values from DataModel.class
                    filmDataModels.add(dataModel);
                }
                filmsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        loadSeriesData(); // series data method here

    }

    private void loadSeriesData() {
        DatabaseReference Sref = database.getReference("series"); // firebase database call
        seriesRecycleView = findViewById(R.id.recyclerViewSeries);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        seriesRecycleView.setLayoutManager(layoutManager);

        seriesDataModels = new ArrayList<>();
        seriesAdapter = new SeriesAdapter(seriesDataModels);
        seriesRecycleView.setAdapter(seriesAdapter);
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

    //(Firebase thumbnails method config)
    private void loadThumbsFirebase() {
        // "movies" database 1st table name
        myRef.child("movies").addListenerForSingleValueEvent(new ValueEventListener() { //bellow 2 overrides  are created by "new ValueEventListener"
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot contentSlider : snapshot.getChildren()) {
                    SliderDataModel sliderItem = contentSlider.getValue(SliderDataModel.class);
                    dataModels.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged(); // call the method notifyDataSetChanged
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show(); // look at 1st word
                finish();

            }
        });
    }

    //(sliding movie items method config)
    public void renewMovieItems(View view) {
        dataModels = new ArrayList<>();
        SliderDataModel dataItems =   new SliderDataModel();
        dataModels.add(dataItems);

        sliderAdapter.renewMovieItems(dataModels);
        sliderAdapter.deleteMovieItems(0);


        // nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.bottomProfile);
        bottomNavigationView.setSelectedItemId(R.id.bottomSearch);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                //case R.id.bottomHome:
                   // return true;
                case R.id.bottomSearch:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottomProfile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                return true;
            }
            return false;

                });
    }
}