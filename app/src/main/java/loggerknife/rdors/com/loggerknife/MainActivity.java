package loggerknife.rdors.com.loggerknife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rdors.loggerknife.loggerknifelibrary.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L.init(null, "houshijie");
        // 测试输出 json 字符串
        testPrintJsonString();
        // 测试输出 普通 字符串
        testPrintString();
        // 测试输出 Double
        testPrintDouble();
        // 测试输出 float
        testPrintFloat();
        // 测试输出 class
        testPrintClass();
        // 测试输出 level
        testPrintLevel(L.LogLevel.None);
        // 测试输出null
        testPrintNull();

    }

    /**
     * 输出 json 类型的 字符串
     */
    private void testPrintJsonString() {
        String temp = "{\"status\":0,\"msg\":\"hello,,,,world\",\"sids\":[001,002,003,004,005,006],\"time\":600,\"guide\":{\"label\":\"你好小明\",\"guides\":[\"小红你也好\",\"小绿,你不好\"],\"show_delay\":2,\"sign\":1000001}}";
        L.i(temp);
    }

    /**
     * 输出普通 字符串
     */
    private void testPrintString() {
        final String commonString = "i am string";
        L.v(commonString);
    }

    /**
     * 输出普通 Double
     */
    private void testPrintDouble() {
        final Double commonDouble = 12.12345;
        L.v(commonDouble);
    }

    /**
     * 输出普通 Float
     */
    private void testPrintFloat() {
        final Float commonFloat = 12.12345f;
        L.v(commonFloat);
    }

    /**
     * 输出普通 class
     */
    private void testPrintClass() {
        L.v(this);
    }

    /**
     * 测试输出级别
     */
    private void testPrintLevel(L.LogLevel logLevel) {
        L.init(logLevel, null);
        final String testLevel = "testLevel";
        L.v(testLevel);
        L.i(testLevel);
        L.d(testLevel);
        L.w(testLevel);
        L.e(testLevel);
        L.init(L.LogLevel.All, null);
    }

    private void testPrintNull() {
        L.i(null);
    }
}
