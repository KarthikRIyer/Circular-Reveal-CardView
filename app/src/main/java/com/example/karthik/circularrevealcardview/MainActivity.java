package com.example.karthik.circularrevealcardview;

import android.animation.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    CardView revealView;
    float pixelDensity;
    boolean flg = true;
    Animation alphaAnimation;
    FloatingActionButton floatingActionButton;
    TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        pixelDensity = getResources().getDisplayMetrics().density;

        imageView = (ImageView)findViewById(R.id.image_view);

        revealView = (CardView)findViewById(R.id.card_view);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });

        alphaAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);

    }

    public void launch(){
        int x = revealView.getRight();
        int y = revealView.getBottom();

        x -= (8*pixelDensity + 28*pixelDensity);
        //y -= (8*pixelDensity + 28*pixelDensity);

        int hyp = (int)Math.hypot(revealView.getWidth(),revealView.getHeight());

        if(flg){
            floatingActionButton.setImageResource(R.drawable.baseline_cancel_black_24);

            Animator animator = ViewAnimationUtils.createCircularReveal(revealView,x,y,0,hyp);
            animator.setDuration(700);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView.startAnimation(alphaAnimation);
                    textView2.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            revealView.setVisibility(View.VISIBLE);
            animator.start();
            flg = false;
        }
        else {
            floatingActionButton.setImageResource(R.drawable.baseline_view_agenda_black_24);
            Animator animator = ViewAnimationUtils.createCircularReveal(revealView,x,y,hyp,0);
            animator.setDuration(400);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    revealView.setVisibility(View.GONE);
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
            flg = true;
        }
    }
}
