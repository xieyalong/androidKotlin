package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import com.example.myapplication.R
import com.wakehao.bar.BottomNavigationBar
import com.wakehao.bar.BottomNavigationItemWithDot
import kotlinx.android.synthetic.main.activity_main.*
import lib.common.activity.BaseActivity
import lib.common.fragment.BaseFragment

class MainActivity : BaseActivity() {
    //静态
    companion object{
        fun  startActivity(context:Context){
            var intent=Intent(context,MainActivity::class.java)
            intent.putExtra("a","A")
            context.startActivity(intent);
        }
    }

    var positionTag=0;
    var mFragments= arrayListOf<BaseFragment>();
    override fun setPageView(): Int {
      return R.layout.activity_main;
    }

    override fun initViews() {
        mFragments.add(HomeFragment.newInstance("a"));
        mFragments.add(
            MyFragment.newInstance(
                "a"
            )
        );
        initBootBar();
        loadMultipleRootFragment(
            R.id.fl_container, positionTag,
            mFragments[0],
            mFragments[1]
        )
    }
    fun initBootBar(){
        bar.setItemSelected(positionTag, true);
        bar.setOnNavigationItemSelectedListener(object :BottomNavigationBar.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: BottomNavigationItemWithDot,selectedPosition: Int): Boolean {
                positionTag=selectedPosition;
                showHideFragment(mFragments.get(positionTag));
                bar.setItemSelected(positionTag,true)
                return  false;
            }

            override fun onNavigationItemSelectedAgain(item: BottomNavigationItemWithDot,reSelectedPosition: Int) {
            }

        })
    }

}

