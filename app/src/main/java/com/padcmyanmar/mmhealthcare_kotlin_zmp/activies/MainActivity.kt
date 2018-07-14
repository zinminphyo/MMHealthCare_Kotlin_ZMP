package com.padcmyanmar.mmhealthcare_kotlin_zmp.activies

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.padcmyanmar.mmhealthcare_kotlin_zmp.R
import com.padcmyanmar.mmhealthcare_kotlin_zmp.adapters.HealthCareAdapter
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.models.HealthCareModel
import com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos.HealthCareVO
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.FailHealthCareList
import com.padcmyanmar.mmhealthcare_kotlin_zmp.events.SuccessGetHealthCareEvent
import com.padcmyanmar.mmhealthcare_kotlin_zmp.network.RetrofitDataAgentImpl

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private var healthCareAdapter: HealthCareAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        rv_healthCareLists.layoutManager = LinearLayoutManager(applicationContext)
        healthCareAdapter = HealthCareAdapter(applicationContext)
        rv_healthCareLists.adapter = healthCareAdapter
        HealthCareModel.getInstance().loadHealthCare()

        swipeRefresh.setOnRefreshListener {
            HealthCareModel.getInstance().forceLoadHealthCare()
            swipeRefresh.isRefreshing=false
        }

        rv_healthCareLists.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var isListEndReached = false

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (recyclerView!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        == recyclerView.adapter?.itemCount!! - 1 && !isListEndReached) {
                    isListEndReached = true
                    HealthCareModel.getInstance().loadHealthCare()
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoadedHealthCareLists(healthCareLists: SuccessGetHealthCareEvent.HealthCareEvent) {
        healthCareAdapter!!.appendNewData(healthCareLists.loadHealthCareLists as MutableList<HealthCareVO>)
        swipeRefresh.isRefreshing = false
        vpEmpty.visibility= GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorHealthCareLists(apiErrorEvent: FailHealthCareList.ApiErrorEvent) {
        Log.d("onFailEvent", "onFailGetHealthCareLists" + apiErrorEvent.getErrorMsg())
        swipeRefresh.isRefreshing = false
        if (healthCareAdapter!!.itemCount <= 0) {
            vpEmpty.visibility = VISIBLE
        } else {
            vpEmpty.visibility = GONE
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorHealthCareLists(emptyDataLoadedEvent: SuccessGetHealthCareEvent.ErreorEvent) {

    }
}
