package com.example.don.othello.Frags;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.don.othello.R;



/**
 * Created by Chris on 12/03/2015.
 */
public class FragRule1 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag_one_layout,container,false);
        return v;
    }
}

