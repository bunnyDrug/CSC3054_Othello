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
 * Frag1_rule-BasicRules
 * extends Fragment
 * to display the fragment Basic rules of othello
 * sets an empty constructor
 * sets onCreateView which holds the info of the layout
 */
public class Frag_3_rule_BasicRules extends Fragment {
    /**
     * onCreateView
     * to set the layout of rule_fragment_03
     *
     * @param inflater to get the layout XML
     * @param container the container of which the fragment is stored
     * @param savedInstanceState stored previous state
     * @return v the layout of rule_fragment_03
     */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.rule_fragment_03,container,false);
        return v;
 }
}
