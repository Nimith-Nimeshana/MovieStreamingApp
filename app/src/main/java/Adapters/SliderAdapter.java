package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixproject.PlayerActivity;
import com.example.flixproject.R;
import Models.SliderDataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }
private List<SliderDataModel> dataModels = new ArrayList<>(); // call the data models of slider movies
    public void renewMovieItems(List<SliderDataModel> dataModels) {
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }

    public void deleteMovieItems(int position){
        this.dataModels.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        //inflate layout for slide movies from slider
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_movie_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        viewHolder.MovieTitle.setText(dataModels.get(position).getMtitle());
        //Glide supports fetching, decoding, and displaying video stills, images, in here this code will show the thumbnail from database to slider view
        Glide.with(viewHolder.itemView).load(dataModels.get(position).getMthumb()).into(viewHolder.MovieSlider);
        viewHolder.PlayBtn.setOnClickListener(new View.OnClickListener() { // crate a new onclick lister for player btn for play video
            @Override
            public void onClick(View v) {
                Intent movie_video = new Intent(context, PlayerActivity.class); // initalize the playeract class for when click player btn
                movie_video.putExtra("vid", dataModels.get(position).getMvid()); // getMvid from datamodels class to new intent movie_video
                movie_video.putExtra("title", dataModels.get(position).getMtitle()); // getMtitle from datamodels class to new intent movie_video
                v.getContext().startActivity(movie_video); // activity launch by above codes

            }
        });

    }

    @Override
    public int getCount() {
        return dataModels.size()    ;
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder{
        //creating variables for movie sider and movie title to sliderview
        ImageView MovieSlider;
        TextView MovieTitle;
        FloatingActionButton PlayBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            // assigning variables by IDs
            MovieSlider = itemView.findViewById(R.id.movThumb);
            MovieTitle = itemView.findViewById(R.id. movTitle);
            PlayBtn = itemView.findViewById(R.id.sliderPlayBtn);
        }
    }
}
