package com.example.pokemongoalpha;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private SquareImageView selectedImage;
    private Integer maxSelectCount;
    private Integer currentSelectIndex = 0;
    private boolean isButtonClicked = false;

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

        Resources resources = getResources();
        int columnCount = resources.getInteger(R.integer.columnCount);
        int rowCount = resources.getInteger(R.integer.rowCount);
        maxSelectCount = columnCount * rowCount;
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

                if (isButtonClicked) {
                    return;
                }

                isButtonClicked = true;
                if (selectedImage == null) {
                    castedView.setImageResource(imageSrc);
                    selectedImage = castedView;
                    isButtonClicked = false;
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
                                isButtonClicked = false;
                            }
                        }, 500);
                    }
                    else{
                        selectedImage = null;
                        isButtonClicked = false;

                        currentSelectIndex += 2;

                        if (maxSelectCount.equals(currentSelectIndex)){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Поздравляем! Вы нашли всех покемонов!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            }
        };

        imageView.setOnClickListener(onClickListener);
    }

    private SquareImageView CreateImageView() {
        Resources resources = getResources();
        SquareImageView imageView = new SquareImageView(this);
        imageView.setBackgroundResource(R.drawable.image_standard_style);

        TableRow.LayoutParams rowLayoutParam = new TableRow.LayoutParams();
        int rowMargin = resources.getInteger(R.integer.image_view_margin);
        rowLayoutParam.height = ViewGroup.LayoutParams.MATCH_PARENT;
        rowLayoutParam.width = ViewGroup.LayoutParams.MATCH_PARENT;
        rowLayoutParam.setMargins(rowMargin, rowMargin, rowMargin, rowMargin);
        rowLayoutParam.gravity = Gravity.CENTER;
        rowLayoutParam.weight = resources.getInteger(R.integer.row_layout_weight);

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
