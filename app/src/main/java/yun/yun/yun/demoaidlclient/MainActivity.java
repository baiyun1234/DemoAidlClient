package yun.yun.yun.demoaidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import bai.bai.bai.demoaidlservice.IAddAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IAddAidlInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutService();

        findViewById(R.id.btn_add_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int x = Integer.parseInt(((EditText) findViewById(R.id.et_add_client_x)).getText().toString());
                int y = Integer.parseInt(((EditText) findViewById(R.id.et_add_client_y)).getText().toString());
                try {
                    Log.d("baibai", "客户端 相加结果：" + mService.add(x, y));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void aboutService() {
        Intent intent = new Intent();
        intent.setAction("bai.bai.bai.aidl.service_add");//5.0出现以后必须使用显示启动，不然会报错（加了下一行代码可解决这个问题）
        intent.setPackage("bai.bai.bai.demoaidlservice");//加了这行代码，就可以解决5.0版本后隐式启动服务报错的问题
        Log.d("baibai", "aboutService");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("baibai", "客户端：连接 Service 成功");
            mService = IAddAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}
