package com.example.android.worldquiz;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayDeque;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String NUM_OF_FRAGMENTS = "numOfFragments";
    private ViewPager mViewPager;
    Queue<Fragment> questionFragments = new ArrayDeque<>();
    String[] questions;
    SectionsStatePagerAdapter adapter;
    boolean[] answered;
    boolean[] answeredCorrect;
    boolean finalPageReached = false;
    Button submitButton;
    boolean enableSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = getResources().getStringArray(R.array.questions);
        answered = new boolean[questions.length];
        answeredCorrect = new boolean[questions.length];
        mViewPager = (ViewPager) findViewById(R.id.container);
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        setupViewPager(mViewPager);
        if (savedInstanceState != null) {
            int numOfFragments = savedInstanceState.getInt(NUM_OF_FRAGMENTS);
            for (int i = 1; i < numOfFragments; i++) {
                addNextPage();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        outState.putInt(NUM_OF_FRAGMENTS, adapter.getCount());
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new WelcomeFragment());
        questionFragments.add(QuestionFragment.newInstance(questions[0], 1, 0, R.string.answer0));
        questionFragments.add(MultipleAnswerQuestionFragment.newInstance(questions[1], 2, R.array.options1, R.array.answer1));
        questionFragments.add(MultipleChoiseQuestionFragment.newInstance(questions[2], 3, R.array.options2, R.string.answer2));
        questionFragments.add(MultipleChoiseQuestionFragment.newInstance(questions[3], 4, R.array.options3, R.string.answer3));
        questionFragments.add(MultipleAnswerQuestionFragment.newInstance(questions[4], 5, R.array.options4, R.array.answer4));
        questionFragments.add(QuestionFragment.newInstance(questions[5], 6, 0, R.string.answer5));
        questionFragments.add(new EndingFragment());
        viewPager.setAdapter(adapter);
    }

    public void addNextPage() {
        adapter.addFragment(questionFragments.remove());
    }


    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public int getViewPager() {
        return mViewPager.getCurrentItem();
    }

    public int getPagesNumber() {
        return mViewPager.getAdapter().getCount();
    }

    public void setAnswerState(int answerNum, boolean state) {
        answered[answerNum - 1] = state;
        if (finalPageReached) {
            boolean newState = isAllTrue(answered);
            Log.d(TAG, String.format("updating submitButton state. is enabled: %b", newState));
            submitButton.setEnabled(newState);
            enableSubmit = newState;
        }
    }

    public boolean shouldEnableSubmit() {
        return enableSubmit;
    }

    public void setCorrectAnswerState(int answerNum, boolean state) {
        answeredCorrect[answerNum - 1] = state;
        Log.d(TAG, String.format("Correct Answers Count: updated question number %d to %b", answerNum, state));
    }

    public void setFinalPageReached(Button button) {
        finalPageReached = true;
        submitButton = button;
    }

    private static boolean isAllTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    public boolean[] getAnsweredCorrect() {
        return answeredCorrect;
    }
}
