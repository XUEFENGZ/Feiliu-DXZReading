package com.wanglei.mylover.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wanglei.mylover.R;
import com.wanglei.mylover.adapter.CommentListAdapter;
import com.wanglei.mylover.adapter.ZanListAdapter;
import com.wanglei.mylover.base.BaseFragment;

public class CommentFragment extends BaseFragment{

	private PullToRefreshListView mPullToRefreshView;
	private CommentListAdapter zanListAdapter;
	private List<String> list;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.comment, null);
		mPullToRefreshView = (PullToRefreshListView) view
				.findViewById(R.id.comment_pull_refresh_view);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		list = new ArrayList<String>();
		for (int i = 1; i < 4; i++) {
			list.add("项羽" + i + " 评论了");
		}
		zanListAdapter = new CommentListAdapter(ct, list);
		mPullToRefreshView.setAdapter(zanListAdapter);
		initPullToRefreshListView();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initPullToRefreshListView() {
		mPullToRefreshView.setMode(Mode.PULL_FROM_START);
		mPullToRefreshView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase refreshView){
						pullDown();
						zanListAdapter.notifyDataSetChanged();
						handler.sendEmptyMessageAtTime(0, 20000);
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase refreshView) {
					}

				});
	}

	private void pullDown() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				list.add("虞姬   评论了");
			}
		}).start();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 0:
				mPullToRefreshView.onRefreshComplete();
				break;
			}
		};
	};
}
