package com.example.nayantiwari.movieapitesting;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nayantiwari on 6/25/17.
 */

public class SomeAdapter extends RecyclerView.Adapter<SomeAdapter.ViewHolder> {

    final private String IMAGE_URL = "http://image.tmdb.org/t/p/original/";

    final private String API_KEY = "?api_key=622c017c1fac858c7683036985247ab5";

    final private ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    private List<MovieItem> movieItems;
    private Context context;

    public SomeAdapter(Context context, List<MovieItem> movieItems, ListItemClickListener listener) {
        this.context = context;
        mOnClickListener = listener;
        this.movieItems = movieItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieItem movieItem = movieItems.get(position);
        ImageView iv_demo = holder.getIv_demo();

        Glide.with(context).load(IMAGE_URL + movieItem.getMoviePoster() + API_KEY)
                .centerCrop()
                .placeholder(R.drawable.placeholderimage)
                .into(iv_demo);

        ImageButton favorite_button = holder.getFavorite_button();
        favorite_button.setImageResource(movieItem.isFavourite() ? R.drawable.icons8_starfilled_48 : R.drawable.icons8_star_48);
    }

    @Override
    public int getItemCount() {
        return getMovieItems().size();
    }

    public List<MovieItem> getMovieItems() {
        if (movieItems == null) {
            movieItems = new ArrayList<>();
        }
        return movieItems;
    }

    public void setMovieItems(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //        private TextView tv_demo;
        private ImageView iv_demo;
        private ImageButton favorite_button;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_demo = (ImageView) itemView.findViewById(R.id.iv_demo);
            favorite_button = (ImageButton) itemView.findViewById(R.id.favorite_button);

            itemView.setOnClickListener(this);

            favorite_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    MovieItem movieItem = getMovieItems().get(adapterPosition);
                    movieItem.setFavourite(!movieItem.isFavourite());
                    notifyItemChanged(adapterPosition);
                }
            });
        }

        public ImageView getIv_demo() {
            return iv_demo;
        }

        public void setIv_demo(ImageView iv_demo) {
            this.iv_demo = iv_demo;
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public ImageButton getFavorite_button() {
            return favorite_button;
        }

        public void setFavorite_button(ImageButton favorite_button) {
            this.favorite_button = favorite_button;
        }
//        public TextView getTv_demo() {
//            return tv_demo;
//        }
//
//        public void setTv_demo(TextView tv_demo) {
//            this.tv_demo = tv_demo;
//        }
    }

}
