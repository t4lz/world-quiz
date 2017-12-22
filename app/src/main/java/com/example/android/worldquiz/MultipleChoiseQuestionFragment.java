package com.example.android.worldquiz;

import android.graphics.Typeface;
import android.os.Bundle;
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

    RadioButton[] answerRadioButtons;

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
    public void populateAnswerArea() {
        RadioGroup answerListRadioGroup = new RadioGroup(((MainActivity)getActivity()));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        answerListRadioGroup.setLayoutParams(layoutParams);
        answerListRadioGroup.setOrientation(LinearLayout.VERTICAL);
        String[] answers = getResources().getStringArray(answerArrayResourceId);

        answerRadioButtons = new RadioButton[answers.length];
        for (int i=0; i < answers.length; i++) {
            answerRadioButtons[i] = new RadioButton(((MainActivity)getActivity()));
            answerRadioButtons[i].setText(answers[i]);
            answerRadioButtons[i].setTypeface(Typeface.create("serif", Typeface.NORMAL));
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
