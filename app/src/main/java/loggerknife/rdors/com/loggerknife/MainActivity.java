package loggerknife.rdors.com.loggerknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rdors.loggerknife.loggerknifelibrary.L;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        L.init(LogLevel.Error,"houshijie");
        String temp = "{\"status\":0,\"msg\":\"\",\"sids\":[6601,2014,6700,8100,9009,7501],\"expire\":600,\"speech_guide\":{\"label\":\"大家都在搜\",\"guides\":[\"我要报志愿\",\"你好度秘\"],\"show_delay\":2,\"sign\":1603298702}}";

//        double abc = 1.2345;
        L.i(temp);
//        L.e(temp);

    }
}
