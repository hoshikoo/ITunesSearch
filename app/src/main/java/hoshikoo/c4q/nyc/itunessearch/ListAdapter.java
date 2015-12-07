package hoshikoo.c4q.nyc.itunessearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoshiko on 12/5/15.
 */


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListingHolder> {

    private List<Track> mListing;

    public ListAdapter(List<Track> listing) {
        mListing = listing;
    }

    @Override
    public ListingHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ListingHolder viewHolder = new ListingHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListingHolder holder, int i) {

        holder.bindTracks(mListing.get(i));

    }

    @Override
    public int getItemCount() {
        if(mListing == null)
        return 0;

        return mListing.size();
    }


    public class ListingHolder extends RecyclerView.ViewHolder {

       public ImageView imageView;
       public TextView trackTextView;
       public TextView artistTextView;

        public ListingHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            trackTextView = (TextView) itemView.findViewById(R.id.track_text_view);
            artistTextView = (TextView) itemView.findViewById(R.id.artist_text_view);
        }

        public void bindTracks(Track track){

            Picasso.with(itemView.getContext()).load(track.getArtworkUrl60()).into(imageView);
            trackTextView.setText(track.getTrackName());
            artistTextView.setText(track.getArtistName());

        }

    }


}
