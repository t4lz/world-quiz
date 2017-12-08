package com.example.android.worldquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by tal on 12/7/17.
 */

public class QuestionFragment extends Fragment {
    private static final String TAG = "QuestionFragment";
    Button nextButton;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);
        nextButton = (Button) view.findViewById(R.id.start);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Button clicked!");
            }
        });


        return view;
    }

    public void nextFragment(View view){
        int currentFragmentNumber = ((MainActivity)getActivity()).getViewPager();
        ((MainActivity)getActivity()).setViewPager(currentFragmentNumber + 1);
    }
}
