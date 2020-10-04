package codepath.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

import codepath.flixster.DetailActivity;
import codepath.flixster.R;
import codepath.flixster.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
       View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "OnBindViewHolder" + position);
    //get the movie at the passed in position
        Movie movie = movies.get(position);

        //bind the movie data int the vh
        holder.bind(movie);
    }
    // Return the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //defines view holder
    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;
            //if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                imageURL = movie.getBackdropPath();
            else {
                imageURL = movie.getPosterPath();
            }
            // then imageURL = back drop image
            //else imageURL = poster image

            Glide.with(context).load(imageURL).into(ivPoster);

            //register click listener on whole container, and navigate to a new activity once clicked
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);

                    i.putExtra("movie", Parcels.wrap(movie));


                    context.startActivity(i);




                }
            });

        }
    }
}
