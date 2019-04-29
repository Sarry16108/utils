package com.example.lib_generic.utils;

import android.util.Log;

/**
 * author ：yanghj
 * created at ：2019/4/16
 * description ：log类，用于logcat日志打印，
 **/

public class LogUtils {
    private static boolean isDebug = true;

    private static boolean isStackInfo = true;

    private static String TAG = "Utils";

    /**
     * 是否开启调试日志打印模式
     * @param debug
     */
    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static boolean getDebug() {
        return isDebug;
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void setStackInfo(boolean isStackInfo) {
        isStackInfo = isStackInfo;
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, getMethodLine() + msg);
        }
    }

    public static void i(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.i(TAG, getMethodLine() + " [" + tag1 + "] [" + tag2 + "] " + msg);
        }
    }

    public static void d() {
        if (isDebug) {
            Log.d(TAG, getMethodLine());
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, getMethodLine() + msg);
        }
    }

    public static void d(String msg1, String msg2) {
        if (isDebug) {
            Log.d(TAG, getMethodLine() + "msg1：" + msg1 + "  msg2:" + msg2);
        }
    }
    public static void d(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.d(TAG, getMethodLine() + " [" + tag1 + "] [" + tag2 + "] " + msg);
        }
    }


    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, getMethodLine() + msg);
        }
    }

    public static void w(String msg1, String msg2) {
        if (isDebug) {
            Log.w(TAG, getMethodLine() + "msg1：" + msg1 + "  msg2:" + msg2);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, getMethodLine() + msg);
        }
    }

    public static void e(String tag1, String tag2, String msg) {
        if (isDebug) {
            Log.e(TAG, getMethodLine() + " [" + tag1 + "] [" + tag2 + "] " + msg);
        }
    }


    private static String getMethodLine() {
        if (!isStackInfo) {
            return "";
        }

        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        StackTraceElement traceElement = stackTraceElement[4];

        StringBuilder sb = new StringBuilder();
        String threadName = Thread.currentThread().getName();
        String fileName = traceElement.getFileName();
        String className = getSimpleName(traceElement.getClassName());
        String methodName = traceElement.getMethodName();
        long threadID = Thread.currentThread().getId();
        int lineNumber = traceElement.getLineNumber();

        sb.append("thread：").append(threadName).append(" [");
        sb.append(threadID).append("]  ");

        sb.append(className).append(".");
        sb.append(methodName).append(" (");
        sb.append(fileName).append(":");
        sb.append(lineNumber).append(")  ");

        return sb.toString();
    }

    private static String getSimpleName(String className) {
        int index = className.lastIndexOf('.');
        if (-1 < index) {
            return className.substring(index + 1, className.length());
        } else {
            return className;
        }
    }
}
