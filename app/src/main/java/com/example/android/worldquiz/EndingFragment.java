package com.example.android.worldquiz;

import android.content.Context;
import android.content.Intent;
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
    public static final String TOTAL_QUESTIONS = "totalQuestions";
    public static final String CORRECT_QUESTIONS = "correctQuestions";
    public static final String CORRECTNESS_ARRAY = "correctnessArray";
    public static final String EXTRA_BUNDLE = "extraBundle";
    Button nextButton;
    TextView mainTitle;
    TextView secondTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);
        mainTitle = (TextView) view.findViewById(R.id.main_title);
        secondTitle = (TextView) view.findViewById(R.id.second_title);
        mainTitle.setText(R.string.end_message);
        secondTitle.setText(R.string.submit_message);
        nextButton = (Button) view.findViewById(R.id.start);
        nextButton.setText(R.string.submit_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Button clicked!");
                boolean[] scoreArray = ((MainActivity) getActivity()).getAnsweredCorrect();
                int correct = 0;
                int total = 0;
                for (int i = 0; i < scoreArray.length; i++) {
                    total++;
                    if (scoreArray[i]) {
                        correct++;
                    }
                }
                Context context = getContext();
                String toastMessage = String.format(getString(R.string.toast_text), correct, total);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toastMessage, duration);
                toast.show();
                Intent intent = new Intent(((MainActivity) getActivity()), EndActivity.class);
                Bundle extra = new Bundle();
                extra.putInt(TOTAL_QUESTIONS, total);
                extra.putInt(CORRECT_QUESTIONS, correct);
                extra.putBooleanArray(CORRECTNESS_ARRAY, scoreArray);
                intent.putExtra(EXTRA_BUNDLE, extra);
                startActivity(intent);
            }
        });
        ((MainActivity) getActivity()).setFinalPageReached(nextButton);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            boolean enable = ((MainActivity) getActivity()).shouldEnableSubmit();
            nextButton.setEnabled(enable);

        }
    }

}
