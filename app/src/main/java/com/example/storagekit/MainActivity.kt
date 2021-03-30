package com.example.storagekit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.executor.kit.ExecutorKit
import com.log.kit.LogKit

import com.log.kit.LogKitManager

import com.log.kit.print.view.ViewPrintProvider
import com.log.kit.print.view.ViewPrinter
import com.storage.kit.StorageKit
import java.util.*

class MainActivity : AppCompatActivity() {

    private val KEY = "key"

    private var mViewPrinter: ViewPrinter? = null
    private var mPrintProvider: ViewPrintProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPrinter = ViewPrinter(this)
        LogKitManager.getInstance().addPrinter(mViewPrinter)
        mPrintProvider = mViewPrinter?.getViewPrintProvider()
        mPrintProvider?.showFloatingView()
    }

    /**
     * 存储数据,支持存任意类型数据
     * 建议搭配多线程ExecutorKit一起使用
     */
    fun onSave(view: View?) {
        val list = ArrayList<String>()
        list.add("风清扬")
        list.add("令狐冲")
        list.add("任盈盈")

        ExecutorKit.execute(runnable =Runnable {
            StorageKit.saveCache(KEY, list)
            LogKit.v("存储的数据: $list")
        })
    }

    /**
     * 展示数据,建议搭配多线程ExecutorKit一起使用
     */
    fun onShow(view: View?) {
        ExecutorKit.execute(runnable =Runnable {
            val list: ArrayList<String>? = StorageKit.getCache(KEY)
            LogKit.i("打印数据: $list")
        })
    }

    /**
     * 删除数据,建议搭配多线程ExecutorKit一起使用
     */
    fun onDelete(view: View?) {
        ExecutorKit.execute(runnable =Runnable {
            LogKit.e("删除数据")
            StorageKit.deleteCache(KEY)
        })
    }


    fun open(view: View?) {
        mPrintProvider?.showFloatingView()
    }

    fun close(view: View?) {
        mPrintProvider?.closeFloatingView()
    }

    override fun onDestroy() {
        LogKitManager.getInstance().removePrinter(mViewPrinter)
        super.onDestroy()
    }
}