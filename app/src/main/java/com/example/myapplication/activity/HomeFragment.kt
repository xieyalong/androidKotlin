package com.example.myapplication.activity

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_home.*
import lib.common.fragment.BaseFragment

class HomeFragment : BaseFragment() {
    companion object{
        fun newInstance(str:String): HomeFragment {
            var fragment = HomeFragment()
            var bundle = Bundle()
            bundle.putString("k", str)
            fragment.arguments = bundle
            return fragment;
        }
    }

    override fun initViews(mView: View?) {
        var  v=tv_list
        tv_list.setOnClickListener(this)
    }

    override fun setPageView(): Int {
        return R.layout.fragment_home
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v!!.id){
            R.id.tv_list->ListViewActivity.startActivity(mContext)
        }
    }
}