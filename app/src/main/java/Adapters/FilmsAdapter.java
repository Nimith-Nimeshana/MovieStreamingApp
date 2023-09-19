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
import Models.FilmDataModel;
import com.example.flixproject.FilmsActivity;
import com.example.flixproject.R;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.MyViewHolder> {

    private List<FilmDataModel> dataModels;

    public FilmsAdapter(List<FilmDataModel> dataModels) {
        this.dataModels = dataModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false); //call movie card layout
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(dataModels.get(position).getFtitle()); // get Ftitle from Datamodel.class to this text viw
        Glide.with(holder.imageView.getContext()).load(dataModels.get(position).getFthumb()).into(holder.imageView); // get Fthumb from firebase to imageview

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new intent for send data to FeaturesActivity class
                // WORNING !!!!! films parts also here
                Intent sendDataToFeaturesActivity = new Intent(holder.itemView.getContext(), FilmsActivity.class);
                sendDataToFeaturesActivity.putExtra("title",dataModels.get(position).getFtitle());
                //sendDataToFeaturesActivity.putExtra("parts",dataModels.get(position).getFparts());
                sendDataToFeaturesActivity.putExtra("cover",dataModels.get(position).getFcover());
                sendDataToFeaturesActivity.putExtra("thumb",dataModels.get(position).getFthumb());
                sendDataToFeaturesActivity.putExtra("dis",dataModels.get(position).getFdis());
                sendDataToFeaturesActivity.putExtra("cast",dataModels.get(position).getFcast());
                sendDataToFeaturesActivity.putExtra("link",dataModels.get(position).getFlink());

                // transition animation
                ActivityOptionsCompat optionsCompat =  ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.imageView,
                                "imageMain"); // transition names all xml files
                holder.itemView.getContext().startActivity(sendDataToFeaturesActivity,optionsCompat.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImg);
            textView = itemView.findViewById(R.id.movieTitle);
        }
    }
}
