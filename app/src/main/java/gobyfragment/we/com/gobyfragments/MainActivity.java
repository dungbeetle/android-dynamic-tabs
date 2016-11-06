package gobyfragment.we.com.gobyfragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        new HttpGetRequest("", new HashMap<String, String>(), handler, Boolean.FALSE, null, null).start();

        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        Bundle bundle = new Bundle();
        bundle.putString("test", "1123");
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("simple").setIndicator(getImageView()),
                WebViewFragment.class, bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putString("test", "112345");
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("contacts").setIndicator("Contacts"),
                WebViewFragment.class, bundle2);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    String value = (String) msg.obj;
                    System.out.println(value);
                    break;
            }
        }
    };

    private View getImageView(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.tab_first);
        return imageView;

    }
}
