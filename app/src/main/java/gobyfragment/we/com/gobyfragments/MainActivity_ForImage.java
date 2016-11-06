package gobyfragment.we.com.gobyfragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hekeji on 16/11/5.
 */
public class MainActivity_ForImage extends FragmentActivity implements View.OnClickListener {

    private ImageView firstImage, secondImage, thirdImage, fourthImage, fifthImage;
    private ImageView[] ImageViewArray;

    private FrameLayout firstFrame, secondFrame, thirdFrame, fourthFrame, fifthFrame;
    private FrameLayout[] FrameLayoutArray;

    private WebViewFragment firstFragment, secondFragment, thirdFragment, fourthFragment, fifthFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_image);


        firstImage = (ImageView) this.findViewById(R.id.firstImg);
        secondImage = (ImageView) this.findViewById(R.id.secondImg);
        thirdImage = (ImageView) this.findViewById(R.id.thirdImg);
        fourthImage = (ImageView) this.findViewById(R.id.fourthImg);
        fifthImage = (ImageView) this.findViewById(R.id.fifthImg);

        ImageViewArray = new ImageView[]{firstImage, secondImage, thirdImage, fourthImage, fifthImage};

        firstFrame = (FrameLayout) this.findViewById(R.id.firstFrame);
        secondFrame = (FrameLayout) this.findViewById(R.id.secondFrame);
        thirdFrame = (FrameLayout) this.findViewById(R.id.thirdFrame);
        fourthFrame = (FrameLayout) this.findViewById(R.id.fourthFrame);
        fifthFrame = (FrameLayout) this.findViewById(R.id.fifthFrame);

        FrameLayoutArray = new FrameLayout[]{firstFrame, secondFrame, thirdFrame, fourthFrame, fifthFrame};

        firstFrame.setOnClickListener(this);
        secondFrame.setOnClickListener(this);
        thirdFrame.setOnClickListener(this);
        fourthFrame.setOnClickListener(this);
        fifthFrame.setOnClickListener(this);

        new HttpGetRequest("http://47.90.91.22/suface1/config.json",
                new HashMap<String, String>(),
                requestTabs,
                Boolean.FALSE,
                null,
                null).start();


    }

    private Handler requestTabs = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    ApplicationConfig.getInstance().init((String) msg.obj);
                    initImageBackGround();
                    initTabs();
                    break;
            }
        }
    };

    private void initTabs() {
        if (ApplicationConfig.getInstance().getPageModelMap().size() < 5) {
            firstFrame.setVisibility(View.VISIBLE);
            secondFrame.setVisibility(View.VISIBLE);
            thirdFrame.setVisibility(View.VISIBLE);
            fourthFrame.setVisibility(View.VISIBLE);
            fifthFrame.setVisibility(View.GONE);
        }

        if (ApplicationConfig.getInstance().getPageModelMap().size() < 4) {
            firstFrame.setVisibility(View.VISIBLE);
            secondFrame.setVisibility(View.VISIBLE);
            thirdFrame.setVisibility(View.VISIBLE);
            fifthFrame.setVisibility(View.GONE);
            fourthFrame.setVisibility(View.GONE);
        }
        if (ApplicationConfig.getInstance().getPageModelMap().size() < 3) {
            firstFrame.setVisibility(View.VISIBLE);
            secondFrame.setVisibility(View.VISIBLE);
            thirdFrame.setVisibility(View.GONE);
            fifthFrame.setVisibility(View.GONE);
            fourthFrame.setVisibility(View.GONE);
        }

    }

    private void displayImage(String url, ImageView view) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = Options.getImageOptions();
        imageLoader.displayImage(url, view, options);

    }

    private void initImageBackGround() {

        for (Map.Entry<Integer, PageModel> entry : ApplicationConfig.getInstance().getPageModelMap().entrySet()) {
            if (entry.getValue().isSelected()) {
                displayImage(entry.getValue().getSelect(), ImageViewArray[(entry.getKey() - 1)]);
                initSelectedFragement(entry.getKey());
            } else {
                displayImage(entry.getValue().getNomal(), ImageViewArray[(entry.getKey() - 1)]);
            }
        }

    }

    private void initSelectedFragement(int index){
        final PageModel pageModel = ApplicationConfig.getInstance().getPageModelMap().get(index);
        if (pageModel == null) {
            return;
        }
        if (index == 1) {
            initFirst(pageModel.getContent());
        }else if(index == 2){
            initSecond(pageModel.getContent());
        }else if(index == 3){
            initThird(pageModel.getContent());
        }else if(index == 4){
            initForth(pageModel.getContent());
        }else if(index == 5){
            initFifth(pageModel.getContent());
        }
    }

    private void changeTabImageBackground(int index) {
        for (int i = 0; i < ApplicationConfig.getInstance().getPageModelMap().size(); i++) {
            PageModel pageModel = ApplicationConfig.getInstance().getPageModelMap().get(i + 1);
            if (pageModel == null) {
                continue;
            }
            if (i == (index - 1)) {
                displayImage(pageModel.getSelect(), ImageViewArray[i]);
            } else {
                displayImage(pageModel.getNomal(), ImageViewArray[i]);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstFrame:
                final PageModel firstPageModel = ApplicationConfig.getInstance().getPageModelMap().get(1);
                if (firstPageModel != null) {
                    initFirst(firstPageModel.getContent());
                    changeTabImageBackground(1);
                }
                break;
            case R.id.secondFrame:
                final PageModel secondTab = ApplicationConfig.getInstance().getPageModelMap().get(2);
                if (secondTab != null) {
                    initSecond(secondTab.getContent());
                    changeTabImageBackground(2);
                }
                break;
            case R.id.thirdFrame:

                final PageModel thirdTab = ApplicationConfig.getInstance().getPageModelMap().get(3);
                if (thirdTab != null) {
                    initThird(thirdTab.getContent());
                    changeTabImageBackground(3);
                }
                break;
            case R.id.fourthFrame:
                final PageModel fourthTab = ApplicationConfig.getInstance().getPageModelMap().get(4);
                if (fourthTab != null) {
                    initForth(fourthTab.getContent());
                    changeTabImageBackground(4);

                }
                break;
            case R.id.fifthFrame:
                final PageModel fifthTab = ApplicationConfig.getInstance().getPageModelMap().get(5);
                if (fifthTab != null) {
                    initFifth(fifthTab.getContent());
                    changeTabImageBackground(15);
                }
                break;
        }

    }


    private void initFirst(String content) {
        if (firstFragment == null) {
            firstFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", content);
            firstFragment.setArguments(bundle);
        }


        if (!firstFragment.isAdded()) {
            replaceFragmentWithSelected(firstFragment);
        }
    }

    private void initSecond(String content) {

        if (secondFragment == null) {
            secondFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", content);
            secondFragment.setArguments(bundle);
        }
        if (!secondFragment.isAdded()) {
            replaceFragmentWithSelected(secondFragment);
        }
    }

    private void initThird(String content) {

        if (thirdFragment == null) {
            thirdFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", content);
            thirdFragment.setArguments(bundle);
        }

        if (!thirdFragment.isAdded()) {
            replaceFragmentWithSelected(thirdFragment);
        }
    }

    private void initForth(String content) {

        if (fourthFragment == null) {
            fourthFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", content);
            fourthFragment.setArguments(bundle);
        }

        if (!fourthFragment.isAdded()) {
            replaceFragmentWithSelected(fourthFragment);
        }
    }

    private void initFifth(String content) {

        if (fifthFragment == null) {
            fifthFragment = new WebViewFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", content);
        fifthFragment.setArguments(bundle);

        if (!fifthFragment.isAdded()) {
            replaceFragmentWithSelected(fifthFragment);
        }
    }

    private void replaceFragmentWithSelected(Fragment fragment) {

        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
//        MobclickAgent.onPageStart("MAINPAGE");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
//        MobclickAgent.onPageEnd("MAINPAGE");
    }
}
