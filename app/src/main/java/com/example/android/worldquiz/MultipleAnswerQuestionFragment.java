package com.example.android.worldquiz;

import android.graphics.Typeface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by tal on 12/19/17.
 */

public class MultipleAnswerQuestionFragment extends QuestionFragment {
    private static final String TAG = "MultipleAnswerQuestionFragment";

    CheckBox[] answerCheckBoxes;

    @Override
    public void populateAnswerArea() {
        LinearLayout answersListLinearLayout = new LinearLayout(((MainActivity)getActivity()));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        answersListLinearLayout.setLayoutParams(layoutParams);
        answersListLinearLayout.setOrientation(LinearLayout.VERTICAL);
        String[] answers = getResources().getStringArray(answerArrayResourceId);
        answerCheckBoxes = new CheckBox[answers.length];
        for (int i=0; i < answers.length; i++) {
            answerCheckBoxes[i] = new CheckBox(((MainActivity)getActivity()));
            answerCheckBoxes[i].setText(answers[i]);
            answerCheckBoxes[i].setTypeface(Typeface.create("serif", Typeface.NORMAL));
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
}
