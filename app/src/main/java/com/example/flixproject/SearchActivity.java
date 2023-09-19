package com.example.flixproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Adapters.FilmsAdapter;
import Adapters.SearchAdapter;
import Models.FilmDataModel;

public class SearchActivity extends AppCompatActivity {

    EditText serchTxt;
    RecyclerView serchRV;
    DatabaseReference serchRef;
    //private List<FilmDataModel> filmDataModels;
    //private FilmsAdapter filmsAdapter;
    FirebaseUser firebaseUser;

    ArrayList<String> FtitleList;
    ArrayList<String> FthumbList;
    ArrayList<String>FcastList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        serchTxt =(EditText) findViewById(R.id.searchTxt);
        serchRV = (RecyclerView) findViewById(R.id.searchRV);

        serchRef = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        serchRV.setHasFixedSize(true);
        serchRV.setLayoutManager(new LinearLayoutManager(this));
        serchRV.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        FtitleList = new ArrayList<>();
        FthumbList = new ArrayList<>();
        FcastList = new ArrayList<>();

//        filmDataModels = new ArrayList<>();
//        filmsAdapter = new FilmsAdapter(filmDataModels);
//        serchRV.setAdapter(filmsAdapter);

        serchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                }else {
                    FtitleList.clear();
                    FcastList.clear();
                    FthumbList.clear();
                    serchRV.removeAllViews();

                }

            }

            private void setAdapter(final String searchedstring) {

                serchRef.child("films").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        //cler for new
                        FtitleList.clear();
                        FcastList.clear();
                        FthumbList.clear();
                        serchRV.removeAllViews();

                        int counter =  0; //for results

                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            String fid = snapshot1.getKey();
                            String ftitle = snapshot1.child("Ftitle").getValue(String.class);
                            String fthumb = snapshot1.child("Fthumb").getValue(String.class);
                            String fcast = snapshot1.child("Fcast").getValue(String.class);

                            if (ftitle.toLowerCase().contains(searchedstring.toLowerCase())){
                                FtitleList.add(ftitle);
                                FcastList.add(fcast);
                                FthumbList.add(fthumb);
                                counter++;

                            }else if (fcast.toLowerCase().contains(searchedstring.toLowerCase())){
                                FtitleList.add(ftitle);
                                FcastList.add(fcast);
                                FthumbList.add(fthumb);
                                counter++;

                            }
                            if (counter == 15) //results
                                break;
                        }
                        searchAdapter = new SearchAdapter(SearchActivity.this, FtitleList,FcastList,FthumbList);
                        serchRV.setAdapter(searchAdapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
