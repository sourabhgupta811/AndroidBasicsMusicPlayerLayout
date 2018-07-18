package com.sourabh.android.musicplayer.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.sourabh.android.musicplayer.model.Song;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SongRetriever {
    private ContentResolver contentResolver;
    private List<Song> mSongsList = new ArrayList<>();

    public SongRetriever(Context context) {
        contentResolver = context.getContentResolver();
    }

    public List<Song> getSongs() {
        if (mSongsList.size() > 0) {
            return mSongsList;
        }
        Uri songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projections = {
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID
        };
        Cursor songsCursor = contentResolver.query(songsUri, projections, null, null, null);
        if (songsCursor != null) {
            long id;
            while (songsCursor.moveToNext()) {
                id = songsCursor.getLong(4);
                Song song = new Song(
                        songsCursor.getString(0), songsCursor.getString(1),
                        songsCursor.getString(2), songsCursor.getString(3),
                        id, getAlbumArt(id)
                );
                mSongsList.add(song);
            }
            songsCursor.close();
            return mSongsList;
        }
        return null;
    }

    private Bitmap getAlbumArt(long id) {
        Bitmap albumArt = null;
        Uri baseAlbumUri = Uri.parse("content://media/external/audio/albumart");
        Uri albumUri = ContentUris.withAppendedId(baseAlbumUri, id);
        try {
            InputStream inputStream = contentResolver.openInputStream(albumUri);
            albumArt = BitmapFactory.decodeStream(inputStream);
            albumArt = Bitmap.createScaledBitmap(albumArt, 120, 120, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return albumArt;
    }
}
