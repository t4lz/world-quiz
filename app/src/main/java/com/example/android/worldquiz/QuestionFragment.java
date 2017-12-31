package com.example.android.worldquiz;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by tal on 12/7/17.
 */

public class QuestionFragment extends Fragment {
    private static final String TAG = "QuestionFragment";

    private static final String CONTENT = "page_message";
    private static final String PAGENUM = "page_number";
    private static final String ANSWERSID = "possible_answers_id";
    public static final String USER_ANSWER = "userAnswer";

    String content;
    int pageNumber;
    int answerArrayResourceId;

    Button nextButton;
    TextView questionTitle;
    TextView questionContent;
    EditText userAnswer;
    RelativeLayout answerArea;

    public static QuestionFragment newInstance(String question_content_text, int pageNum, int possibleAnswersId)
    {
        QuestionFragment f = new QuestionFragment();
        Bundle bdl = new Bundle(3);
        bdl.putString(CONTENT, question_content_text);
        bdl.putInt(PAGENUM, pageNum);
        bdl.putInt(ANSWERSID, possibleAnswersId);
        f.setArguments(bdl);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        content = arguments.getString(CONTENT);
        pageNumber = arguments.getInt(PAGENUM);
        answerArrayResourceId = arguments.getInt(ANSWERSID);

        View view = inflater.inflate(R.layout.question_layout, container, false);
        questionTitle= (TextView) view.findViewById(R.id.question_title);
        questionContent = (TextView) view.findViewById(R.id.question_content);
        questionContent.setText(content);
        questionTitle.setText(String.format(getString(R.string.question_title),  pageNumber));

        answerArea = (RelativeLayout) view.findViewById(R.id.answer_area);
        populateAnswerArea();
        nextButton = (Button) view.findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentFragmentNumber = ((MainActivity)getActivity()).getViewPager();
                int totalNumberOfFragments = ((MainActivity)getActivity()).getPagesNumber();
                if (currentFragmentNumber == totalNumberOfFragments - 1) {
                    ((MainActivity)getActivity()).addNextPage();
                }
                ((MainActivity)getActivity()).setViewPager(currentFragmentNumber + 1);
                Log.v(TAG, "Button clicked!");
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            if (userAnswer != null) {
                userAnswer.setText(savedInstanceState.getString(USER_ANSWER));
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        if (userAnswer != null) {
            outState.putString(USER_ANSWER, userAnswer.getText().toString());
        }
    }


    public void populateAnswerArea() {
        userAnswer = new EditText(((MainActivity)getActivity()));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        userAnswer.setLayoutParams(layoutParams);
        userAnswer.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        userAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
        userAnswer.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        userAnswer.setHint(R.string.answer_hint);
        userAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setRightButtonState(shouldEnableButton());
            }
        });
        answerArea.addView(userAnswer);

    }

    public boolean shouldEnableButton(){
        return (userAnswer.getText().toString().length() > 0);
    }

    /**
     * Enable the button if an answer was given, else disable it.
     */
    public void setRightButtonState(boolean shouldEnable){
        nextButton.setEnabled(shouldEnable);
    }
}
