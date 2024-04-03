package algonquin.CST2335.pate1214;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeezerSongSearch extends AppCompatActivity {
    private List<Artist> artistList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArtistsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deezer_song_api);

        recyclerView = findViewById(R.id.artistsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArtistsAdapter(artistList);
        recyclerView.setAdapter(adapter);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(view -> searchArtists(searchEditText.getText().toString()));
    }

    private void searchArtists(String query) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.deezer.com/search/artist/?q="+ Uri.encode(query);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray artistsArray = response.getJSONArray("data");
                        Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                        artistList.clear();
                        for (int i = 0; i < artistsArray.length(); i++) {
                            JSONObject artistObj = artistsArray.getJSONObject(i);
                            Artist artist = new Artist(
                                    artistObj.getInt("id"),
                                    artistObj.getString("name"),
                                    artistObj.getString("tracklist")
                            );
                            artistList.add(artist);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("Artist Search", "Error: " + error.toString())
        );

        queue.add(jsonObjectRequest);
    }
}
