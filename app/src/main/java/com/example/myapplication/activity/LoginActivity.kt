package com.example.myapplication.activity

import android.Manifest
import android.text.TextUtils
import android.view.View
import com.example.myapplication.R
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_login.*
import lib.common.activity.BaseActivity
import lib.common.util.UtilToast

class  LoginActivity: BaseActivity() {
    override fun setPageView(): Int {
        return R.layout.activity_login;
    }

    override fun initViews() {
        tv_login.setOnClickListener(this)
        permission()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id){
            R.id.tv_login ->{
                login()
            }
        }
    }

    fun  login(){
        if (TextUtils.isEmpty(tv_account.text)){
            UtilToast.i().showWarn("账号不能为null")
            return
        }
        if (TextUtils.isEmpty(tv_pwd.text)){
            UtilToast.i().showWarn("密码不能为null")
            return
        }
        MainActivity.startActivity(this);
    }
    fun permission() {
//        AndPermission.with(this).permission(
//            Manifest.permission.INTERNET,
//            Manifest.permission.ACCESS_NETWORK_STATE,
//            Manifest.permission.ACCESS_WIFI_STATE,
//            Manifest.permission.CHANGE_WIFI_STATE,
//            Manifest.permission.WRITE_SETTINGS,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.MODIFY_AUDIO_SETTINGS,
//            Manifest.permission.SYSTEM_ALERT_WINDOW,
//            Manifest.permission.VIBRATE,
//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.BLUETOOTH,
//            Manifest.permission.CAMERA,
//            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
//            Manifest.permission.CALL_PHONE
//        ).requestCode(0)
//            .callback(object : PermissionListener {
//            override fun onSucceed(
//                requestCode: Int,
//                grantPermissions: List<String>
//            ) {
//                println(">]权限回调成功")
//            }
//
//            override fun onFailed(
//                requestCode: Int,
//                deniedPermissions: List<String>
//            ) {
//                println(">]权限回调失败")
//            }
//        }).start()


        AndPermission.with(this)
            .runtime()
            .permission(Permission.Group.STORAGE).permission(Permission.CAMERA)
            .onGranted({ permissions ->
            })
            .onDenied({ permissions -> })
            .start()
    }
}