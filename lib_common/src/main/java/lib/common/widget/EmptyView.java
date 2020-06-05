package lib.common.widget;

import android.view.View;
import android.widget.LinearLayout;

import lib.common.CAPP;
import lib.common.R;


/**
 * queryRecordAdapter.setEmptyView(new EmptyView().getView());
 */
public class EmptyView {
    LinearLayout mEmptyView;
    public  LinearLayout getView(){
        mEmptyView=(LinearLayout)View.inflate(CAPP.getInstance().getApplication(), R.layout.view_empty,null);
        return  mEmptyView;
    }
}
