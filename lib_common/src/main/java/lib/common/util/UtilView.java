package lib.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lib.common.CAPP;


public class UtilView {
    private UtilView(){}
    private  static UtilView intance;
    public  static UtilView getInstance(){
        if (null==intance){
            intance=new UtilView();
        }
        return  intance;
    }
    public  static UtilView i(){
        if (null==intance){
            intance=new UtilView();
        }
        return  intance;
    }
    @Deprecated
    public  void setRecyclerViewLinearLayoutManager( Context context,RecyclerView rv){
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
    }
    public  void setRecyclerViewLinearLayoutManager_VERTICAL( Context context,RecyclerView rv){
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
    }
    public  void setRecyclerViewGridLayoutManager_VERTICAL (Context context,RecyclerView rv, int spanCount){
        GridLayoutManager layoutManager=new GridLayoutManager(context,spanCount);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
    }
    public  void setRecyclerViewGridLayoutManager_HORIZONTAL (Context context,RecyclerView rv, int spanCount){
        GridLayoutManager layoutManager=new GridLayoutManager(context,spanCount);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
    }
    public  void setCompoundDrawablesLeft(TextView tv, int drawableId){
        Drawable dra = CAPP.getInstance().getApplication().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(dra,null,null, null);
    }
    public  void setCompoundDrawablesTop(TextView tv, int drawableId){
        Drawable dra = CAPP.getInstance().getApplication().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(null,dra,null, null);
    }

    public  void setCompoundDrawablesRight(TextView tv, int drawableId){
        Drawable dra = CAPP.getInstance().getApplication().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(null,null,dra, null);
    }
    public  void setCompoundDrawablesRight(TextView tv, int drawableId, float width_dp, float heigth_dp){
        Drawable dra = CAPP.getInstance().getApplication().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0,UtilScreen.getInstance().dp2px( width_dp), UtilScreen.getInstance().dp2px( heigth_dp));//设置高度
        tv.setCompoundDrawables(null,null,dra, null);
    }
    public  void setCompoundDrawablesBottom(TextView tv, int drawableId){
        Drawable dra = CAPP.getInstance().getApplication().getResources().getDrawable(drawableId);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());//设置高度
        tv.setCompoundDrawables(null,null,null, dra);
    }
    public  void setTextColor(TextView tv, int color){
        tv.setTextColor(CAPP.getInstance().getApplication().getResources().getColor(color));
    }
    public  void setBackgroundColor(View view, int colorId){
        view.setBackgroundColor(CAPP.getInstance().getApplication().getResources().getColor(colorId));
    }

    /**
     * 动态设置ListView的高度
     * 当ScrollView中包含ListView的时候，必须要手动的给设置ListView的高度
     * 否则的话，listView只会显示一行数据
     * 注意：要在adapter.notifyDataSetChanged();前
     * @param listView
     */
    public   void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    /**
     * 设置视图的宽高
     * @param view
     */
    public  void widthHigh(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    private  long lastClickTime;

    /**
     * 防止多次点击
     *   if (Utils.isFastDoubleClick()) {
     return;
     }else{
     //弹出Toast或者Dialog
     }
     * @return
     */
    public  boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public  void setTextChangedListener(TextView tv,
                                        final BeforeTextChanged beforeTextChanged,
                                        final TextChanged textChanged,
                                        final AfterTextChanged afterTextChanged){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (null!=beforeTextChanged){
                    beforeTextChanged.beforeTextChanged(s,start,count,after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textChanged!=null){
                    textChanged.onTextChanged(s,start,before,count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null!=afterTextChanged){
                    afterTextChanged.afterTextChanged(s);
                }
            }
        });
    }
    public  interface  AfterTextChanged{
        void afterTextChanged(Editable s);
    }
    public  interface  BeforeTextChanged{
        void beforeTextChanged(CharSequence s, int start, int count, int after);
    }
    public  interface  TextChanged{
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
