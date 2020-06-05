package lib.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import lib.common.R;


/**
 *
 * @author xieyalong
 * @describe
 */
public class HeadLayout extends LinearLayout implements View.OnClickListener {
    private LinearLayout left_ll, right_ll, view;
    private ImageView left_iv, title_iv, right_iv1, right_iv2;
    private TextView title_tv1, title_tv2, right_tv;
    private String title_tv1_text, title_tv2_text, right_tv_text;
    private boolean title_tv2_visibility;
    boolean title_iv_visibility;
    boolean right_iv1_visibility;
    boolean right_iv2_visibility;
    boolean right_tv_visibility;
    boolean left_iv_visibility;
    int right_iv1_src,right_iv2_src,title_iv_src;
    public static    int headLayoutId=generateViewId();
    HeadLayoutOnClickListener headLayoutOnClickListener;
    public HeadLayout(Context context) {
        super(context);
        initView();
    }

    public HeadLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HeadLayout);
        title_tv1_text = ta.getString(R.styleable.HeadLayout_title_tv1_text);
        title_tv2_text = ta.getString(R.styleable.HeadLayout_title_tv2_text);
        right_tv_text = ta.getString(R.styleable.HeadLayout_right_tv_text);
        title_tv2_visibility = ta.getBoolean(R.styleable.HeadLayout_title_tv2_visibility, false);
        title_iv_visibility = ta.getBoolean(R.styleable.HeadLayout_title_iv_visibility, false);
        right_iv1_visibility = ta.getBoolean(R.styleable.HeadLayout_right_iv1_visibility, false);
        right_iv2_visibility = ta.getBoolean(R.styleable.HeadLayout_right_iv2_visibility, false);
        right_tv_visibility = ta.getBoolean(R.styleable.HeadLayout_right_tv_visibility, false);
        left_iv_visibility = ta.getBoolean(R.styleable.HeadLayout_left_iv_visibility, true);
        right_iv1_src=ta.getResourceId(R.styleable.HeadLayout_right_iv1_src,0);
        right_iv2_src=ta.getResourceId(R.styleable.HeadLayout_right_iv2_src,0);
        title_iv_src=ta.getResourceId(R.styleable.HeadLayout_title_iv_src,0);
        ta.recycle();
        initView();
    }

    public void initView() {
        headLayoutId=generateViewId();
        view = (LinearLayout) View.inflate(getContext(), R.layout.view_head, null);
        addView(view);
        left_ll = (LinearLayout) findViewById(R.id.left_ll);
        left_ll.setOnClickListener(this);
        left_iv = (ImageView) view.findViewById(R.id.left_iv);
        if (left_iv_visibility){
            left_iv.setVisibility(View.VISIBLE);
        }else{
            left_iv.setVisibility(View.GONE);
        }
        title_iv = (ImageView) findViewById(R.id.title_iv);
        if (title_iv_visibility) {
            title_iv.setVisibility(View.VISIBLE);
        } else {
            title_iv.setVisibility(View.GONE);
        }
        title_iv.setOnClickListener(this);
        title_iv.setImageResource(title_iv_src);
        title_tv1 = (TextView) findViewById(R.id.title_tv1);
        title_tv1.setText(title_tv1_text);
        title_tv2 = (TextView) findViewById(R.id.title_tv2);
        title_tv2.setText(title_tv2_text);
        if (title_tv2_visibility) {
            title_tv2.setVisibility(View.VISIBLE);
        } else {
            title_tv2.setVisibility(View.GONE);
        }
        title_tv2.setOnClickListener(this);
        right_iv1 = (ImageView) findViewById(R.id.right_iv1);
        right_iv1.setOnClickListener(this);
        if (right_iv1_visibility) {
            right_iv1.setVisibility(View.VISIBLE);
        } else {
            right_iv1.setVisibility(View.GONE);
        }
        right_iv1.setImageResource(right_iv1_src);
        right_iv2 = (ImageView) findViewById(R.id.right_iv2);
        right_iv2.setOnClickListener(this);
        if (right_iv2_visibility) {
            right_iv2.setVisibility(View.VISIBLE);
        } else {
            right_iv2.setVisibility(View.GONE);
        }
        right_iv2.setImageResource(right_iv2_src);
        right_tv = (TextView) findViewById(R.id.right_tv);
        right_tv.setText(right_tv_text);
        if (right_tv_visibility) {
            right_tv.setVisibility(View.VISIBLE);
        } else {
            right_tv.setVisibility(View.GONE);
        }

        right_ll = (LinearLayout) findViewById(R.id.right_ll);
        right_ll.setOnClickListener(this);

    }
    public  void setHeadLayoutOnClickListener(HeadLayoutOnClickListener headLayoutOnClickListener){
        this.headLayoutOnClickListener=headLayoutOnClickListener;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public  void setTitleText(String txt){
        title_tv1.setText(txt);
    }
    public  TextView getTitleText(){
        return title_tv1;
    }
    public  void setRightText(String txt){
        right_tv.setText(txt);
    }
    public  TextView getRightTextView(){
//        right_tv.setTextSize(8);
        return right_tv;
    }
    public  ImageView getRghtIv1(){
        return  right_iv1;
    }
    public  ImageView getRghtIv2(){
        return  right_iv2;
    }
    @Override
    public void onClick(View v) {
        if (null==headLayoutOnClickListener){
            return;
        }
        int id = v.getId();
        if (id == R.id.left_ll) {
            if (left_iv_visibility) {
                headLayoutOnClickListener.leftOnClick();
            }
        } else if (id == R.id.right_tv) {
            headLayoutOnClickListener.rightOnClick();
        } else if (id == R.id.right_ll) {
            headLayoutOnClickListener.rightOnClick();
        } else if (id == R.id.right_iv1) {
            headLayoutOnClickListener.rightIv1OnClick();
        } else if (id == R.id.right_iv2) {
            headLayoutOnClickListener.rightIv2OnClick();
        } else if (id == R.id.title_iv) {
            headLayoutOnClickListener.titleIvOnClick();

            headLayoutOnClickListener.titleTv2OnClick();
        } else if (id == R.id.title_tv2) {
            headLayoutOnClickListener.titleTv2OnClick();
        }
    }

    public interface HeadLayoutOnClickListener {
        void leftOnClick();//左边回退

        void rightOnClick();//右边文字

        void rightIv1OnClick();//右边图片1

        void rightIv2OnClick();//右边图片2

        void titleIvOnClick();//标题1

        void titleTv2OnClick();//b标题2
    }
}
