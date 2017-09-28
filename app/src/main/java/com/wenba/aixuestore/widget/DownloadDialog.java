package com.wenba.aixuestore.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.wenba.aixuestore.R;

public class DownloadDialog extends ProgressDialog {

    CallBack callBack;

    public DownloadDialog(Context context, CallBack callBack) {
        this(context);
        this.callBack = callBack;
    }

    protected DownloadDialog(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setTitle(context.getString(R.string.str_downloading));
        setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.str_cancel), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.onCancel();
                dialog.dismiss();
            }
        });
    }

    public void update(Integer progress) {
        setProgress(progress);
    }

    public interface CallBack {

        void onCancel();
    }
}