package lib.common.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import lib.common.widget.HeadLayout;

public abstract class CommonActivity  extends AppCompatActivity {
    protected HeadLayout headLayout;
    protected Context mContext;
    protected Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onClickBack(0);
                break;
        }
        return false;
    }
    protected void onClickBack(int i){
        if (0==i){
            onBackPressed();
        }
    }
    public  void aa(){

    }
}
