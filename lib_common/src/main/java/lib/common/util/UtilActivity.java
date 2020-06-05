package lib.common.util;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;

import java.util.List;

public class UtilActivity {
    private  static UtilActivity instance;
    @Deprecated
    public static UtilActivity getInstance(){
        if (null==instance){
            instance=new UtilActivity();
        }
        return  instance;
    }
    public static UtilActivity i(){
        if (null==instance){
            instance=new UtilActivity();
        }
        return  instance;
    }
    private Activity activity;

    public Activity getActivity() {
        activity= ActivityUtils.getTopActivity();
        return activity;
    }
    public  void startActivity(@NonNull final Class<? extends Activity> clz) {
        ActivityUtils.startActivity(clz);
    }
    public  boolean startActivity(@NonNull final Intent intent) {
        return  ActivityUtils.startActivity(new Intent(intent));
    }
    public  List<Activity> getActivityList() {
        return ActivityUtils.getActivityList();
    }
}
