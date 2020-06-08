package com.example.myapplication

import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import com.alibaba.fastjson.JSONObject
import kotlinx.android.synthetic.main.activity_register.*
import lib.common.CStaticKey
import lib.common.activity.BaseActivity
import lib.common.http.HttpCall
import lib.common.http.HttpRequest
import lib.common.util.UtilActivity
import lib.common.util.UtilSP
import lib.common.util.UtilToast
import java.util.*

class RegisterActivity : BaseActivity() {
    var tv_account: EditText? = null
    var tv_pwd: EditText? = null
    var tv_pwd2: EditText? = null
    override fun setPageView(): Int {
        return R.layout.activity_register
    }

    override  fun initViews() {
        tv_pwd?.setText("");
        tv_pwd!!.setText("")
        tv_login.setOnClickListener(this)
        tv_pwd?.transformationMethod = PasswordTransformationMethod.getInstance()
        tv_pwd2!!.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_login -> println("")
            R.id.tv_login ->{
                println("")
            }
        }
    }

    fun register() {
        val account = tv_account!!.text.toString()
        if (TextUtils.isEmpty(account)) {
            UtilToast.i().showWarn("请输入注册名称")
            return
        }
        if (account.length < 3) {
            UtilToast.i().showWarn("注册名称不能小于3位")
            return
        }
        val pwd = tv_pwd!!.text.toString()
        val pwd2 = tv_pwd2!!.text.toString()
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2)) {
            UtilToast.i().showWarn("请输入密码")
            return
        }
        if (pwd.length < 6 || pwd2.length < 6) {
            UtilToast.i().showWarn("密码不能小于6位")
            return
        }
        if (pwd != pwd2) {
            UtilToast.i().showWarn("两次输入密码不一致")
            return
        }
        val params: MutableMap<String, Any> =
            HashMap()
        params["mobile"] = account
        params["password"] = pwd
        HttpRequest.i().post("", params, object : HttpCall() {
            override fun onBefore() {
                super.onBefore()
                isLoadDialog = true
            }

            override fun onJsonSuccess(
                code: Int,
                msg: String,
                data: JSONObject
            ) {
                super.onJsonSuccess(code, msg, data)
                if (0 == code) {
                    UtilSP.i().put(CStaticKey.sp_account, account)
                    finish()
                } else {
                    UtilToast.i().showWarn(msg)
                }
            }
        })
    }

    companion object {
        fun startActivity() {
            UtilActivity.i().startActivity(RegisterActivity::class.java)
        }
    }
}