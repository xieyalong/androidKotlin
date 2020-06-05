package lib.common.util;

import org.greenrobot.eventbus.EventBus;

public class UtilMsg {
    private  static UtilMsg instance;
    @Deprecated
    public static UtilMsg getInstance(){
        if (null==instance){
            instance=new UtilMsg();
        }
        return  instance;
    }
    public static UtilMsg i(){
        if (null==instance){
            instance=new UtilMsg();
        }
        return  instance;
    }
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }
    public void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);

    }
    public synchronized void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }
}
