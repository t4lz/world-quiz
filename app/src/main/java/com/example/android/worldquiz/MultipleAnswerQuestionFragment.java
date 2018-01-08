package com.example.android.worldquiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by tal on 12/19/17.
 */

public class MultipleAnswerQuestionFragment extends QuestionFragment {
    private static final String TAG = "MultipleAnswerQuestionFragment";

    private static final String CONTENT = "pageMessage";
    private static final String PAGENUM = "pageNumber";
    private static final String ANSWERSID = "possibleAnswersId";
    public static final String CHECKED_STATE = "checkedState";
    public static final String CORRECT_ANSWER_ID = "correctAnswerId";

    CheckBox[] answerCheckBoxes;
    boolean[] checkedState;
    String[] correctAnswers;
    String joinedAnswers;

    public static MultipleAnswerQuestionFragment newInstance(String question_content_text, int pageNum, int possibleAnswersId, int correctAnswerId)
    {
        MultipleAnswerQuestionFragment f = new MultipleAnswerQuestionFragment();
        Bundle bdl = new Bundle(3);
        bdl.putString(CONTENT, question_content_text);
        bdl.putInt(PAGENUM, pageNum);
        bdl.putInt(ANSWERSID, possibleAnswersId);
        bdl.putInt(CORRECT_ANSWER_ID, correctAnswerId);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            checkedState = savedInstanceState.getBooleanArray(CHECKED_STATE);
            for (int i=0; i < answerCheckBoxes.length; i++) {
                answerCheckBoxes[i].setChecked(checkedState[i]);
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        checkedState = new boolean[answerCheckBoxes.length];
        for (int i=0; i < answerCheckBoxes.length; i++) {
            checkedState[i] = answerCheckBoxes[i].isChecked();
        }
        outState.putBooleanArray(CHECKED_STATE, checkedState);
    }

    @Override
    public void populateAnswerArea() {
        correctAnswers = getResources().getStringArray(correctAnswerResourceId);
        StringBuilder builder = new StringBuilder();
        for(String s : correctAnswers) {
            builder.append(s);
        }
        joinedAnswers = builder.toString();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout answersListLinearLayout = (LinearLayout) inflater.inflate(R.layout.multiple_answer_layout, null, false);
        String[] answers = getResources().getStringArray(answerArrayResourceId);
        answerCheckBoxes = new CheckBox[answers.length];
        for (int i=0; i < answers.length; i++) {
            answerCheckBoxes[i] = new CheckBox(((MainActivity)getActivity()));
            answerCheckBoxes[i].setText(answers[i]);
            answerCheckBoxes[i].setTypeface(Typeface.create("serif", Typeface.NORMAL));
            answerCheckBoxes[i].setPadding(getResources().getDimensionPixelSize(R.dimen.space_between_button_and_answer),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_vertical),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_horizontal),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_vertical));
            answerCheckBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked) {
                        setRightButtonState(isChecked);
                    }
                    else {
                        setRightButtonState(shouldEnableButton());
                    }
                    ((MainActivity)getActivity()).setCorrectAnswerState(pageNumber, checkAnswer());
                }
            });
            answersListLinearLayout.addView(answerCheckBoxes[i]);
        }
        answerArea.addView(answersListLinearLayout);
    }

    @Override
    public boolean shouldEnableButton() {
        boolean should = false;
        for (int i=0; i<answerCheckBoxes.length && !should; i++) {
            should = answerCheckBoxes[i].isChecked();
        }
        return should;
    }

    public boolean checkAnswer(){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < answerCheckBoxes.length; i++){
            if(answerCheckBoxes[i].isChecked()) {
                builder.append(answerCheckBoxes[i].getText().toString());
            }
        }
        return (builder.toString().equals(joinedAnswers));
    }
}
