package com.example.resumeparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ResumeDetailActivity extends AppCompatActivity {

    ImageButton parseFileExpandButton;
    LinearLayout parseFileExpand;
    NestedScrollView nestedContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        parseFileExpandButton = findViewById(R.id.parseFileExpandButton);
        parseFileExpand = findViewById(R.id.parseFileExpand);
        nestedContent = findViewById(R.id.nestedContent);

        //showing error due to commented section
       // parseFileExpandButton.setOnClickListener(view1 -> toggleSectionText(parseFileExpandButton));


        /*new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, MainScreen.class));
            finish();
        }, 1000);*/
    }

    /*public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    private void toggleSectionText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            expand(parseFileExpand, () -> nestedScrollTo(nestedContent, parseFileExpand));
        } else {
            collapse(parseFileExpand);
        }
    }

    public interface AnimListener {
        void onFinish();
    }

    public static void expand(final View v, final ExpansionPanel1000.AnimListener animListener) {
        Animation a = expandAction(v);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animListener.onFinish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(a);
    }

    private static Animation expandAction(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetedHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetedHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (targetedHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        return a;
    }

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(() -> nested.scrollTo(500, targetView.getBottom()));
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }*/



}