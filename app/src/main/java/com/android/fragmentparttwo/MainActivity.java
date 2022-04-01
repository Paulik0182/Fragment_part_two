package com.android.fragmentparttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ColorListController {

    public static final String VALUE_KEY = "VALUE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void openColorScreen(int color) {
        DetailFragment detailFragment = DetailFragment.newInstance(color);//newInstance из class DetailFragment

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.details_fragment_container, detailFragment)
                .addToBackStack(null)//обратный стэк (по нажатию на кнопку назад)
                .commit();

    }
}