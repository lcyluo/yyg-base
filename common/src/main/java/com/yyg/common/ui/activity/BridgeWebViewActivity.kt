package com.yyg.common.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.FrameLayout
import com.yyg.common.constant.CommonConstants
import com.yyg.common.constant.IntentArgs
import com.yyg.common.constant.RequestCodes
import com.yyg.common.widget.GlideEngine
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.huantansheng.easyphotos.setting.Setting
import com.lcy.base.core.ui.activity.SimpleActivity
import com.lcy.base.core.utils.UriUtils
import com.tencent.smtt.export.external.interfaces.JsResult
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.yyg.common.R
import com.yyg.common.widget.webview.ProgressX5WebView
import com.yyg.common.widget.webview.bridge.BridgeX5WebView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers
import top.zibin.luban.Luban


/**
 * 公共网页加载
 *
 * @author lh
 */
class BridgeWebViewActivity : SimpleActivity() {

    /**  X5内核浏览器 **/
    private var mX5WebView: BridgeX5WebView? = null
    /**  浏览器容器 **/
    private var mContainer: FrameLayout? = null
    /**  访问链接 **/
    private var mUrl: String? = null
    //  For Android 5.0
    /** 图片选择意图回调 **/
    private var mValueCallbackArray: ValueCallback<Array<Uri?>?>? = null
    /** 选择的图片 **/
    private var mSelected: ArrayList<Uri>? = null

    companion object {
        fun start(context: Context, url: String, title: String = "") {
            val starter = Intent(context, BridgeWebViewActivity::class.java)
            starter.putExtra(IntentArgs.ARGS_TITLE, title)
            starter.putExtra(IntentArgs.ARGS_URL, url)
            context.startActivity(starter)
        }
    }

    override fun getLayout(): Int = R.layout.activity_bridge_web_view

    override fun initEventAndData() {
        mContainer = findViewById(R.id.web_container)
        val title = intent.getStringExtra(IntentArgs.ARGS_TITLE)
            ?: getString(R.string.title_activity_bridge_web_view)
        setTitle(title)
        mUrl = intent.getStringExtra(IntentArgs.ARGS_URL) ?: null
        if (TextUtils.isEmpty(mUrl)) {
            throw IllegalArgumentException("url can not be empty")
        }

        initX5WebView()
    }

    override fun initListeners() {
        if (mX5WebView == null) return
        mX5WebView!!.webClientListener = object : ProgressX5WebView.WebClient {
            override fun onPageFinished(webView: WebView, url: String) {

            }

            override fun onPageStarted(webView: WebView, s: String, bitmap: Bitmap?) {

            }
        }
        mX5WebView!!.webChromeListener = object : ProgressX5WebView.WebChrome {
            override fun onJsAlert(
                webView: WebView?, url: String?, message: String?, jsResult: JsResult
            ): Boolean {
                jsResult.cancel()
                return false
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                setTitle(title)
            }

            override fun onShowFileChooser(
                webView: WebView?, valueCallback: ValueCallback<Array<Uri?>?>,
                fileChooserParams: WebChromeClient.FileChooserParams
            ): Boolean {
                mValueCallbackArray = valueCallback
                val acceptTypes = fileChooserParams.acceptTypes
                if (acceptTypes != null && acceptTypes.isNotEmpty()) {
                    for (acceptType in acceptTypes) {
                        // 选择图片意图
                        if (acceptType != null && acceptType.contains("image")) {
                            chooseImage()
                        }
                    }
                } else {
                    setValueCallbackEmpty()
                }
                return true
            }
        }
    }

    /**
     * 初始化X5WebView
     */
    private fun initX5WebView() {
        mX5WebView = BridgeX5WebView(mContext)
        mContainer?.addView(mX5WebView)
        val params = mX5WebView?.layoutParams
        params?.height = ViewGroup.LayoutParams.MATCH_PARENT
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        mX5WebView?.layoutParams = params
        mX5WebView?.loadUrl(mUrl)
    }

    private fun chooseImage() {
        EasyPhotos.createAlbum(this, true, GlideEngine.instance)
            .setFileProviderAuthority(CommonConstants.APP_FILE_PROVIDER)
            .setCount(CommonConstants.MAX_PICKER_PHOTO_COUNT)
            .setCleanMenu(false)
            .setPuzzleMenu(false)
            .setCameraLocation(Setting.LIST_FIRST)
            .start(RequestCodes.IMAGE_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null || !data.hasExtra(EasyPhotos.RESULT_PHOTOS)) {
            setValueCallbackEmpty()
            return
        }
        val list: ArrayList<Photo> = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS)
        if (requestCode == RequestCodes.IMAGE_PICKER) {
            if (list.size > 0) {
                if (mValueCallbackArray == null) {
                    return
                }
                if (mSelected == null) {
                    mSelected = ArrayList()
                } else {
                    mSelected!!.clear()
                }
                addSubscribe(
                    Observable.fromIterable(list)
                        .map { photo: Photo ->
                            Luban.with(mContext).ignoreBy(100).load(photo.path).get()[0]
                        }
                        .map { UriUtils.file2Uri(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : ResourceObserver<Uri>() {
                            override fun onNext(uri: Uri) {
                                mSelected!!.add(uri)
                            }

                            override fun onError(e: Throwable) {
                                setValueCallbackEmpty()
                            }

                            override fun onComplete() {
                                if (mSelected!!.size > 0) { //上传文件
                                    if (mValueCallbackArray != null) {
                                        mValueCallbackArray!!.onReceiveValue(mSelected!!.toTypedArray())
                                        mValueCallbackArray = null
                                    }
                                } else {
                                    setValueCallbackEmpty()
                                }
                            }
                        })
                )
            }
        }

    }

    private fun setValueCallbackEmpty() {
        if (mValueCallbackArray != null) {
            mValueCallbackArray!!.onReceiveValue(null)
            mValueCallbackArray = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mX5WebView != null) {
            mX5WebView!!.destroyWebView()
            mX5WebView = null
        }
    }

    override fun onBackPressedSupport() {
        if (mX5WebView != null && mX5WebView!!.canGoBack()) {
            mX5WebView!!.goBack()
        } else {
            super.onBackPressedSupport()
        }
    }
}
