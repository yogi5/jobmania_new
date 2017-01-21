package com.youngindia.jobportal.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.youngindia.jobportal.R;
import com.youngindia.jobportal.ui.Employee_HomeActivity;
import com.youngindia.jobportal.ui.LoginUser_Activity;



public class Dialog_Advertisement extends Activity implements Animation.AnimationListener {
    Animation animblink ,animation;
    int SPLASH_TIME_OUT = 5000;
    public void showDialog(final Activity activity, String msg, Bitmap img) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        final Dialog dialog = new Dialog(activity, R.style.MyDialog);


        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_advertistment, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.politician_pic);
        imageView.setImageBitmap(img);
        final TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(msg);
//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(R.style.MyDialog);
        dialog.show();

        animblink = AnimationUtils.loadAnimation(activity, R.anim.slide_down);
        animblink.setAnimationListener(this);

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        //textView.setVisibility(View.VISIBLE);
        textView.startAnimation(animblink);


    }

    //   });
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                dialog.dismiss();
            }
        }, SPLASH_TIME_OUT);*/

    LoginUser_Activity loginUser_activity= new LoginUser_Activity();
    @Override
    public void onAnimationStart (Animation animation)
    {

    }

    @Override
    public void onAnimationEnd (Animation animation){

        if (animation == animblink) {
            //      Toast.makeText(loginUser_activity, "Animation Stopped", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAnimationRepeat (Animation animation){

    }
}