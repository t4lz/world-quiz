package com.example.android.worldquiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by tal on 12/19/17.
 */

public class MultipleChoiseQuestionFragment extends QuestionFragment {
    private static final String TAG = "MultipleAnswerQuestionFragment";

    private static final String CONTENT = "pageMessage";
    private static final String PAGENUM = "pageNumber";
    private static final String ANSWERSID = "possibleAnswersId";
    public static final String CHECKED_BUTTON = "checkedButton";
    public static final String CORRECT_ANSWER_ID = "correctAnswerId";

    RadioButton[] answerRadioButtons;
    int checkedButton;
    RadioGroup answerListRadioGroup;

    public static MultipleChoiseQuestionFragment newInstance(String question_content_text, int pageNum, int possibleAnswersId, int correctAnswerId)
    {
        MultipleChoiseQuestionFragment f = new MultipleChoiseQuestionFragment();
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
            checkedButton = savedInstanceState.getInt(CHECKED_BUTTON);
            if (checkedButton != -1){
                answerListRadioGroup.check(checkedButton);
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        checkedButton = -1;
        for (int i=0; i < answerRadioButtons.length; i++) {
            if (answerRadioButtons[i].isChecked()) {
                checkedButton = i;
            }
        }
        checkedButton = answerListRadioGroup.getCheckedRadioButtonId();
        outState.putInt(CHECKED_BUTTON, checkedButton);
    }

    @Override
    public void populateAnswerArea() {
        correctAnswer = getString(correctAnswerResourceId);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        answerListRadioGroup = (RadioGroup) inflater.inflate(R.layout.multiple_choice_layout, null, false);
        String[] answers = getResources().getStringArray(answerArrayResourceId);

        answerRadioButtons = new RadioButton[answers.length];
        for (int i=0; i < answers.length; i++) {
            answerRadioButtons[i] = new RadioButton(((MainActivity)getActivity()));
            answerRadioButtons[i].setId(i);
            answerRadioButtons[i].setText(answers[i]);
            answerRadioButtons[i].setTypeface(Typeface.create("serif", Typeface.NORMAL));
            answerRadioButtons[i].setPadding(getResources().getDimensionPixelSize(R.dimen.space_between_button_and_answer),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_vertical),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_horizontal),
                    getResources().getDimensionPixelSize(R.dimen.space_between_answers_vertical));
            answerListRadioGroup.addView(answerRadioButtons[i]);
        }
        answerArea.addView(answerListRadioGroup);
        answerListRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setRightButtonState(true);
                ((MainActivity)getActivity()).setCorrectAnswerState(pageNumber,
                        (answerRadioButtons[answerListRadioGroup.getCheckedRadioButtonId()].
                        getText().toString() == correctAnswer));
            }
        });
    }
}
