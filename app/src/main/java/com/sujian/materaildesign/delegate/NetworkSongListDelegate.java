package com.sujian.materaildesign.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.MusicListAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.presenter.NetworkSongListActivity;
import com.sujian.materaildesign.uitls.UIUitls;
import com.sujian.materaildesign.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在线音乐列表
 * Created by sujian on 2016/7/30.
 * Mail:121116111@qq.com
 */
public class NetworkSongListDelegate extends AppDelegate {

    private LinearLayoutManager manager;
    private List<Song> list;
    private NetworkSongListActivity activity;
    private MusicListAdapter adapter;

    @BindView(R.id.tb_network)
    Toolbar tb_network;
    @BindView(R.id.iv_network)
    ImageView iv_network;
    @BindView(R.id.rv_network)
    RecyclerView rv_network;
    @BindView(R.id.srl_network)
    SwipeRefreshLayout srl_network;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_network_song_list;
    }


    @Override
    public Toolbar getToolbar() {
        tb_network.setTitle("");
        return tb_network;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initWindow();
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        rv_network.setHasFixedSize(true);
        activity = getActivity();
        list = new ArrayList<>();


        srl_network.setProgressViewOffset(false, 0, UIUitls.dip2px(getActivity(), 24));
        srl_network.setRefreshing(true);

        manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_network.setLayoutManager(manager);

        adapter = new MusicListAdapter(list);
        rv_network.setAdapter(adapter);

        rv_network.addItemDecoration(new SpaceItemDecoration(12));


    }


    /**
     * 刷新view数据
     *
     * @param data
     * @param isLoadMore
     */
    public void refreshRecyclerView(List<Song> data, boolean isLoadMore) {
        if (isLoadMore) {
            list.addAll(data);
        } else {
            list.addAll(0, data);
        }
        srl_network.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }


    /**
     * 设置图片
     *
     * @param url
     */
    public void setBG(String url) {
        Glide.with(getActivity())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(iv_network);
    }

    public MusicListAdapter getAdapter() {
        return adapter;
    }

    public List<Song> getList() {
        return list;
    }

    public LinearLayoutManager getManager() {
        return manager;
    }
}
