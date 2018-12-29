package com.example.asus.my_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class TwoFragment extends Fragment {
    private ImageButton btn;
    private ImageButton btn1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        btn = (ImageButton) view.findViewById(R.id.imageButton);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "加入课程《轻食尚，享瘦有态度》成功 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btn1 = (ImageButton) view.findViewById(R.id.imageButton1);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "加入课程《新生训练营》成功 ! ",
                        Toast.LENGTH_SHORT).show();
            }

        });

        //return inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }
}