package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixproject.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;

    ArrayList<String> FtitleList;
    ArrayList<String> FthumbList;
    ArrayList<String>FcastList;

    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView searchMovImg;
        TextView searchMovTitle, searchMovICast;

        public SearchViewHolder(View itemView) {
            super(itemView);
            searchMovImg = (ImageView) itemView.findViewById(R.id.searchMovThub);
            searchMovTitle =(TextView) itemView.findViewById(R.id.searchMovTitle);
           // searchMovICast =(TextView) itemView.findViewById(R.id.searchMovCast);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> ftitleList, ArrayList<String> fthumbList, ArrayList<String> fcastList) {
        this.context = context;
        FtitleList = ftitleList;
        FthumbList = fthumbList;
        FcastList = fcastList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_cards, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.searchMovTitle.setText(FtitleList.get(position));
        //holder.searchMovICast.setText(FcastList.get(position));

       // Glide.with(context).asBitmap().placeholder(R.mipmap.ic_launcher_round).load(FthumbList.get(position)).into(holder.searchMovImg);
        Glide.with(holder.searchMovImg.getContext()).load(FthumbList.get(position)).into(holder.searchMovImg);
    }

    @Override
    public int getItemCount() {
        return FtitleList.size();
    }
}

