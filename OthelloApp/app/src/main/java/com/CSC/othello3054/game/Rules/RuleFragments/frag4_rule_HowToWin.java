package com.CSC.othello3054.game.Rules.RuleFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CSC.othello3054.game.R;

/**
 * Created by don on 22/04/2015.
 */
/**
 * Frag1_rule-howToWin
 * extends Fragment
 * to display the fragment how to win
 * sets an empty constructor
 * sets onCreateView which holds the info of the layout
 */
public class Frag4_rule_HowToWin extends Fragment {
    /**
     * onCreateView
     * to set the layout of rule_fragment_04
     *
     * @param inflater to get the layout XML
     * @param container the container of which the fragment is stored
     * @param savedInstanceState stored previous state
     * @return v the layout of rule_fragment_04
     */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.rule_fragment_04,container,false);
        return v;
 }
}
