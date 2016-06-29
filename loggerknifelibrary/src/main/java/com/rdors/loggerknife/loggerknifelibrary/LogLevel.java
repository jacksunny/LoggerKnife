package com.rdors.loggerknife.loggerknifelibrary;

/**
 * Created by houshijie on 6/27/16.
 * 定义level级别
 */
public enum LogLevel {
    Verbose(0), Debug(1), Info(2), Warn(3), Error(4), Assert(5);
    private int mIndex;

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
