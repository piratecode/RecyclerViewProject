package lxfeng.recyclerviewproject.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import lxfeng.recyclerviewproject.R;
import lxfeng.recyclerviewproject.view.BaseRecyclerAdapter;
import lxfeng.recyclerviewproject.view.CustomRecyclerView;

public class StaggeredFragment extends BaseFragment {

    private CustomRecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;

    private ArrayList<String> mObjectList = new ArrayList<String>();

    public static StaggeredFragment newInstance() {
        StaggeredFragment fragment = new StaggeredFragment();
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
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMyAdapter = new MyAdapter(mObjectList);

        ImageView imageView1 = new ImageView(context);
        imageView1.setImageResource(R.drawable.one);
        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView imageView2 = new ImageView(context);
        imageView2.setImageResource(R.drawable.one);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView imageView3 = new ImageView(context);
        imageView3.setImageResource(R.drawable.one);
        imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mRecyclerView.addHeaderView(imageView1);
        mRecyclerView.addHeaderView(imageView2);
        mRecyclerView.addFooterView(imageView3);
        //    mRecyclerView.addFooterView(imageView3);
        mRecyclerView.setAdapter(mMyAdapter);
        mMyAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
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

        private static final int ONE = 0;

        public MyAdapter(ArrayList<String> objects) {
            super(objects);
        }

        @Override
        public int getItemViewType(int position) {
            if (0 == position){
                return ONE;
            }
            return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType) {
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggeres,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindVH(RecyclerView.ViewHolder holder, int position) {
            if (0 == position) {
                ((MyViewHolder) holder).imageView.setImageResource(R.drawable.two);
                ((MyViewHolder) holder).imageView.setBackgroundResource(R.color.colorAccent);
            }else {
                ((MyViewHolder) holder).imageView.setImageResource(R.drawable.three);
                ((MyViewHolder) holder).imageView.setBackgroundResource(R.color.colorPrimaryDark);
            }
        }

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_cartoon);
        }

    }

}
