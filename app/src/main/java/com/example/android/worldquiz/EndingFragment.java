package com.example.android.worldquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tal on 12/7/17.
 */

public class EndingFragment extends Fragment {
    private static final String TAG = "WelcomeFragment";
    Button nextButton;
    TextView mainTitle;
    TextView secondTitle;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);
        mainTitle = (TextView) view.findViewById(R.id.main_title);
        secondTitle = (TextView) view.findViewById(R.id.second_title);
        mainTitle.setText("You've answered all the questions!");
        secondTitle.setText("Submit your answers and get your results!");

        nextButton = (Button) view.findViewById(R.id.start);
        nextButton.setText("Submit");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Button clicked!");
            }
        });


        return view;
    }
}
