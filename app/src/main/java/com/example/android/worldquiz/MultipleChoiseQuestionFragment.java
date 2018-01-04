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

    private static final String CONTENT = "page_message";
    private static final String PAGENUM = "page_number";
    private static final String ANSWERSID = "possible_answers_id";
    public static final String CHECKED_BUTTON = "checkedButton";

    RadioButton[] answerRadioButtons;
    int checkedButton;

    public static MultipleChoiseQuestionFragment newInstance(String question_content_text, int pageNum, int possibleAnswersId)
    {
        MultipleChoiseQuestionFragment f = new MultipleChoiseQuestionFragment();
        Bundle bdl = new Bundle(3);
        bdl.putString(CONTENT, question_content_text);
        bdl.putInt(PAGENUM, pageNum);
        bdl.putInt(ANSWERSID, possibleAnswersId);
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
                answerRadioButtons[checkedButton].setChecked(true);
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
        outState.putInt(CHECKED_BUTTON, checkedButton);
    }

    @Override
    public void populateAnswerArea() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        RadioGroup answerListRadioGroup = (RadioGroup) inflater.inflate(R.layout.multiple_choice_layout, null, false);
        String[] answers = getResources().getStringArray(answerArrayResourceId);

        answerRadioButtons = new RadioButton[answers.length];
        for (int i=0; i < answers.length; i++) {
            answerRadioButtons[i] = new RadioButton(((MainActivity)getActivity()));
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
                switch (checkedId) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
