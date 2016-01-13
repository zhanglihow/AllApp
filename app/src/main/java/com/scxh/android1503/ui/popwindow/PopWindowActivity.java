package com.scxh.android1503.ui.popwindow;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scxh.android1503.R;
import com.scxh.android1503.ui.adapter.MessageBean;
import com.scxh.android1503.util.Logs;

public class PopWindowActivity extends Activity {
	private static final int TYPE_PICTURE = 0;// 图片
	private static final int TYPE_TEXT = 1; // 文本
	private TextView mPopWindowTxt;
	private PopupWindow mPopupWindow;
	private boolean hasMeasured;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_window_layout);
		mPopWindowTxt = (TextView) findViewById(R.id.pop_window_btn);

		mPopupWindow = new PopupWindow(getPopContentView());

		mPopWindowTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				} else {
					mPopupWindow.showAsDropDown(v);
				}
			}
		});

		ViewTreeObserver vto = mPopWindowTxt.getViewTreeObserver();

		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				if (hasMeasured == false) {
					int btWidth = mPopWindowTxt.getMeasuredWidth();
					int btHeight = mPopWindowTxt.getMeasuredHeight();
					Logs.v("btWidth ：" + btWidth);
					
					mPopupWindow.setWidth(btWidth);
					mPopupWindow.setHeight(500);
					hasMeasured = true;
				}
				return true;
			}
		});

	}

	/**
	 * 弹出窗口内容View
	 * 
	 * @return
	 */
	public View getPopContentView() {
		View v = LayoutInflater.from(this).inflate(
				R.layout.pop_window_content_layout, null);
		ListView listView = (ListView) v;

		MyMessageAdapter adapter = new MyMessageAdapter(this);
		listView.setAdapter(adapter);

		adapter.setListData(getData());

		return listView;
	}

	public ArrayList<MessageBean> getData() {
		ArrayList<MessageBean> listData = new ArrayList<MessageBean>();

		MessageBean msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("1【多店通用】乡村基");
		msg.setContent("20元代金券！全场通用，可叠加使用，提供免费WiFi！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("2【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_PICTURE);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("3【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m3);
		msg.setTitle("4【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_PICTURE);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m4);
		msg.setTitle("5【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("6【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		msg = new MessageBean();
		msg.setIcon(R.drawable.m8);
		msg.setTitle("7【多店通用】廖记棒棒鸡");
		msg.setContent("32元代金券！全场通用，可叠加使用，节假日通用！");
		msg.setType(TYPE_TEXT);
		listData.add(msg);

		return listData;

	}

	public class MyMessageAdapter extends BaseAdapter {
		private ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		private LayoutInflater layoutInflater;

		public MyMessageAdapter(Context context) {
			layoutInflater = LayoutInflater.from(context);
		}

		public void setListData(ArrayList<MessageBean> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			ViewHodler holder;
			if (convertView == null) {
				v = layoutInflater.inflate(
						R.layout.adapter_simple_item1_layout, null);

				holder = new ViewHodler();
				holder.iconImg = (ImageView) v
						.findViewById(R.id.simple_icon_img);
				holder.titleTxt = (TextView) v
						.findViewById(R.id.simple_title_txt);
				holder.contentTxt = (TextView) v
						.findViewById(R.id.simple_content_txt);

				v.setTag(holder);
			} else {
				v = convertView;
				holder = (ViewHodler) v.getTag();
			}

			MessageBean msg = (MessageBean) getItem(position);

			if (msg.getType() == TYPE_PICTURE) {
				holder.contentTxt.setText("多优惠");
				holder.contentTxt.setBackgroundColor(getResources().getColor(
						android.R.color.holo_orange_light));
			} else {
				holder.contentTxt.setText(msg.getContent());
			}

			holder.iconImg.setImageResource(msg.getIcon());
			holder.titleTxt.setText(msg.getTitle());

			return v;
		}

		public class ViewHodler {
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
		}
	}

}
