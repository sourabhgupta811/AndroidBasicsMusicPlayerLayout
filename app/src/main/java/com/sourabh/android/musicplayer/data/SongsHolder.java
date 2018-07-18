package com.sourabh.android.musicplayer.data;

import com.sourabh.android.musicplayer.model.Song;

import java.util.List;

public class SongsHolder {
    private List<Song> songList;
    private static final SongsHolder songsHolder = new SongsHolder();

    private SongsHolder() {
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public static SongsHolder getInstance() {
        return songsHolder;
    }
}
