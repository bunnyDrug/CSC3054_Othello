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
public final class Frag1_rule_AimOfTheGame extends Fragment {

    public Frag1_rule_AimOfTheGame() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rule_fragment_01, container, false);
        return rootView;
    }
}
