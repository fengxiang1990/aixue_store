package com.wenba.aixuestore.apps

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.wenba.aixuestore.R
import com.wenba.aixuestore.util.Config
import com.wenba.aixuestore.util.UrlMapping
import com.wenba.aixuestore.widget.DownloadDialog
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URLDecoder


class WebViewActivity : AppCompatActivity(), DownloadDialog.CallBack {

    val tag = "WebViewActivity"
    var currentTask: BaseDownloadTask? = null
    val REQ_INSTALL = 10000
    var FILE_NAME: String? = null
    var downloadDialog: DownloadDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FileDownloader.setup(this)
        downloadDialog = DownloadDialog(this@WebViewActivity, this@WebViewActivity)
        setContentView(R.layout.activity_web_view)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.str_app_download)
        val aKey = intent.getStringExtra("aKey")
        val appName = intent.getStringExtra("appName")
        val webSetting = webView.settings
        webSetting.javaScriptEnabled = true
        webView.setWebViewClient(object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Log.d(tag, "url-->" + url)
                super.onPageStarted(view, url, favicon)
            }
        })

        webView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            FILE_NAME = appName + ".apk"
            val fileName = externalCacheDir.path + File.separator + URLDecoder.decode(FILE_NAME)
            val file = File(fileName)
            if (file.exists()) {
                file.delete()
            }
            FileDownloader.getImpl().create(url)
                    .setPath(fileName)
                    .setListener(object : FileDownloadListener() {
                        override fun pending(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {}

                        override fun connected(task: BaseDownloadTask?, etag: String?, isContinue: Boolean, soFarBytes: Int, totalBytes: Int) {
                            currentTask = task
                        }

                        override fun progress(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {
                            val bdc = BigDecimal(soFarBytes)
                            val bdt = BigDecimal(totalBytes)
                            val mProgressd = bdc.divide(bdt, 2, RoundingMode.HALF_UP).toDouble().times(100)
                            val progress = mProgressd.toInt()
                            val message = handler.obtainMessage()
                            message.what = progress
                            handler.sendMessage(message)
                        }

                        override fun blockComplete(task: BaseDownloadTask?) {}

                        override fun retry(task: BaseDownloadTask?, ex: Throwable?, retryingTimes: Int, soFarBytes: Int) {}

                        override fun completed(task: BaseDownloadTask) {
                            val message = handler.obtainMessage()
                            message.what = 100
                            handler.sendMessage(message)
                        }

                        override fun paused(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {}

                        override fun error(task: BaseDownloadTask, e: Throwable) {}

                        override fun warn(task: BaseDownloadTask) {}
                    }).start()
        }
        var url = UrlMapping.INSTALL_DOMAIN + "?_api_key=" + Config._api_key + "&aKey=" + aKey
        webView.loadUrl(url)
    }


    var openfile: Boolean = false

    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (downloadDialog?.isShowing) {
                false -> downloadDialog?.show()
            }
            downloadDialog?.update(msg?.what)
            if (msg?.what!! >= 100) {
                postDelayed({
                    downloadDialog?.dismiss()
                    if (!openfile) {
                        val fileName = externalCacheDir.path + File.separator + URLDecoder.decode(FILE_NAME)
                        val file = File(fileName)
                        val intent = Intent(Intent.ACTION_VIEW)
                        var data: Uri
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            data = FileProvider.getUriForFile(this@WebViewActivity, "com.wenba.aixuestore.fileprovider", file)
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        } else {
                            data = Uri.fromFile(file)
                        }
                        intent.setDataAndType(data, "application/vnd.android.package-archive")
                        try {
                            startActivityForResult(intent, REQ_INSTALL)
                            openfile = true
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }, 1000)
            }
        }
    }

    override fun onCancel() {
        currentTask?.cancel()
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(tag, "onDestroy")
        downloadDialog?.dismiss()
        downloadDialog = null
        handler.removeCallbacksAndMessages(null)

    }
}
