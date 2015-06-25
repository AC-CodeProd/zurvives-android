package com.lpdw.zurvivescompanion.views.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.R.anim;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

/**
 * Created by CAJUSTE Alain on 24/06/2015.
 */
public class ProgressDialog extends Dialog {
    Context context;
    View view;
    View backView;
    String title;
    TextView titleTextView;
    int progressColor = -1;
    Boolean backViewDismiss = false;

    public ProgressDialog(Context context, String title) {
        super(context, 16973839);
        this.title = title;
        this.context = context;
    }

    public ProgressDialog(Context context, String title, int progressColor) {
        super(context, 16973839);
        this.title = title;
        this.progressColor = progressColor;
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        this.setContentView(com.gc.materialdesign.R.layout.progress_dialog);
        this.view = (RelativeLayout) this.findViewById(com.gc.materialdesign.R.id.contentDialog);
        this.backView = (RelativeLayout) this.findViewById(com.gc.materialdesign.R.id.dialog_rootView);
        this.backView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getX() < (float) ProgressDialog.this.view.getLeft() || event.getX() > (float) ProgressDialog.this.view.getRight() || event.getY() > (float) ProgressDialog.this.view.getBottom() || event.getY() < (float) ProgressDialog.this.view.getTop()) {
                    if (backViewDismiss) {
                        ProgressDialog.this.dismiss();
                    }
                }

                return false;
            }
        });
        this.titleTextView = (TextView) this.findViewById(com.gc.materialdesign.R.id.title);
        this.setTitle(this.title);
        if (this.progressColor != -1) {
            ProgressBarCircularIndeterminate progressBarCircularIndeterminate = (ProgressBarCircularIndeterminate) this.findViewById(com.gc.materialdesign.R.id.progressBarCircularIndetermininate);
            progressBarCircularIndeterminate.setBackgroundColor(this.progressColor);
        }

    }

    public void show() {
        super.show();
        this.view.startAnimation(AnimationUtils.loadAnimation(this.context, com.gc.materialdesign.R.anim.dialog_main_show_amination));
        this.backView.startAnimation(AnimationUtils.loadAnimation(this.context, com.gc.materialdesign.R.anim.dialog_root_show_amin));
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title == null) {
            this.titleTextView.setVisibility(8);
        } else {
            this.titleTextView.setVisibility(0);
            this.titleTextView.setText(title);
        }

    }

    public TextView getTitleTextView() {
        return this.titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }

    public void setBackViewDismiss(Boolean value) {
        this.backViewDismiss = value;
    }

    public void dismiss() {
        Animation anim = AnimationUtils.loadAnimation(this.context, com.gc.materialdesign.R.anim.dialog_main_hide_amination);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ProgressDialog.this.view.post(new Runnable() {
                    public void run() {
                        ProgressDialog.super.dismiss();
                    }
                });
            }
        });
        Animation backAnim = AnimationUtils.loadAnimation(this.context, com.gc.materialdesign.R.anim.dialog_root_hide_amin);
        this.view.startAnimation(anim);
        this.backView.startAnimation(backAnim);
    }
}
