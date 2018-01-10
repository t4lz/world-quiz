package com.example.android.worldquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    TextView greeting;
    TextView scoreSentence;
    Button resetAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        greeting = (TextView) findViewById(R.id.greeting);
        scoreSentence = (TextView) findViewById(R.id.score_sentence);
        Intent intent = getIntent();
        Bundle scoreInfo = intent.getBundleExtra(EndingFragment.EXTRA_BUNDLE);
        int correct = scoreInfo.getInt(EndingFragment.CORRECT_QUESTIONS);
        int total = scoreInfo.getInt(EndingFragment.TOTAL_QUESTIONS);
        boolean[] correctnessArray = scoreInfo.getBooleanArray(EndingFragment.CORRECTNESS_ARRAY);
        int greetingId;
        int greetingColorId;
        float percent = (float) correct / total;
        percent = percent * 100;
        if (percent == 100) {
            greetingId = R.string.perfect_score_message;
            greetingColorId = R.color.color_perfect_score;
        } else if (percent > 80) {
            greetingId = R.string.good_score_message;
            greetingColorId = R.color.color_very_good_score;
        } else if (percent > 60) {
            greetingId = R.string.not_that_good_score_message;
            greetingColorId = R.color.color_ok_score;
        } else if (percent > 0) {
            greetingId = R.string.bad_score_message;
            greetingColorId = R.color.color_bad_score;
        } else {
            greetingId = R.string.nothing_correct_message;
            greetingColorId = R.color.color_nothing_correct;
        }
        greeting.setText(greetingId);
        greeting.setTextColor(getResources().getColor(greetingColorId));
        scoreSentence.setText(String.format(getString(R.string.score_sentence), correct, total));
        resetAppButton = (Button) findViewById(R.id.reset_app_button);
        resetAppButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent i = getBaseContext().getPackageManager()
                                                          .getLaunchIntentForPackage(getBaseContext().getPackageName());
                                                  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                  startActivity(i);
                                              }
                                          }
        );
    }
}
