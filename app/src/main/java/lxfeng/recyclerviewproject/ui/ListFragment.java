package lxfeng.recyclerviewproject.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lxfeng.recyclerviewproject.R;
import lxfeng.recyclerviewproject.view.BaseRecyclerAdapter;
import lxfeng.recyclerviewproject.view.CustomRecyclerView;

public class ListFragment extends BaseFragment {
    private CustomRecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;

    private ArrayList<String> mObjectList = new ArrayList<String>();

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recyclerview);
        setupRecyclerView();
        requestData();
    }

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mObjectList);

        ImageView imageView1 = new ImageView(context);
        imageView1.setImageResource(R.drawable.one);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView1.setLayoutParams(
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageView imageView2 = new ImageView(context);
        imageView2.setImageResource(R.drawable.one);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView2.setLayoutParams(
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageView imageView3 = new ImageView(context);
        imageView3.setImageResource(R.drawable.one);
        imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView3.setLayoutParams(
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        mRecyclerView.addHeaderView(imageView1);
        mRecyclerView.addHeaderView(imageView2);
        mRecyclerView.addFooterView(imageView3);
        mRecyclerView.setAdapter(mMyAdapter);
        mMyAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void requestData() {
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                list.add(i + "");
            }
            mObjectList.addAll(list);
            mRecyclerView.getAdapter().notifyDataSetChanged();
            //    sendEmptyMessageDelayed(1, 5000);
        }
    };

    private class MyAdapter extends BaseRecyclerAdapter<String> {

        public MyAdapter(ArrayList<String> objects) {
            super(objects);
        }

        @Override
        public RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType) {
            TextView textView = new TextView(context);
            return new MyViewHolder(textView);
        }

        @Override
        public void onBindVH(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).setItemView(objects.get(position));
        }

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                    80));
            textView.setTextColor(0xffff0000);
            textView.setBackgroundColor(0xffffffff);
        }

        public void setItemView(String string) {
            textView.setText(string);
        }

    }

}
