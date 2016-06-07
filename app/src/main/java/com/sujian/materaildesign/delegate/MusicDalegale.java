package com.sujian.materaildesign.delegate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.MusicActivity;


import net.qiujuer.genius.blur.StackBlur;

import butterknife.BindView;

/**
 * Created by sujian on 2016/6/3.
 * Mail:121116111@qq.com
 */
public class MusicDalegale extends AppDelegate {
    @BindView(R.id.rl_music)
    RelativeLayout rl_music;

    @BindView(R.id.tb_music)
    Toolbar tb_music;

    private MusicActivity musicActivity;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initBG();
    }


    @Override
    public Toolbar getToolbar() {
        tb_music.setTitle("年轻的战场");
        return super.getToolbar();
    }


    @Override
    public int getOptionsMenuId() {
        return R.menu.music_menu;
    }

    /**
     * 半透明状态栏
     */
    public void initWindow() {
        musicActivity = getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = musicActivity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#50000000"));
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private void initBG() {
        rl_music.setBackgroundResource(R.mipmap.iv_splash);
        final Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.iv_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap b = StackBlur.blurNatively(bitmap, 50, false);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl_music.setBackgroundDrawable(new BitmapDrawable(getActivity().getResources(), b));
                    }
                });
            }
        }).start();


    }
}
