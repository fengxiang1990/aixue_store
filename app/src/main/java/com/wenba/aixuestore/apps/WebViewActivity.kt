package com.wenba.aixuestore.apps

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.graphics.Bitmap
import android.net.Uri
import android.os.*
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
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

    val REQ_INSTALL = 10000
    var downloadManager: DownloadManager? = null
    var FILE_NAME: String? = null
    var currentDownloadId: Long = -1
    var downloadDialog: DownloadDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        downloadDialog = DownloadDialog(this@WebViewActivity, this@WebViewActivity)
        setContentView(R.layout.activity_web_view)
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
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
            val fileName = getExternalFilesDir(null).path + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + URLDecoder.decode(FILE_NAME)
            val file = File(fileName)
            if (file.exists()) {
                file.delete()
            }
            val request = DownloadManager.Request(Uri.parse(url))
            request.setDestinationInExternalFilesDir(this@WebViewActivity, Environment.DIRECTORY_DOWNLOADS, FILE_NAME)
            request.setTitle(appName)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            currentDownloadId = downloadManager!!.enqueue(request)
            contentResolver.registerContentObserver(Uri.parse("content://downloads/"), true, DownloadObserver())
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
                        val fileName = getExternalFilesDir(null).path + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + URLDecoder.decode(FILE_NAME)
                        Log.e(tag, "fileName->" + fileName)
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
        downloadManager?.remove(currentDownloadId)
        finish()
    }


    inner class DownloadObserver : ContentObserver(handler) {

        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            val query = DownloadManager.Query().setFilterById(currentDownloadId)
            val cursor = downloadManager!!.query(query)
            while (cursor.moveToNext()) {
                val mDownload_so_far = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val mDownload_all = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val bdc = BigDecimal(mDownload_so_far)
                val bdt = BigDecimal(mDownload_all)
                val mProgressd = bdc.divide(bdt, 2, RoundingMode.HALF_UP).toDouble().times(100)
                val progress = mProgressd.toInt()
                val message = handler.obtainMessage()
                message.what = progress
                handler.sendMessage(message)
            }
        }
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
