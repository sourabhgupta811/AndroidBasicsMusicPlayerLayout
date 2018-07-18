package com.sourabh.android.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sourabh.android.musicplayer.adapter.SongsListAdapter;
import com.sourabh.android.musicplayer.data.SongsHolder;
import com.sourabh.android.musicplayer.model.Song;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mSongsListView;
    private SongsHolder holder = SongsHolder.getInstance();
    private final AdapterView.OnItemClickListener mClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSongsListView = findViewById(R.id.listView);
        displaySongs();
    }

    public void displaySongs() {
        List<Song> songsList = holder.getSongList();
        SongsListAdapter adapter = new SongsListAdapter(this, songsList);
        mSongsListView.setAdapter(adapter);
        mSongsListView.setOnItemClickListener(mClickListener);
    }
}
