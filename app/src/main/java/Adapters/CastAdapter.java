package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import Models.CastDataModel;
import com.example.flixproject.R;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {

    private List<CastDataModel> castDataModels;

    public CastAdapter(List<CastDataModel> castDataModels) {
        this.castDataModels = castDataModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.castTxtviw.setText(castDataModels.get(position).getCname());
        Glide.with(holder.itemView).load(castDataModels.get(position).getClink()).into(holder.castImage);

    }

    @Override
    public int getItemCount() {

        return castDataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView castImage;
        TextView castTxtviw;

        public MyViewHolder(View itemView) {
            super(itemView);

            castImage = itemView.findViewById(R.id.castImage);
            castTxtviw = itemView.findViewById(R.id.castName);
        }
    }
}
