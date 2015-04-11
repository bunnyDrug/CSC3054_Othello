package com.example.don.othello.Frags;

import android.app.Activity;
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
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    public void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag_one_layout,container,false);
        return v;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}

