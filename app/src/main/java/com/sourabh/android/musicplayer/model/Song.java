package com.sourabh.android.musicplayer.model;

import android.graphics.Bitmap;

public class Song {
    private String title;
    private String artist;
    private String data;
    private String duration;
    private long albumId;
    private Bitmap albumArt;

    public Song(String title, String artist, String data, String duration, long albumId, Bitmap albumArt) {
        this.title = title;
        this.artist = artist;
        this.data = data;
        this.duration = duration;
        this.albumId = albumId;
        this.albumArt = albumArt;
    }

    public Bitmap getAlbumArt() {
        return albumArt;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return title + "\t" + artist + "\t" + data + "\t" + duration + "\n";
    }
}
