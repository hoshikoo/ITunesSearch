package hoshikoo.c4q.nyc.itunessearch;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Hoshiko on 12/5/15.
 */
public interface iTunesAPI {
    @GET("/search?")
    Call<ListOfTrack> getMusic(@Query("media") String media, @Query("term") String term);
}
