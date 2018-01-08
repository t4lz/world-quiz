package com.example.android.worldquiz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tal on 12/7/17.
 */

public class EndingFragment extends Fragment {
    private static final String TAG = "WelcomeFragment";
    public static final String BUTTON_STATE = "ButtonState";
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

                boolean[] scoreArray = ((MainActivity)getActivity()).getAnsweredCorrect();
                int correct = 0;
                int total = 0;
                for (int i=0; i<scoreArray.length; i++) {
                    total++;
                    if (scoreArray[i]) {
                        correct++;
                    }
                }
                Context context = getContext();
                String toastMessage = String.format("You've answered %d questions correctly, out of a total of %d questions.", correct, total);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toastMessage, duration);
                toast.show();
            }
        });

        ((MainActivity)getActivity()).setFinalPageReached(nextButton);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            boolean enable = ((MainActivity)getActivity()).shouldEnableSubmit();
            nextButton.setEnabled(enable);

        }
    }

}
