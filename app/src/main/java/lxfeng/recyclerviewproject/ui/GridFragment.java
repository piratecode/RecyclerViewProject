package lxfeng.recyclerviewproject.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import lxfeng.recyclerviewproject.R;
import lxfeng.recyclerviewproject.view.BaseRecyclerAdapter;
import lxfeng.recyclerviewproject.view.CustomRecyclerView;

public class GridFragment extends BaseFragment {

    private CustomRecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;

    private ArrayList<String> mObjectList = new ArrayList<String>();

    public static GridFragment newInstance() {
        GridFragment fragment = new GridFragment();
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
        // Inflate the layout for this fragment
        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.recyclerview);
        setupRecyclerView();
        requestData();
    }

    private void setupRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mObjectList);
        mMyAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
            }
        });

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
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindVH(RecyclerView.ViewHolder holder, int position) {

        }

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

    }


}
