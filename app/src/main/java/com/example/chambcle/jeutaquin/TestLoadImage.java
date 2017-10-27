package com.example.chambcle.jeutaquin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by chambcle on 27/10/17.
 */

public class TestLoadImage extends AppCompatActivity {

    private static final int PICK_PICTURE = 0;

    private void loadPicture(Uri pUri) {
        Toast.makeText(this, pUri.toString(), Toast.LENGTH_LONG).show();
        // activité doit être elligible quand on clique sur une image

        try {
            InputStream is = this.getContentResolver().openInputStream(pUri);
            // flux en lecture vers l'uri récupérée
            Bitmap img = BitmapFactory.decodeStream(is);
            // on récupère l'image
            // on veut l'afficher
            ImageView iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(img);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_load_image);
        Uri pUri = this.getIntent().getData();
        if (pUri == null) {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image:*");

            this.startActivityForResult(i, PICK_PICTURE);
        } else {
            loadPicture(pUri);
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new TaquinAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(TestLoadImage.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri pUri = data.getData();
        if (pUri == null) {
            loadPicture(pUri);
        }
    }
}
