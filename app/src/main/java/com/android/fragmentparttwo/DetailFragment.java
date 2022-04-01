package com.android.fragmentparttwo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class DetailFragment extends Fragment {

    private static final String COLOR_ARG_KEY = "COLOR_ARG_KEY";

    private TextView resultNumberTv;
    private TextView resultTextTv;

    //обязательно должен быть пустой конструктор фрагмента для пересоздания фрагмента,
    // например при повороте (система обязательно требует пустой конструктор
    public DetailFragment() {
    }

    //для того чтобы фрагмент пережил поворот экрана (здесь создается фрагмент и запоминаются данные)
    public static DetailFragment newInstance(int color){
        DetailFragment fragment = new DetailFragment(); //создание фрагмента
        Bundle args = new Bundle();// это корзина (в данном случае это тоже самое, что и при с
        // охранении ключь значение. сюда мы кладем данные, используется при повороте экрана).
        args.putInt(COLOR_ARG_KEY, color);// в корзину положили цвет

        fragment.setArguments(args);//устанавливаем аргументы (корзину положили во фрагмент)
        return fragment;// отдали фрагмент
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
//        getResult();
        resultNumberTv.setText(String.valueOf(getRandomValue()));
        //получаем фрагмент с данными (ниже приведенный метод)
        if (getArguments() != null){
            int color = getArguments().getInt(COLOR_ARG_KEY); //получаем цвет
            view.setBackgroundColor(color);//кладем цвет

        }
    }

    private void initViews(View view) {
        resultNumberTv = view.findViewById(R.id.result_number_second_text_view);
        resultTextTv = view.findViewById(R.id.result_text_second_text_view);
    }

//    private void getResult(){
//        Bundle result = new Bundle();
//
//        result.getInt(String.valueOf(getRandomValue()));
//                resultNumber.setText(String.valueOf(getRandomValue()));
//    }

    private int getRandomValue() {
        Random random = new Random();
        return random.nextInt(Integer.parseInt(MainActivity.VALUE_KEY, 0));
    }
}