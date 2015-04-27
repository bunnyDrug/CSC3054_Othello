package com.CSC.othello3054.game.Rules.RuleFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CSC.othello3054.game.R;

/**
 * Created by Chris on 12/03/2015.
 */
/**
 * Frag1_rule-Aim of the game
 * extends Fragment
 * to display the fragment Aim of the game
 * sets an empty constructor
 * sets onCreateView which holds the info of the layout
 */
public final class Frag_1_rule_AimOfTheGame extends Fragment {
    /**
     * empty constructor
     */
    public Frag_1_rule_AimOfTheGame() {

    }
    /**
     * onCreateView
     * to set the layout of rule_fragment_01
     *
     * @param inflater to get the layout XML
     * @param container the container of which the fragment is stored
     * @param savedInstanceState stored previous state
     * @return rootView the layout of rule_fragment_01
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rule_fragment_01, container, false);
        return rootView;
    }
}
