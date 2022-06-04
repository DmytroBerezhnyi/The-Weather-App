package com.example.android.test1.Fragments

import com.hannesdorfmann.mosby.mvp.MvpFragment
import com.example.android.test1.MVP.IMainWeatherView
import com.example.android.test1.MVP.IWeatherPresenter
import com.example.android.test1.Adapters.CustomAdapterEventBus
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.android.test1.R
import com.example.android.test1.data.WeatherDB
import com.example.android.test1.MVP.WeatherPresenterImpl

class DataBaseFragment : MvpFragment<IMainWeatherView?, IWeatherPresenter>(), IMainWeatherView {
    private var button: Button? = null
    private var listView: ListView? = null
    private var customAdapterEventBus: CustomAdapterEventBus? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        button = view?.findViewById(R.id.myBtnGetEventBus)
        listView = view?.findViewById(R.id.myListViewEventBus)
        button?.setOnClickListener { getPresenter().buttonPressed() }
    }

    override fun showList(weatherDBList: List<WeatherDB>) {
        activity?.runOnUiThread {
            button!!.visibility = View.GONE
            listView!!.visibility = View.VISIBLE
            customAdapterEventBus = CustomAdapterEventBus(weatherDBList, context)
            listView!!.adapter = customAdapterEventBus
            customAdapterEventBus!!.notifyDataSetChanged()
        }
    }

    override fun createPresenter(): IWeatherPresenter {
        return WeatherPresenterImpl()
    }
}