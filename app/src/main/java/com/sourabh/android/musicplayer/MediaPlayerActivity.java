package com.sourabh.android.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sourabh.android.musicplayer.data.SongsHolder;
import com.sourabh.android.musicplayer.model.Song;

import java.util.List;

public class MediaPlayerActivity extends AppCompatActivity {
    ImageView albumArtImageView;
    ImageButton playButton;
    TextView songNameTextView;
    TextView artistNameTextView;
    boolean playing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        albumArtImageView = findViewById(R.id.songAlbumArt);
        playButton = findViewById(R.id.play_button);
        songNameTextView = findViewById(R.id.songName);
        artistNameTextView = findViewById(R.id.artistName);
        int position = getIntent().getIntExtra("position", 0);
        SongsHolder holder = SongsHolder.getInstance();
        List<Song> songList = holder.getSongList();
        Song currSong = songList.get(position);
        if (currSong.getTitle().length() > 10)
            setTitle(currSong.getTitle().substring(0, 10).concat("..."));
        else
            setTitle(currSong.getTitle());
        artistNameTextView.setText(currSong.getArtist());
        songNameTextView.setText(currSong.getTitle());
        if (currSong.getAlbumArt() != null)
            Glide.with(this).load(currSong.getAlbumArt()).into(albumArtImageView);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing) {
                    playButton.setBackgroundResource(R.drawable.ic_pause);
                    playing = true;
                } else {
                    playButton.setBackgroundResource(R.drawable.ic_play);
                    playing = false;
                }
            }
        });
    }
}