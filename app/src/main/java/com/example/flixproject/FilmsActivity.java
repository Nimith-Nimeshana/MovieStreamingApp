package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import Models.CastDataModel;

public class FilmsActivity extends AppCompatActivity {

    private List<CastDataModel> castDataModels;
    private CastAdapter castAdapter;

    private RecyclerView castRecyclerView;

    private ImageView thumb;
    private ImageView cover;
    private TextView title;
    private TextView dis;
    private FloatingActionButton playBtn;

    private String movieTitle;
    private String movieDis;
    private String movieThumb;
    private String movieCover;
    private String movieCast;
    private String movieLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        thumb = findViewById(R.id.fThumb);
        cover = findViewById(R.id.coverImg);
        title = findViewById(R.id.fTitle);
        dis = findViewById(R.id.fDis);
        playBtn = findViewById(R.id.featuresPlayBtn);
        castRecyclerView = findViewById(R.id.castRecycleView);

        movieTitle = getIntent().getStringExtra("title");
        movieDis = getIntent().getStringExtra("dis");
        movieThumb = getIntent().getStringExtra("thumb");
        movieCover = getIntent().getStringExtra("cover");
        movieCast = getIntent().getStringExtra("cast");
        movieLink = getIntent().getStringExtra("link");

        Glide.with(this).load(movieThumb).into(thumb); // firebase call
        Glide.with(this).load(movieCover).into(cover);

        thumb.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        cover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

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
                        Intent intent = new Intent(FilmsActivity.this, PlayerActivity.class);
                        intent.putExtra("vid", vidUrl);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(FilmsActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        loadCast();
    }

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
}