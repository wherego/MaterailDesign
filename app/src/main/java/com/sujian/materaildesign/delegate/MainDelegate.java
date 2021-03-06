package com.sujian.materaildesign.delegate;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;


import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.IntroductionFragment;
import com.sujian.materaildesign.presenter.MainActivity;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;
import com.sujian.materaildesign.uitls.T;

import java.util.Collection;

import butterknife.BindView;

/**
 * 主页视图
 * Created by sujian on 2016/5/29.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_1)
public class MainDelegate extends BaseViewPagerDelegate {

    @BindView(R.id.main_toorbar)
    Toolbar main_toorbar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle(R.string.sujian);
        return get(R.id.main_toorbar);
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        IntroductionFragment introductionFragment = new IntroductionFragment();
        fragments.add(introductionFragment);
        titles.add("介绍");
        baseFragmentPagerAdapter.notifyDataSetChanged();
    }



}
