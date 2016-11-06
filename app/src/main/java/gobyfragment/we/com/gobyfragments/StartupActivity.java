package gobyfragment.we.com.gobyfragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by hekeji on 16/10/27.
 */
public class StartupActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        ImageView imageView = (ImageView) findViewById(R.id.startup);

        // 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
//
//        Animation operatingAnim = AnimationUtils.loadAnimation(this,
//                R.anim.logo_anim);
        Animation operatingAnim = new AlphaAnimation(0.1f, 1.0f);
        //渐变时间
        operatingAnim.setDuration(1500);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        // 给view设置动画效果
        imageView.startAnimation(operatingAnim);
        operatingAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            // 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
            // 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
            // 达到持续显示第一屏500毫秒的效果
            @Override
            public void onAnimationEnd(Animation arg0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent applyIntent = new Intent(StartupActivity.this, MainActivity_ForImage.class);
                        startActivity(applyIntent);
                        finish();
                    }
                }, 1);
            }
        });
    }
}
