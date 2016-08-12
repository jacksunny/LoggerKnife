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
    private static LogLevel logLevel = LogLevel.All;
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
    private static final String SEPARATOR_VERTICAL = "";
    /**
     * 默认回车之后加上竖直分隔符
     */
    private static final String STYLE_ENTER = "\n" + SEPARATOR_VERTICAL;

    /**
     * 每3000个字符就切割（建议在 3000左右，实测 3705字符之后的字符无法显示）
     */
    private static final int MAX_PRINT_LENGTH = 3000;

    public enum LogLevel {
        All(-1), Verbose(0), Debug(1), Info(2), Warn(3), Error(4), None(100);
        private final int mIndex;

        private LogLevel(int index) {
            mIndex = index;
        }

        /**
         * @return 得到先后顺序
         */
        public int getIndex() {
            return mIndex;
        }
    }

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
    private static void logAll(String tagName, LogLevel logLevel, Object object, Throwable tr) {
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
        LogAllItems(tagName, logLevel, items, tr);
    }

    /**
     * 根据切割的字符串数组输出超长 log
     *
     * @param tagName  如果不为空，以该tagName 为 TAG
     * @param logLevel 输出控制级别
     * @param items    切割的字符串数组
     */
    private static void LogAllItems(String tagName, LogLevel logLevel, String[] items, Throwable tr) {
        if (items == null || items.length == 0) {
            return;
        }
        String thisTag = TAG;
        if (!TextUtils.isEmpty(tagName)) {
            thisTag = tagName;
        }
        for (String item : items) {
            switch (logLevel) {
                case All:
                case Verbose:
                    Log.v(thisTag, item, tr);
                    break;
                case Debug:
                    Log.d(thisTag, item, tr);
                    break;
                case Info:
                    Log.i(thisTag, item, tr);
                    break;
                case Warn:
                    Log.w(thisTag, item, tr);
                    break;
                case Error:
                    Log.e(thisTag, item, tr);
                    break;
                case None:
                default:
                    break;
            }
        }
    }

    /**
     * 日志输出级别：V
     *
     * @param object 输出对象
     */
    public static void v(Object object) {
        v(null, object, null);
    }

    /**
     * 日志输出级别：V
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void v(String tagName, Object object) {
        v(tagName, object, null);
    }

    /**
     * 日志输出级别：V
     *
     * @param tr      An exception to log
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void v(String tagName, Object object, Throwable tr) {
        logAll(tagName, LogLevel.Verbose, object, tr);
    }

    /**
     * 日志输出级别：D
     *
     * @param object 输出对象
     */
    public static void d(Object object) {
        d(null, object, null);
    }

    /**
     * 日志输出级别：D
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void d(String tagName, Object object) {
        d(tagName, object, null);
    }

    /**
     * 日志输出级别：D
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void d(String tagName, Object object, Throwable tr) {
        logAll(tagName, LogLevel.Debug, object, tr);
    }

    /**
     * 日志输出级别：I
     *
     * @param object 输出对象
     */
    public static void i(Object object) {
        i(null, object, null);
    }

    /**
     * 日志输出级别：I
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void i(String tagName, Object object) {
        i(tagName, object, null);
    }

    /**
     * 日志输出级别：I
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void i(String tagName, Object object, Throwable tr) {
        logAll(tagName, LogLevel.Info, object, tr);
    }

    /**
     * 日志输出级别：W
     *
     * @param object 输出对象
     */
    public static void w(Object object) {
        w(null, object, null);
    }

    /**
     * 日志输出级别：W
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void w(String tagName, Object object) {
        w(tagName, object, null);
    }

    /**
     * 日志输出级别：W
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void w(String tagName, Object object, Throwable tr) {
        logAll(tagName, LogLevel.Warn, object, tr);
    }

    /**
     * 日志输出级别：E
     *
     * @param object 输出对象
     */
    public static void e(Object object) {
        e(null, object, null);
    }

    /**
     * 日志输出级别：E
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void e(String tagName, Object object) {
        e(tagName, object, null);
    }

    /**
     * 日志输出级别：E
     *
     * @param tagName tag 名称，以该名称为 TAG
     * @param object  输出对象
     */
    public static void e(String tagName, Object object, Throwable tr) {
        logAll(tagName, LogLevel.Error, object, tr);
    }

    /**
     * 是否传进来的 级别 能够输出
     *
     * @param ll 传进来的级别
     * @return 和当前输出级别对比，如果在之后，就能输出
     */
    private static boolean isCurrentTypeCanLogOut(LogLevel ll) {
        return ll.getIndex() >= logLevel.getIndex();
    }


    /**
     * @param object 输出的对象
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
        String content = object.toString();
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
        int spaceCount = 0;
        StringBuffer sb = new StringBuffer();
        // 是否检测到逗号需要换行
        boolean isCommaChangeLine = false;
        for (int i = 0; i < length; i++) {
            char charI = object.charAt(i);
            String string = String.valueOf(charI);

            // 按照 json 格式过滤处理 string
            if (string.equals("{")) {
                spaceCount++;
                sb.append("{");
                sb.append(STYLE_ENTER);
                sb.append(getSpaceStringWithCount(spaceCount));
                isCommaChangeLine = true;
            } else if ("[".equals(string)) {
                spaceCount++;
                sb.append("[");
                sb.append(STYLE_ENTER);
                sb.append(getSpaceStringWithCount(spaceCount));
                isCommaChangeLine = true;
            } else if (",".equals(string)) {
                sb.append(",");
                if (isCommaChangeLine) {
                    sb.append(STYLE_ENTER);
                    sb.append(getSpaceStringWithCount(spaceCount));
                }
            } else if ("}".equals(string)) {
                spaceCount--;
                sb.append(STYLE_ENTER);
                sb.append(getSpaceStringWithCount(spaceCount));
                sb.append("}");
            } else if ("]".equals(string)) {
                spaceCount--;
                sb.append(STYLE_ENTER);
                sb.append(getSpaceStringWithCount(spaceCount));
                sb.append("]");
            } else if ("\"".equals(string)) {
                isCommaChangeLine = !isCommaChangeLine;
                sb.append("\"");
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
