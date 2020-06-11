package com.example.myapplication.activity

import android.text.TextUtils
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*
import lib.common.activity.BaseActivity
import lib.common.util.UtilToast

class  LoginActivity: BaseActivity() {
    override fun setPageView(): Int {
        return R.layout.activity_login;
    }

    override fun initViews() {
        tv_login.setOnClickListener(this)
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
}