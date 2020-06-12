package com.example.myapplication.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.bean.User
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_list_view.*
import lib.common.activity.BaseActivity


class ListViewActivity:BaseActivity(){
    companion object{
        fun startActivity(context: Context){
            var intent=Intent(context,ListViewActivity::class.java)
            context.startActivity(intent)
        }
    }
    var adapter:MyAdapter?=null
    override fun setPageView(): Int {
        return  R.layout.activity_list_view
    }

    override fun initContentViewBefore() {
        super.initContentViewBefore()
        adapter=MyAdapter()
    }
    override fun initViews() {
        var layoutManager= LinearLayoutManager(mActivity);
        layoutManager.orientation=LinearLayoutManager.VERTICAL;
        //使用kotlin 不用findBaId
        rv_list.layoutManager=layoutManager;
        rv_list.adapter=adapter

        refreshLayout.setOnRefreshListener(object :OnRefreshListener{
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout!!.finishRefresh(2000 /*,false*/)
                //传入false表示刷新失败
            }
        })
        refreshLayout.setOnLoadMoreListener(object :OnLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                refreshLayout!!.finishLoadMore(2000)// finishLoadMore(2000 /*,false*/) //传入false表示加载失败
            }
        })
        refreshLayout.autoRefresh()
    }

    class MyAdapter: BaseQuickAdapter<User, BaseViewHolder> {
        var list:ArrayList<User>?=null;
        constructor() : super(R.layout.item) {
            var list= arrayListOf<User>();
            for (v in 0.rangeTo(50)){
                var u=User()
                u.name="i="+v
                list.add(u)
            }
            this.setNewData(list)
        }
        override fun convert(helper: BaseViewHolder?, item: User?) {
            helper!!.setText(R.id.tv, item?.name);
        }
    }

}