package com.sourabh.android.musicplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sourabh.android.musicplayer.data.SongsHolder;
import com.sourabh.android.musicplayer.model.Song;
import com.sourabh.android.musicplayer.util.SongRetriever;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    AsyncTask<Void, Void, List<Song>> asyncTask;
    SongRetriever mRetriever;
    SongsHolder holder = SongsHolder.getInstance();
    private static final int songRequestCode = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, songRequestCode);
        } else {
            loadSongs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == songRequestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadSongs();
        }
        else{
            finish();
        }
    }

    private void loadSongs() {
        mRetriever = new SongRetriever(this);
        asyncTask = new AsyncTask<Void, Void, List<Song>>() {

            @Override
            protected List<Song> doInBackground(Void... voids) {
                return mRetriever.getSongs();
            }

            @Override
            protected void onPostExecute(List<Song> songs) {
                holder.setSongList(songs);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        asyncTask.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            asyncTask.cancel(true);
            asyncTask = null;
        }
    }
}
