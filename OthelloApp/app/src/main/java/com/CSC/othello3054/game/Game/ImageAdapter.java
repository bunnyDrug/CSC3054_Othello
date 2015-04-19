package com.CSC.othello3054.game.Game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by don on 08/03/2015.
 *
 * Modified from the google grid view tutorial.
 * Ref: http://developer.android.com/guide/topics/ui/layout/gridview.html#top
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private static final int NUMBER_OF_COUNTERS = 64;
    private int[] boardLayout;

    public ImageAdapter(Context c, int[] initialPieceLayout) {
        mContext = c;
        boardLayout = initialPieceLayout;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_COUNTERS;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(boardLayout[position]);
        return imageView;
    }
}
