package com.example.chambcle.jeutaquin;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.Arrays;

/**
 * Created by chambcle on 27/10/17.
 */

public class TaquinAdapter extends BaseAdapter {

    private Context mContext;
    // keep the original order of chunks, do not modify it during the game
    private Bitmap[] chunks;
    // shuffled chunks on which we are playing
    private Bitmap[] shuffled;

    // methodes getCount() etc à implémenter
    // 1 constructeur qui prend le contexte, l'image et la taille de la grille en param
    // (Context c, Bitmap img, int gridwidth) gridwidth = 3
    // le constructeur va prendre l'image, la découper en 9 morceaux et remplir un tableau
    // pour couper l'image Bitmap.createBitmap(img, x, y, width, height)

    public TaquinAdapter (Context mContext, Bitmap picture, int gridWidth) {
        this.mContext = mContext;
        // on découpe et on stocke dans un tableau, on initialise
        chunks = new Bitmap[gridWidth*gridWidth];
        for(int i = 0; i < chunks.length; i++) {
            //chunks[i] = Bitmap.createBitmap(...);
            // TO-DO
            // for the first chunk put a black square chunks[0]
        }
        chunks[0] = Bitmap.createBitmap(picture.getWidth()/gridWidth, picture.getHeight()/gridWidth, Bitmap.Config.ALPHA_8);
    }

    public void shuffle() {
    // shuffle all the chunks so the image is mixed-up
        shuffled = Arrays.copyOf(chunks, chunks.length);
        // you have to do a loop in order to switch chunks a random number of times
    }

    public TaquinAdapter(TestLoadImage testLoadImage) {
    }

    @Override
    public int getCount() {
        return chunks.length;
    }

    @Override
    public Object getItem(int i) {
        return chunks[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    //public View getView(int i, View view, ViewGroup viewGroup) {
    //   return null;
    //}

    public View getView(int i, View view, ViewGroup parent) {
        ImageView imageView;
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageBitmap(shuffled[i]);
        return imageView;
    }
}


