package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.CastAdapter;
import Adapters.EpisodesAdapter;
import Models.CastDataModel;
import Models.EpisodesDataModel;

// fetching firebase data detail page
public class FeaturesActivity extends AppCompatActivity {

    private List<CastDataModel> castDataModels;
    private List<EpisodesDataModel> episodesDataModels;
    private CastAdapter castAdapter;
    private EpisodesAdapter episodesAdapter;

    private RecyclerView episodeRecyclerView, castRecyclerView;

    private ImageView thumb;
    private ImageView cover;
    private TextView title;
    private TextView dis;
    private FloatingActionButton playBtn;


    private String movieTitle;
    private String movieDis;
    private String movieThumb;
    private String movieParts;
    private String movieCover;
    private String movieCast;
    private String movieLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        thumb = findViewById(R.id.fThumb);
        cover = findViewById(R.id.coverImg);
        title = findViewById(R.id.fTitle);
        dis = findViewById(R.id.fDis);
        playBtn = findViewById(R.id.featuresPlayBtn);
        castRecyclerView = findViewById(R.id.castRecycleView);
        episodeRecyclerView = findViewById(R.id.episodesRecycleView);

        //get data through intent
        movieTitle = getIntent().getStringExtra("title");
        movieDis = getIntent().getStringExtra("dis");
        movieThumb = getIntent().getStringExtra("thumb");
        movieParts = getIntent().getStringExtra("parts");
        movieCover = getIntent().getStringExtra("cover");
        movieCast = getIntent().getStringExtra("cast");
        movieLink = getIntent().getStringExtra("link");

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(movieTitle);
        //back btn toolbar
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(movieThumb).into(thumb); // firebase call
        Glide.with(this).load(movieCover).into(cover);

        thumb.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        cover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        title.setText(movieTitle);
        dis.setText(movieDis);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("link").child(movieLink).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String vidUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(FeaturesActivity.this,PlayerActivity.class);
                        intent.putExtra("vid", vidUrl);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(FeaturesActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        loadCast();
        loadEpisodes();
    }

    private void loadEpisodes() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference episodeRef = database.getReference();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        episodeRecyclerView.setLayoutManager(layoutManager);

        episodesDataModels = new ArrayList<>();
        episodesAdapter = new EpisodesAdapter(episodesDataModels);
        episodeRecyclerView.setAdapter(episodesAdapter);


        episodeRef.child("link").child(movieParts).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot content : snapshot.getChildren()) { // firebase children gets form cast
                    EpisodesDataModel episodesDataModel = content.getValue(EpisodesDataModel.class);
                    episodesDataModels.add(episodesDataModel);
                }
                episodesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    // cast database call and config
    private void loadCast() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference castRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        castRecyclerView.setLayoutManager(layoutManager);

        castDataModels = new ArrayList<>();
        castAdapter = new CastAdapter(castDataModels);
        castRecyclerView.setAdapter(castAdapter);

        castRef.child("cast").child(movieCast).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot content : snapshot.getChildren()) { // firebase children gets form cast
                    CastDataModel castDataModel = content.getValue(CastDataModel.class);
                    castDataModels.add(castDataModel);
                }
                castAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish();
       }
    return super.onOptionsItemSelected(item);
  }
}