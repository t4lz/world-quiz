package com.example.android.worldquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by tal on 12/7/17.
 */

public class QuestionFragment extends Fragment {
    private static final String TAG = "QuestionFragment";

    private static final String TITLE = "page_title";
    private static final String CONTENT = "page_message";

    String title;
    String content;

    Button nextButton;
    TextView questionTitle;
    TextView questionContent;
    EditText userAnswer;

    public static QuestionFragment newInstance(String question_title_text, String question_content_text)
    {
        QuestionFragment f = new QuestionFragment();
        Bundle bdl = new Bundle(2);
        bdl.putString(TITLE, question_title_text);
        bdl.putString(CONTENT, question_content_text);
        f.setArguments(bdl);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        title = arguments.getString(TITLE);
        content = arguments.getString(CONTENT);

        View view = inflater.inflate(R.layout.question_layout, container, false);
        questionTitle= (TextView) view.findViewById(R.id.question_title);
        questionContent = (TextView) view.findViewById(R.id.question_content);
        questionTitle.setText(title);
        questionContent.setText(content);
        
        userAnswer = (EditText) view.findViewById(R.id.answer_edit_text);
        userAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableButtonIfText();
            }
        });

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

    /**
     * Enable the button if an answer was given, else disable it.
     */
    public void enableButtonIfText(){
        nextButton.setEnabled(userAnswer.getText().toString().length()>0);
    }
}
