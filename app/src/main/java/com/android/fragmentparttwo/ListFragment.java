package com.android.fragmentparttwo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ListFragment extends Fragment {

    private Button blueButton;
    private Button greenButton;
    private Button redButton;
    private Button blackButton;
    private Button yellowButton;


    //метод присоединяющий фрагмент к активити
    //здесь фрагмент становится частью активити
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);// context в данном случае и есть активити
        //в момент присоединения фрагмента к активити проверяем является
        // ли контролерром (активити) или нет
        try {
            getController();
            // ловим все исключения
        }catch (Exception e){
            throw new IllegalStateException(
                    "ListFragment можно показывать только " +
                "в активити имплементирующих интерфейс ColorListController"
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setListeners();
    }


    private void initViews(View view) {
        blueButton = view.findViewById(R.id.blue_button);
        greenButton = view.findViewById(R.id.green_button);
        redButton = view.findViewById(R.id.red_button);
        blackButton = view.findViewById(R.id.black_button);
        yellowButton = view.findViewById(R.id.yellow_button);
    }

    private void setListeners() {
        @SuppressLint("NonConstantResourceId") final View.OnClickListener OnColorClickListener = view -> {
            int color;

            //проверяем нажатие кнопки и устанвливаем цвет экрана
            switch (view.getId()) {
                case R.id.blue_button:
                    color = Color.BLUE;
                    break;
                case R.id.green_button:
                    color = Color.GREEN;
                    break;
                case R.id.red_button:
                    color = Color.RED;
                    break;
                case R.id.black_button:
                    color = Color.BLACK;
                    break;
                case R.id.yellow_button:
                    color = Color.YELLOW;
                    break;
                default:
                    color = 0;
            }
            openColorScreen(color);
        };

        blueButton.setOnClickListener(OnColorClickListener);
        greenButton.setOnClickListener(OnColorClickListener);
        redButton.setOnClickListener(OnColorClickListener);
        blackButton.setOnClickListener(OnColorClickListener);
        yellowButton.setOnClickListener(OnColorClickListener);
    }

    //открываем экран
    private void openColorScreen(int color) {

        //так как getActivity не видет тип активити необходимо указывать конкретно ту активити к которой обращаемся.
        //К активити лучше обращатся, например, через интерфейс
//        ((MainActivity) getActivity()).openColorScreen(color); //вариант1 технически верный но не стоит так писать
//        ((ColorListController) getActivity()).openColorScreen(color);//вариант 2. верный но обязательно нужно наследоватся в соответствующем классе
        getController().openColorScreen(color);//вариант 3. отимальный, даже если забудем отнаследоватся (вариант2) то все будет работать
    }

    private ColorListController getController(){
        return (ColorListController) getActivity();
    }

    //интерфей. от  данного интерфейса необходимо наследоватся в соответствующей активити
    interface ColorListController {
        void openColorScreen(int color);
    }
}