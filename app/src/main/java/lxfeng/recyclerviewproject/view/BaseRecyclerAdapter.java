package lxfeng.recyclerviewproject.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * <类描述>
 * 作者： Administrator
 * 时间： 2015/12/25
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<T> objects;

    public BaseRecyclerAdapter(ArrayList<T> objects){
        this.objects = objects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent,viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (null == onItemClickListener) return;
                onItemClickListener.onItemClick(position,holder.itemView);
            }
        });
        onBindVH(holder,position);
    }

    public abstract RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType);

    public abstract void onBindVH(RecyclerView.ViewHolder holder,int position);

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(int position, View itemView);
    }

}
