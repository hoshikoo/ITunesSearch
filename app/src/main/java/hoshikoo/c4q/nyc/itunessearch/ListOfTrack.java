package hoshikoo.c4q.nyc.itunessearch;

/**
 * Created by Hoshiko on 12/6/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfTrack{

    @SerializedName("results")
    @Expose
    List<Track> trackArrayList;

    public List<Track> getTrackArrayList(){
        return trackArrayList;
    }
}



