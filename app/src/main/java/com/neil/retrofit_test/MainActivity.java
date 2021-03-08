package com.neil.retrofit_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.neil.retrofit_test.bean.Response;
import com.neil.retrofit_test.service.IUserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();

    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = this.findViewById(R.id.search_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAction(v);
            }
        });
    }



    public void onAction(View view) {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口对象
        IUserService iUserService = retrofit.create(IUserService.class);

        //调用登陆方法
        final Call<Response> call = iUserService.login("123", "123");


        //发送请求 --- 同步
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                retrofit2.Response<Response>  retrofitResponse = null;
//                try {
//                    retrofitResponse = call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.i(TAG, "onAction: " + retrofitResponse.body());
//            }
//        }).start();
//
//

        //发送请求 异步
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}