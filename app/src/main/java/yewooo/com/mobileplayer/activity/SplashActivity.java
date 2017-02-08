package yewooo.com.mobileplayer.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;

import yewooo.com.mobileplayer.R;


public class SplashActivity extends Activity {

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);

    }

    private void startMainActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }
}
