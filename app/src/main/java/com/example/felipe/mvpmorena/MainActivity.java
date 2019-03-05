package com.example.felipe.mvpmorena;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.example.felipe.mvpmorena.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding mBinding; /*Link del video para entender esto
   https://www.youtube.com/watch?v=JqPv4LZ53rU */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }
    public void load(View view){
        animateButtonWidth();
        fadeOutTextAndSetProgressDialog();
        nextAction();
    }
    private void animateButtonWidth(){
        ValueAnimator anim = ValueAnimator.ofInt(mBinding.signInBtn.getMeasuredWidth(),getFinalWidth());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            int value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mBinding.signInBtn.getLayoutParams();
                layoutParams.width=value;
                mBinding.signInBtn.requestLayout();
            }
        });
        anim.setDuration(250);
        anim.start();
    }
    private void fadeOutTextAndSetProgressDialog(){
        mBinding.SignInText.animate().alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
            }
        }).start();
    }

    private void showProgressDialog(){
        mBinding.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#240B3B"), PorterDuff.Mode.SRC_IN);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void nextAction(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                revealButton();
                fadeOutProgressDialog();
                delayedStartNextActivity();

            }

        },2000);
    }
    private void revealButton(){
       mBinding.signInBtn.setElevation(0f);
       mBinding.revealView.setVisibility(View.VISIBLE);

       int x = mBinding.revealView.getWidth();
       int y = mBinding.revealView.getHeight();

       int starX =(int) (getFinalWidth()/ 2 + mBinding.signInBtn.getX());
       int starY = (int)(getFinalWidth()/ 2 +mBinding.signInBtn.getY());
       float radius = Math.max(x,y) * 1.2f;
       Animator reveal = ViewAnimationUtils.createCircularReveal(mBinding.revealView, starX, starY, getFinalWidth(), radius);
       reveal.setDuration(350);
       reveal.addListener(new AnimatorListenerAdapter() {
           @Override
           public void onAnimationEnd(Animator animation) {
               super.onAnimationEnd(animation);
               finish();
           }
       });
       reveal.start();

    }

    private void fadeOutProgressDialog(){
        mBinding.progressBar.animate().alpha(0f).setDuration(200).start();
    }
    private void delayedStartNextActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));

            }
        }, 1000);

    }
    private int getFinalWidth(){
        return (int) getResources().getDimension(R.dimen.get_width);
    }
}

