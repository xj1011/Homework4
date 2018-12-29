package com.example.asus.my_app;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class ForthFragment extends Fragment {
    private ImageButton btn;
    private ImageButton btn1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forth, container, false);
        btn = (ImageButton) view.findViewById(R.id.imageButton10);
        btn1 = (ImageButton) view.findViewById(R.id.imageButton11);





        return view;
    }
}