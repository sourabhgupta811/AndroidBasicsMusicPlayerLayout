package com.sourabh.android.musicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sourabh.android.musicplayer.R;
import com.sourabh.android.musicplayer.model.Song;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongsListAdapter extends ArrayAdapter {
    private List<Song> data;

    public SongsListAdapter(@NonNull Context context, List<Song> data) {
        super(context, 0);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        }
        TextView titleTextView = v.findViewById(R.id.titleTextView);
        TextView artistTextView = v.findViewById(R.id.artistTextView);
        CircleImageView albumArtImageView = v.findViewById(R.id.albumArtImageView);
        Song currentSong = data.get(position);

        if (currentSong.getAlbumArt() == null) {
            Glide.with(getContext()).load(R.drawable.sample).into(albumArtImageView);
        } else {
            Glide.with(getContext()).load(currentSong.getAlbumArt()).into(albumArtImageView);
        }
        titleTextView.setText(currentSong.getTitle());
        artistTextView.setText(currentSong.getArtist());
        return v;
    }
}
