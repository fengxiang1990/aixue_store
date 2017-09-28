package com.wenba.aixuestore.apps

import com.wenba.aixuestore.R
import com.wenba.aixuestore.data.source.AppDataRepostory
import com.wenba.aixuestore.data.source.remote.RemoteDataSource
import com.wenba.aixuestore.util.SharedPreferenceUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.withArguments


class MainActivity : AppCompatActivity() {

    val LAST_TAB = "lastTabPosition"
    var titles: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        titles = arrayOf(getString(R.string.nav_master)
                , getString(R.string.nav_pro), getString(R.string.nav_all))

        val masterFragment = Fragment.instantiate(this, AppsFragment::class.java.name).withArguments(Pair("tag", "master")) as AppsFragment
        val proFragment = Fragment.instantiate(this, AppsFragment::class.java.name).withArguments(Pair("tag", "pro")) as AppsFragment
        val allFragment = Fragment.instantiate(this, AppsFragment::class.java.name).withArguments(Pair("tag", "all")) as AppsFragment
        val fragments = arrayListOf<Fragment>(masterFragment, proFragment, allFragment)

        val repository = AppDataRepostory(RemoteDataSource())
        masterFragment.pressenter = AppPressenter(repository, masterFragment)
        proFragment.pressenter = AppPressenter(repository, proFragment)
        allFragment.pressenter = AppPressenter(repository, allFragment)

        viewPager.adapter = MyPagerAdapter(fragments)
        tabLayout.addTab(tabLayout.newTab().setText(titles!![0]))
        tabLayout.addTab(tabLayout.newTab().setText(titles!![1]))
        tabLayout.addTab(tabLayout.newTab().setText(titles!![2]))
        tabLayout.setupWithViewPager(viewPager)

        val selection = SharedPreferenceUtil.getSharedPreferences(this)?.getInt(LAST_TAB, 0)
        viewPager.currentItem = selection!!
    }

    override fun onDestroy() {
        super.onDestroy()
        SharedPreferenceUtil.getSharedPreferences(this)?.edit()?.putInt(LAST_TAB, tabLayout.selectedTabPosition)?.commit()
    }

    inner class MyPagerAdapter : FragmentPagerAdapter {

        private val fragments: ArrayList<Fragment>

        constructor(fragments: ArrayList<Fragment>) : super(supportFragmentManager) {
            this.fragments = fragments
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles!![position]
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.filter_apps, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            R.id.menu_filter -> showFilteringPopUpMenu()
//        }
//        return super.onOptionsItemSelected(item)
//    }

//     fun showFilteringPopUpMenu() {
//        val popup = PopupMenu(this@MainActivity, this@MainActivity.findViewById(R.id.menu_filter))
//        popup.menuInflater.inflate(R.menu.filter_menus, popup.menu)
//
//        popup.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.all -> pressenter?.loadAppInfos()
//                R.id.master -> pressenter?.loadAppInfos(Filter.MASTER)
//                else -> pressenter?.loadAppInfos(Filter.PRO)
//            }
//            true
//        }
//
//        popup.show()
//    }

}
