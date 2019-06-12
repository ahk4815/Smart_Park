package com.example.android.smartpark;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class adapterComm extends ArrayAdapter<car> {
    private Activity context;
    List<car> artists;

    public adapterComm(Activity context, List<car> artists) {
        super(context, R.layout.coms, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.coms, null, true);


        TextView UserName = (TextView) listViewItem.findViewById(R.id.id3);
        // TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        car artist = artists.get(position);
        UserName.setText(artist.getRfid());
        //  textViewGenre.setText(artist.getArtistGenre());

        return listViewItem;
    }
}
