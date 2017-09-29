package com.wenba.aixuestore.apps

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.wenba.aixuestore.R
import com.wenba.aixuestore.data.AppInfoDetail
import com.wenba.aixuestore.data.source.AppDataRepostory
import com.wenba.aixuestore.data.source.remote.RemoteDataSource
import com.wenba.aixuestore.util.UrlMapping
import kotlinx.android.synthetic.main.activity_app_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class AppDetailActivity : AppCompatActivity(), AppContract.DetailView {

    val tag = "AppDetailActivity"

    var pressenter: AppContract.DetailPressenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_detail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        collapsingToolbarLayout.title = intent.getStringExtra("appName")
        pressenter = AppDetailPressenter(AppDataRepostory(RemoteDataSource()), this)
        pressenter?.loadAppDetail(intent.getStringExtra("aKey"))
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = MyAdapter()
    }

    override fun setPresenter(presenter: AppContract.Pressenter) {
    }


    override fun toInstall(aKey: String, appName: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("aKey", aKey)
        intent.putExtra("appName", appName)
        startActivity(intent)
    }

    override fun showAppDetail(appInfoDetail: AppInfoDetail?) {
        val data = appInfoDetail?.data
        text_name.text = data?.appName
        text_version.text = data?.appVersion
        text_build.text = data?.appBuildVersion
        text_package_name.text = data?.appIdentifier
        text_update_time.text = data?.appUpdated
        val size = data?.appFileSize!!.div(1024).div(1024)
        text_size.text = size.toString() + "Mb"
        when (data?.appIsLastest) {
            "1" -> text_isnew.text = "最新"
            else -> {
                text_isnew.visibility = View.GONE
            }
        }
        text_intro.text = data?.appDescription
        text_update_desc.text = data?.appUpdateDescription
        Glide.with(this)
                .load(UrlMapping.ICON_DOMAIN + data?.appIcon)
                .placeholder(R.mipmap.icon_img_faild)
                .into(img_icon)
        text_setup.setOnClickListener { toInstall(data?.appKey, data?.appName) }

        if (data?.otherapps.size > 0) {
            layoutInflater.inflate(R.layout.item_detail_header, ll_history, true)
            for (otherappsBean in data?.otherapps) {
                val itemView = layoutInflater.inflate(R.layout.item_detail_history, null, false)
                val text1 = itemView.findViewById(R.id.text1) as TextView
                val text2 = itemView.findViewById(R.id.text2) as TextView
                val text3 = itemView.findViewById(R.id.text3) as TextView
                val text4 = itemView.findViewById(R.id.text4) as TextView
                text1.text = otherappsBean.appName
                text2.text = otherappsBean.appVersion
                text3.text = otherappsBean.appBuildVersion
                text4.text = otherappsBean.appCreated
                ll_history.addView(itemView)
            }
        }

    }


    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(layoutInflater.inflate(R.layout.content_detail, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        }

        override fun getItemCount(): Int {
            return 1
        }

        inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    }
}
