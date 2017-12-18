package com.example.android.worldquiz;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    String[] questions;
    String[] answers;
    SectionsStatePagerAdapter adapter;
    Queue<QuestionFragment> questionsQueue = new LinkedList<QuestionFragment>();

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
    }


    private void setupViewPager (ViewPager viewPager){
        adapter.addFragment(new WelcomeFragment());
        adapter.addFragment(QuestionFragment.newInstance("First Question", questions[0]));
        questionsQueue.add(QuestionFragment.newInstance("Second Question", questions[1]));
        viewPager.setAdapter(adapter);
    }

    public void addNextPage() {
        adapter.addFragment(questionsQueue.remove());
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
