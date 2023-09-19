package Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixproject.FeaturesActivity;
import com.example.flixproject.R;
import Models.SeriesDataModel;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private List<SeriesDataModel> modelList;

    //modelList constructor
    public SeriesAdapter(List<SeriesDataModel> modelList) {
        this.modelList = modelList;
    }

    //Implement methods for SeriesAdapter
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false); // call the layout
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(modelList.get(position).getStitle()); // get Stitle string value from modellist to holder title
        Glide.with(holder.cardImageView.getContext()).load(modelList.get(position).getSthumb()).into(holder.cardImageView);// get Sthumb string value from modellist to holder cardImgView

        //create new intent for send data to SeriesActivity class
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new intent for send data to FeaturesActivity class
                // WORNING !!!!! films parts also here
                Intent sendDataToFeaturesActivity = new Intent(holder.itemView.getContext(), FeaturesActivity.class);

                sendDataToFeaturesActivity.putExtra("title",modelList.get(position).getStitle());
                sendDataToFeaturesActivity.putExtra("parts",modelList.get(position).getSepisodes());
                sendDataToFeaturesActivity.putExtra("cover",modelList.get(position).getScover());
                sendDataToFeaturesActivity.putExtra("thumb",modelList.get(position).getSthumb());
                sendDataToFeaturesActivity.putExtra("dis",modelList.get(position).getSdis());
                sendDataToFeaturesActivity.putExtra("cast",modelList.get(position).getScast());
                sendDataToFeaturesActivity.putExtra("link",modelList.get(position).getSlink());

                // transition animation
                ActivityOptionsCompat optionsCompat =  ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.cardImageView,
                                "imageMain"); // transition names all xml files
                holder.itemView.getContext().startActivity(sendDataToFeaturesActivity,optionsCompat.toBundle());

    }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    //create ViewHolder Constructor
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView cardImageView;
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            cardImageView = itemView.findViewById(R.id.cardImg);
        }
    }
}

