package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.example.myapplication.R
import com.example.myapplication.bean.User
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
//            MainActivity.javaClass
            var  user=User();
            user.address="北京"
            user.age=9
            user.name="lisi"
            intent.putExtra("a","A")
            intent.putExtra("user",user)
//            context.startActivity(intent);
            ActivityUtils.startActivity(intent)
//            ActivityUtils.startActivity(MainActivity::class.java)
        }
    }

    var positionTag=0;
    var mFragments= arrayListOf<BaseFragment>();

    var  a="";
    override fun initViewDataAfter() {
        super.initViewDataAfter()
        a=intent.getStringExtra("a")
        LogUtils.i(">]="+a)
        var user=intent.getSerializableExtra("user")
        LogUtils.i(">]"+JSON.toJSONString(user))
    }
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
    fun b(){
        var aa=1
        var b=""
        //实体类
        var user=User()
        a(user)
    }

    fun a(any: Any){

    }

}

