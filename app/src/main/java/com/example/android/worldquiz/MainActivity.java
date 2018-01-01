package com.example.android.worldquiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    public static final String NUM_OF_FRAGMENTS = "numOfFragments";
    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    Queue<Fragment> questionFragments = new ArrayDeque<>();
    String[] questions;
    String[] answers;
    SectionsStatePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        setupViewPager(mViewPager);
        if (savedInstanceState != null){
            int numOfFragments = savedInstanceState.getInt(NUM_OF_FRAGMENTS);
            for (int i=1; i<numOfFragments; i++){
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

    private void setupViewPager (ViewPager viewPager){
        adapter.addFragment(new WelcomeFragment());
        questionFragments.add(QuestionFragment.newInstance(questions[0], 1, 0));
        questionFragments.add(MultipleAnswerQuestionFragment.newInstance(questions[1], 2, R.array.options1));
        questionFragments.add(MultipleChoiseQuestionFragment.newInstance(questions[2], 3, R.array.options2));
        questionFragments.add(MultipleChoiseQuestionFragment.newInstance(questions[3], 4, R.array.options3));
        questionFragments.add(MultipleAnswerQuestionFragment.newInstance(questions[4], 5, R.array.options4));
        questionFragments.add(QuestionFragment.newInstance(questions[5], 6, 0));
        questionFragments.add(new EndingFragment());
        viewPager.setAdapter(adapter);
    }

    public void addNextPage() {
        adapter.addFragment(questionFragments.remove());
    }



    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public int getViewPager(){
        return mViewPager.getCurrentItem();
    }

    public int getPagesNumber() {
        return mViewPager.getAdapter().getCount();
    }
}
