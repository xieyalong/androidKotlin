package com.example.myapplication.activity

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import lib.common.fragment.BaseFragment

class MyFragment : BaseFragment() {
    companion object{
        fun newInstance(str:String): MyFragment {
            var fragment = MyFragment()
            var bundle = Bundle()
            bundle.putString("k", str)
            fragment.arguments = bundle
            return fragment;
        }
    }
    override fun initViews(mView: View?) {
        print("")
    }

    override fun setPageView(): Int {
        return R.layout.fragment_my
    }
}