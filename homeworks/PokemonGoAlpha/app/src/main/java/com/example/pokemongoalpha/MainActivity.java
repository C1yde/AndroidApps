package com.example.pokemongoalpha;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private SquareImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SparseIntArray imagesDict = new SparseIntArray();
        setGridLayout(imagesDict);
    }

    private void setGridLayout(final SparseIntArray imagesDict) {
        final ArrayList<Integer> imagesList = GetImagesList();

        TableLayout tableLayout = findViewById(R.id.table_layout);

        int columnCount = 3;
        int rowCount = 4;
        int imageIndex = 0;
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            // New tableRow
            TableRow tableRow = new TableRow(this);
            // Setting row layout
            TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(tableLayoutParams);

            tableLayout.addView(tableRow);

            int columnIndex = 0;
            for(;columnIndex < columnCount; columnIndex++) {
                int imageSrc = imagesList.get(imageIndex);
                imageIndex++;

                SquareImageView imageView = CreateImageView();

                imageView.setId(imageIndex);
                imagesDict.append(imageIndex, imageSrc);
                //imageView.setImageResource(imageSrc);

                SetOnImageClick(imageView, imagesDict);

                tableRow.addView(imageView);
            }
        }
    }

    private void SetOnImageClick(SquareImageView imageView, final SparseIntArray imagesDict){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                final SquareImageView castedView = (SquareImageView)view;
                int imageSrc = imagesDict.get(castedView.getId());

                if (selectedImage == null) {
                    castedView.setImageResource(imageSrc);
                    selectedImage = castedView;
                }
                else {
                    castedView.setImageResource(imageSrc);

                    int selectedImageId = imagesDict.get(selectedImage.getId());
                    if (selectedImageId != imageSrc){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                castedView.setImageResource(0);
                                selectedImage.setImageResource(0);
                                selectedImage = null;
                            }
                        }, 500);
                    }
                    else{
                        selectedImage = null;
                    }
                }
            }
        };

        imageView.setOnClickListener(onClickListener);
    }

    private SquareImageView CreateImageView() {
        SquareImageView imageView = new SquareImageView(this);
        imageView.setBackgroundResource(R.drawable.image_standard_style);

        TableRow.LayoutParams rowLayoutParam = new TableRow.LayoutParams();
        rowLayoutParam.height = ViewGroup.LayoutParams.MATCH_PARENT;
        rowLayoutParam.width = ViewGroup.LayoutParams.MATCH_PARENT;
        rowLayoutParam.setMargins(5, 5, 5, 5);
        rowLayoutParam.gravity = Gravity.CENTER;
        rowLayoutParam.weight = 1;

        imageView.setLayoutParams(rowLayoutParam);

        return imageView;
    }

    private ArrayList<Integer> GetImagesList(){
        ArrayList<Integer> imagesList = new ArrayList<>();
        imagesList.add(R.drawable.ic_pikachu);
        imagesList.add(R.drawable.ic_pikachu);
        imagesList.add(R.drawable.ic_bullbasaur);
        imagesList.add(R.drawable.ic_bullbasaur);
        imagesList.add(R.drawable.ic_charmander);
        imagesList.add(R.drawable.ic_charmander);
        imagesList.add(R.drawable.ic_rattata);
        imagesList.add(R.drawable.ic_rattata);
        imagesList.add(R.drawable.ic_pidgey);
        imagesList.add(R.drawable.ic_pidgey);
        imagesList.add(R.drawable.ic_caterpie);
        imagesList.add(R.drawable.ic_caterpie);

        Collections.shuffle(imagesList);

        return imagesList;
    }
}
