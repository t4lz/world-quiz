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

import org.w3c.dom.Text;

/**
 * Created by tal on 12/7/17.
 */

public class QuestionFragment extends Fragment {
    private static final String TAG = "QuestionFragment";

    private static final String TITLE = "page_title";
    private static final String MESSAGE = "page_message";

    String title;
    String message;

    Button nextButton;
    TextView question_title;
    TextView question_content;

    public static QuestionFragment newInstance(String title, String message)
    {
        QuestionFragment f = new QuestionFragment();
        Bundle bdl = new Bundle(2);
        bdl.putString(TITLE, title);
        bdl.putString(MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        title = arguments.getString(TITLE);
        message = arguments.getString(MESSAGE);

        View view = inflater.inflate(R.layout.question_layout, container, false);
        question_title = (TextView) view.findViewById(R.id.question_title);
        question_content = (TextView) view.findViewById(R.id.question_content);

        question_title.setText(title);
        question_content.setText(message);

        nextButton = (Button) view.findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentFragmentNumber = ((MainActivity)getActivity()).getViewPager();
                ((MainActivity)getActivity()).setViewPager(currentFragmentNumber + 1);
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
