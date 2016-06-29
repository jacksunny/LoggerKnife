package com.rdors.loggerknife.loggerknifelibrary;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by houshijie on 6/27/16.
 * 日志工具类
 * 1.输出格式化的 log 信息（ 如 json 等 ）
 * 2.可以自定义分隔符
 */
public class L {
    /**
     * 日志级别
     */
    private static LogLevel logLevel = LogLevel.Verbose;
    private static String TAG = "";
    /**
     * 空格（表示缩进）
     */
    private static final String STYLE_SPACE = "    ";

    /**
     * 横向分隔符
     */
    private static final String SEPARATOR_HORIZONTAL = "==";
    /**
     * 横向分隔符 数量
     */
    private static final int SEPARATOR_HORIZONTAL_COUNT = 50;
    /**
     * 竖直分隔符
     */
    private static final String SEPARATOR_VERTICAL = "| ";
    /**
     * 默认回车之后加上竖直分隔符
     */
    private static final String STYLE_ENTER = "\n" + SEPARATOR_VERTICAL;

    /**
     * 每3000个字符就切割（建议在 3000左右，实测 3705字符之后的字符无法显示）
     */
    private static final int MAX_PRINT_LENGTH = 3000;

    /**
     * 初始化
     *
     * @param ll  输出 log 级别，为空则输出 Verbose 级别
     * @param tag 默认全局TAG，为 空则默认采用类名做TAG
     */
    public static void init(LogLevel ll, String tag) {
        if (ll != null) {
            logLevel = ll;
        }
        if (!TextUtils.isEmpty(tag)) {
            TAG = tag;
        }
    }

    /**
     * @param logLevel 输出等级
     * @param object   输出内容
     */
    private static void logAll(LogLevel logLevel, Object object) {
        if (object == null) {
            return;
        }
        if (!isCurrentTypeCanLogOut(logLevel)) {
            return;
        }
        String content = getContentStringWithAllType(object);

        if (TextUtils.isEmpty(content)) {
            logLevel = LogLevel.Error;
            content = "something wrong happened!,please check";
        }

        final int lengthOfContent = content.length();
        int count = lengthOfContent / MAX_PRINT_LENGTH;
        if (lengthOfContent % MAX_PRINT_LENGTH != 0) {
            count++;
        }
        final String[] items = new String[count];
        for (int i = 0; i < count; i++) {
            int topIndex = i + 1;
            if (topIndex == count) {
                topIndex = lengthOfContent;
            } else {
                topIndex = topIndex * MAX_PRINT_LENGTH;
            }
            items[i] = content.substring(i * MAX_PRINT_LENGTH, topIndex);
        }
        LogAllItems(logLevel, items);
    }

    private static final void LogAllItems(LogLevel logLevel, String[] items) {
        if (items == null || items.length == 0) {
            return;
        }
        for (String item : items) {
            switch (logLevel) {
                case Verbose:
                    Log.v(TAG, item);
                    break;
                case Debug:
                    Log.d(TAG, item);
                    break;
                case Info:
                    Log.i(TAG, item);
                    break;
                case Warn:
                    Log.w(TAG, item);
                    break;
                case Error:
                    Log.e(TAG, item);
                    break;
            }
        }
    }

    /**
     * 日志输出级别：V
     */
    public static void v(Object object) {
        logAll(LogLevel.Verbose, object);
    }

    /**
     * 日志输出级别：D
     */
    public static void d(Object object) {
        logAll(LogLevel.Debug, object);
    }

    /**
     * 日志输出级别：I
     */
    public static void i(Object object) {
        logAll(LogLevel.Info, object);
    }

    /**
     * 日志输出级别：W
     */
    public static void w(Object object) {
        logAll(LogLevel.Warn, object);
    }

    /**
     * 日志输出级别：E
     */
    public static void e(Object object) {
        logAll(LogLevel.Error, object);
    }

    /**
     * 是否传进来的 级别 能够输出
     *
     * @param ll 传进来的级别
     * @return 和当前输出级别对比，如果在之后，就能输出
     */
    private static boolean isCurrentTypeCanLogOut(LogLevel ll) {
        if (ll.getIndex() >= logLevel.getIndex()) {
            return true;
        }
        return false;
    }


    /**
     * @param object
     * @return 打印出特定格式 ，根据类型 object
     */
    private static String getContentStringWithAllType(Object object) {
        if (object == null) {
            return null;
        }

        final StringBuffer sb = new StringBuffer();
        sb.append(getSeparatorHorizontalsOfTop());

        if (object instanceof JSONObject) {
            sb.append(getContentStringWithJSONObject((JSONObject) object));
        } else if (object instanceof String) {
            sb.append(getContentStringWithString((String) object));
        } else {
            sb.append(getContentStringWithString(object.toString()));
        }

        sb.append(getSeparatorHorizontalsOfBottom());
        return sb.toString();
    }

    /**
     * @param object json 参数
     * @return 打印出 jsonObject 对应的格式
     */
    private static String getContentStringWithJSONObject(JSONObject object) {
        if (object == null) {
            return null;
        }
        String content = null;
        if (object instanceof JSONObject) {
            content = ((JSONObject) object).toString();
        }
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        return getContentStringWithString(content);
    }

    /**
     * @param object 参数
     * @return 输出 String 对应的格式
     */
    private static String getContentStringWithString(String object) {
        if (TextUtils.isEmpty(object)) {
            return null;
        }
        final int length = object.length();
        int space_count = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char charI = object.charAt(i);
            String string = String.valueOf(charI);

            // 按照 json 格式过滤处理 string
            if (string.equals("{")) {
                space_count++;
                sb.append("{" + STYLE_ENTER + getSpaceStringWithCount(space_count));
            } else if ("[".equals(string)) {
                space_count++;
                sb.append("[" + STYLE_ENTER + getSpaceStringWithCount(space_count));
            } else if (",".equals(string)) {
                sb.append("," + STYLE_ENTER + getSpaceStringWithCount(space_count));
            } else if ("}".equals(string)) {
                space_count--;
                sb.append("" + STYLE_ENTER + getSpaceStringWithCount(space_count) + "}");
            } else if ("]".equals(string)) {
                space_count--;
                sb.append("" + STYLE_ENTER + getSpaceStringWithCount(space_count) + "]");
            } else {
                sb.append(string);
            }
            // 按照 json 格式过滤处理 string
            // TODO:如果过滤其他，则


        }
        return sb.toString();
    }

    /**
     * @param count 缩进符 个数
     * @return 根据单个缩进符和缩进个数返回 缩进符号组合
     */
    private static String getSpaceStringWithCount(int count) {
        if (count <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(STYLE_SPACE);
        }
        return stringBuffer.toString();
    }

    /**
     * @return 输出顶部横向分隔符组合
     */
    private static String getSeparatorHorizontalsOfTop() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SEPARATOR_HORIZONTAL_COUNT; i++) {
            stringBuilder.append(SEPARATOR_HORIZONTAL);
        }
        stringBuilder.append(STYLE_ENTER);
        stringBuilder.append(getCalledClassName());
        stringBuilder.append(STYLE_ENTER);
        return stringBuilder.toString();
    }

    /**
     * @return 输出底部横向分隔符组合
     */
    private static String getSeparatorHorizontalsOfBottom() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < SEPARATOR_HORIZONTAL_COUNT; i++) {
            stringBuilder.append(SEPARATOR_HORIZONTAL);
        }
        return stringBuilder.toString();
    }

    /**
     * @return 得到调用log的 class Name，并可以点击进入来源
     */
    private static String getCalledClassName() {
        java.util.Map<Thread, StackTraceElement[]> ts = Thread.getAllStackTraces();
        StackTraceElement[] ste = ts.get(Thread.currentThread());
        boolean isAfterCurrentFile = false;
        final String currentFileName = L.class.getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<< ");
        for (StackTraceElement s : ste) {
            if (s.getClassName().equals(currentFileName)) {
                isAfterCurrentFile = true;
            }
            if (isAfterCurrentFile && !s.getClassName().equals(currentFileName)) {
                // 如果没有指定 TAG ，则默认使用文件名作为 TAG
                if (TextUtils.isEmpty(TAG)) {
                    TAG = s.getFileName();
                }
                stringBuilder.append(s.toString());
                break;
            }
        }
        stringBuilder.append(" >>");
        return stringBuilder.toString();
    }

}
