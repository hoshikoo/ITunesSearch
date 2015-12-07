package hoshikoo.c4q.nyc.itunessearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Hoshiko on 12/5/15.
 */
public class ContentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    static String text;
    private List<Track> mListing;
    private ListAdapter mAdapter;
    private String mediaType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        mediaType = bundle.getString("media_type");


        View v = inflater.inflate(R.layout.content_fragment,container,false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
        TextView title = (TextView)v.findViewById(R.id.fragment_title);
        title.setText("** " + mediaType );
        final EditText input = (EditText)v.findViewById(R.id.input);
        Button searchBtn = (Button)v.findViewById(R.id.button);

        mAdapter = new ListAdapter(mListing);

        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = input.getText().toString();
                text.replace(' ', '+');

                Retrofit retrofit =
                        new  Retrofit.Builder().baseUrl("https://itunes.apple.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                iTunesAPI service = retrofit.create(iTunesAPI.class);

                Call call = service.getMusic(mediaType, text);

                call.enqueue(new Callback<ListOfTrack>() {
                    @Override
                    public void onResponse(Response<ListOfTrack> response, Retrofit retrofit) {
                        ListOfTrack responseBody = response.body();
                        List<Track> trackLists =  responseBody.getTrackArrayList();

                        mAdapter = new ListAdapter(trackLists);
                        mRecyclerView.setAdapter(mAdapter);

                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Toast.makeText(getActivity(),"No Response",Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
