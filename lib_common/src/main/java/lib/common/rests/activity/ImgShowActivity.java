package lib.common.rests.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;

import lib.common.R;
import lib.common.activity.BaseActivity;
import lib.common.util.UtilImg;

/**
 * 图片放大，图片缩放
 */
public class ImgShowActivity extends BaseActivity {
    public  static  void startActivity(String imgurl){
        Intent intent=new Intent(ActivityUtils.getTopActivity(),ImgShowActivity.class);
        intent.putExtra("imgurl",imgurl);
        ActivityUtils.startActivity(intent);
    }

    private ImageView img;
    String imgurl;//可以是网络的，也可以是本地磁盘的

    @Override
    public int setPageView() {
        return R.layout.activity_img_show;
    }

    @Override
    public void initViews() {
        imgurl = getIntent().getStringExtra("imgurl");

        img = findViewById(R.id.img);
        UtilImg.i().load(imgurl,img);
    }
}
