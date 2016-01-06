package lxfeng.recyclerviewproject.ui;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * <类描述>
 * 作者： Administrator
 * 时间： 2015/12/25
 */
public class BaseFragment extends Fragment {

    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

}
