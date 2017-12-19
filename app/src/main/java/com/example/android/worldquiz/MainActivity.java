package com.example.android.worldquiz;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

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
    }


    private void setupViewPager (ViewPager viewPager){
        adapter.addFragment(new WelcomeFragment());
        adapter.addFragment(QuestionFragment.newInstance(questions[0], 1, 0));
        questionFragments.add(QuestionFragment.newInstance(questions[1], 2, 0));
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
