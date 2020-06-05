package lib.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.Utils;

import lib.common.util.SSLHandshake;

public class CAPP  implements Application.ActivityLifecycleCallbacks{
    private  static CAPP commonAPP;
    public static CAPP getInstance(){
        if (null==commonAPP){
            commonAPP=new CAPP();
        }
        return  commonAPP;
    }

    private CAPP() {
    }


    private Application application;
    private Activity activity;
    public    void init(Application application){
        this.application=application;
        Utils.init(application);
        application.registerActivityLifecycleCallbacks(this);
        new SSLHandshake().handleSSLHandshake();
    }

    public Application getApplication() {
        return application;
    }

    public Activity getActivity() {
        return activity;
    }


    //////////////////////////////////////////////////////////
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        this.activity=activity;
        System.out.println(">]activity="+activity.getClass().getName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
    ////////////////////////////////////////////////////

}
