package com.example.asus.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThreeFragment extends Fragment {
    private ImageButton btn;
    private ImageButton btn1;
    private EditText AllTrainer;
    String titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_three, container, false);

        AllTrainer = (EditText) view.findViewById(R.id.editText3);

        //获取传过来的参数
        titles= (String) ((Activity1)getActivity()).getmTitles();
        Log.d("Activity1", "AllDynamic is " + titles);
        AllTrainer.setText(titles);


        btn = (ImageButton) view.findViewById(R.id.imageButton5);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(getActivity(),TrainerActivity.class);
                startActivity(intent);

            }

        });



        //return inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }
}
