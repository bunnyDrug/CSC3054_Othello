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
 * Frag0_rule-intro
 * extends Fragment
 * to display the fragment introduction
 * sets an empty constructor
 * sets onCreateView which holds the info of the layout
 */
public final class Frag_0_rule_Intro extends Fragment {
    /**
     * empty Constructor
     */
    public Frag_0_rule_Intro() {

    }

    /**
     * onCreateView
     * to set the layout of rule_fragment_00
     *
     * @param inflater to get the layout XML
     * @param container the container of which the fragment is stored
     * @param savedInstanceState stored previous state
     * @return rootView the layout of rule_fragment_00
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rule_fragment_00, container, false);
        return rootView;
    }
}
