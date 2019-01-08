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

import bai.bai.bai.demoaidlservice.BookBean;
import bai.bai.bai.demoaidlservice.IBookManager;

public class BookActivity extends AppCompatActivity {

    private IBookManager mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        aboutService();

        findViewById(R.id.btn_book_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.et_book_name)).getText().toString();
                int price = Integer.parseInt(((EditText) findViewById(R.id.et_book_price)).getText().toString());
                try {
                    mService.addBook(new BookBean(name, price));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    Log.d("baibai", "客户端：添加后的book列表长度：" + mService.getBooks().size());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void aboutService() {
        Intent intent = new Intent();
        intent.setAction("bai.bai.bai.aidl.service_book");
        intent.setPackage("bai.bai.bai.demoaidlservice");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("baibai", "客户端：book 连接 Service 成功");
            mService = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
