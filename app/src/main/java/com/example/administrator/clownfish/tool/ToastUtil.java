package com.example.administrator.clownfish.tool;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clownfish.R;

/**
 * Created by hzwq on 2016/7/8.
 */
public class ToastUtil {


    private static Toast toast = null;

   /* public static void showSuccessToast(int stringRes) {
        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showSuccessToast(message, BaseApplication.getContext());
    }*/

    /*public static void showSuccessToast(String message) {
        showSuccessToast(message, BaseApplication.getContext());
    }*/

    public static void showSuccessToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.VISIBLE);
        toastIconImageView.setImageResource(R.mipmap.ic_toast_success);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


    /*public static void showWarningToast(int stringRes) {

        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showWarningToast(message, BaseApplication.getContext());
    }*/

    /*public static void showWarningToast(String message) {
        if (null != message)
            showWarningToast(message, BaseApplication.getContext());
    }*/

    public static void showWarningToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.VISIBLE);
        toastIconImageView.setImageResource(R.mipmap.ic_toast_warnning);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


    /*public static void showNormalToast(int stringRes) {
        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showNormalToast(message, BaseApplication.getContext());
    }*/

    /*public static void showNormalToast(String message) {
        showNormalToast(message, BaseApplication.getContext());
    }*/

    public static void showNormalToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.GONE);
        // toastIconImageView.setImageResource(R.mipmap.ic_toast_warnning);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }


   /* public static void showLongSuccessToast(int stringRes) {

        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showLongSuccessToast(message, BaseApplication.getContext());
    }*/

    /*public static void showLongSuccessToast(String message) {
        showLongSuccessToast(message, BaseApplication.getContext());
    }*/

    public static void showLongSuccessToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.VISIBLE);
        toastIconImageView.setImageResource(R.mipmap.ic_toast_success);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


    /*public static void showLongWarningToast(int stringRes) {

        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showLongWarningToast(message, BaseApplication.getContext());
    }*/

    /*public static void showLongWarningToast(String message) {
        showLongWarningToast(message, BaseApplication.getContext());
    }*/

    public static void showLongWarningToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.VISIBLE);
        toastIconImageView.setImageResource(R.mipmap.ic_toast_warnning);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


    /*public static void showLongNormalToast(int stringRes) {
        String message = BaseApplication.getContext().getResources().getString(stringRes);
        showLongNormalToast(message, BaseApplication.getContext());
    }*/

    /*public static void showLongNormalToast(String message) {
        showLongNormalToast(message, BaseApplication.getContext());
    }*/


    public static void showLongNormalToast(String message, Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        ImageView toastIconImageView = (ImageView) view.findViewById(R.id.toastIconImageView);
        TextView toastMessageTextView = (TextView) view.findViewById(R.id.toastMessageTextView);
        toastMessageTextView.setText(message);
        toastIconImageView.setVisibility(View.GONE);
        toastIconImageView.setImageResource(R.mipmap.ic_toast_warnning);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


    /**
     * 每日签到Toast
     */
    public static void showDaySignToast() {
        //     showDaySignToast(BaseApplication.getContext());
    }


    /**
     * 每日签到Toast
     *
     * @param
     */
   /* private static void showDaySignToast(Context context) {
        cancelToast();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_day_sign, null);
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }*/


    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
        toast = null;
    }

}
