/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.keeganlee.kandroid;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import me.keeganlee.kandroid.core.AppAction;
import me.keeganlee.kandroid.core.AppActionImpl;
import okhttp3.OkHttpClient;

/**
 * Application类，应用级别的操作都放这里
 *
 * @version 1.0 创建时间：15/6/25
 */
public class KApplication extends Application {

    private AppAction appAction;

    @Override
    public void onCreate() {
        super.onCreate();
        appAction = new AppActionImpl(this);
        initOKHttp();
    }

    private void initOKHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public AppAction getAppAction() {
        return appAction;
    }
}
