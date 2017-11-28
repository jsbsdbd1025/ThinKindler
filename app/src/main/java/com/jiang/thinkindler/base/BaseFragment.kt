package com.jiang.thinkindler.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiang.common.base.BaseView
import com.jiang.common.base.CommonApplication
import com.jiang.common.base.CommonFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by jiang on 2017/2/28.
 */

abstract class BaseFragment<P : BasePresenter> : CommonFragment(), BaseView {

    @Inject
    var mPresenter: P? = null

    private var disposables2Stop: CompositeDisposable? = null //管理stop取消订阅者
    private var disposables2Destroy: CompositeDisposable? = null //管理Destroy取消订阅者

    protected var mStatus: Int = 0

    private var mApplication: CommonApplication? = null

    /*********************
     * 子类实现
     */
    //获取布局文件
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (disposables2Destroy != null) {
            throw IllegalStateException("onCreate called multiple times")
        }
        disposables2Destroy = CompositeDisposable()
    }

    override fun addRxStop(disposable: Disposable): Boolean {
        if (disposables2Stop == null) {
            throw IllegalStateException(
                    "addUtilStop should be called between onStart and onStop")
        }
        disposables2Stop!!.add(disposable)
        return true
    }

    override fun addRxDestroy(disposable: Disposable): Boolean {
        if (disposables2Destroy == null) {
            throw IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy")
        }
        disposables2Destroy!!.add(disposable)
        return true
    }

    override fun remove(disposable: Disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw IllegalStateException("remove should not be called after onDestroy")
        }
        if (disposables2Stop != null) {
            disposables2Stop!!.remove(disposable)
        }
        if (disposables2Destroy != null) {
            disposables2Destroy!!.remove(disposable)
        }
    }

    override fun onStart() {
        super.onStart()
        if (disposables2Stop != null) {
            throw IllegalStateException("onStart called multiple times")
        }
        disposables2Stop = CompositeDisposable()
    }

    override fun onStop() {
        super.onStop()
        if (disposables2Stop == null) {
            throw IllegalStateException("onStop called multiple times or onStart not called")
        }
        disposables2Stop!!.dispose()
        disposables2Stop = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposables2Destroy == null) {
            throw IllegalStateException(
                    "onDestroy called multiple times or onCreate not called")
        }
        disposables2Destroy!!.dispose()
        disposables2Destroy = null
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (rootView == null) {
            rootView = inflater!!.inflate(layoutId, container, false)
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mApplication = activity.application as CommonApplication
        init(rootView)

        initInjector()
    }

    //初始化view
    protected abstract fun init(view: View)


    // dagger 注入
    protected abstract fun initInjector()

}
