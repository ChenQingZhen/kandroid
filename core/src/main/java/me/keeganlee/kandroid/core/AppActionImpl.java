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
package me.keeganlee.kandroid.core;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.BaseAdapter;

import me.keeganlee.kandroid.api.Api;
import me.keeganlee.kandroid.api.ApiImpl;
import me.keeganlee.kandroid.api.ApiResponse;
import me.keeganlee.kandroid.core.base.BaseAction;
import me.keeganlee.kandroid.core.runnable.ObjListRunnable;
import me.keeganlee.kandroid.core.runnable.VoidRunnable;
import me.keeganlee.kandroid.executor.JobExecutor;
import me.keeganlee.kandroid.model.CouponBO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AppAction接口的实现类
 *
 * @author Keegan小钢
 * @date 15/6/25
 * @version 1.0
 */
public class AppActionImpl extends BaseAction implements AppAction {

    private final static int LOGIN_OS = 1; // 表示Android
    private final static int PAGE_SIZE = 20; // 默认每页20条

    private Context context;
    private Api api;


    public AppActionImpl(Context context) {
        this.context = context;
        this.api = new ApiImpl();
    }

    @Override
    public void sendSmsCode(final String phoneNum, final ActionCallbackListener<Void> listener) {
        // 参数检查
        if (TextUtils.isEmpty(phoneNum)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "手机号为空");
            return;
        }
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(phoneNum);
        if (!matcher.matches()) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "手机号不正确");
            return;
        }
        // 请求Api
        JobExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                final ApiResponse<Void> response= api.sendSmsCode4Register(phoneNum);
                handler.post(new VoidRunnable<>(listener,response));
            }
        });



    }

    @Override
    public void register(final String phoneNum, final String code, final String password, final ActionCallbackListener<Void> listener) {
        // 参数检查
        if (TextUtils.isEmpty(phoneNum)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "手机号为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "验证码为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "密码为空");
            return;
        }
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(phoneNum);
        if (!matcher.matches()) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "手机号不正确");
            return;
        }

        // 请求Api
        JobExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                ApiResponse response= api.registerByPhone(phoneNum,code,password);
                handler.post(new VoidRunnable<>(listener,response));
            }
        });

    }

    @Override
    public void login(final String loginName, final String password,@NonNull final ActionCallbackListener<Void> listener) {
        // 参数检查
        if (TextUtils.isEmpty(loginName)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "登录名为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "密码为空");
            return;
        }

        // 请求Api
        JobExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = telephonyManager.getDeviceId();
                ApiResponse<Void> response= api.loginByApp(loginName, password, imei, LOGIN_OS);
                handler.post(new VoidRunnable<>(listener,response));
            }
        });

    }

    @Override
    public void listCoupon(final int currentPage, final ActionCallbackListener<List<CouponBO>> listener) {
        // 参数检查
        if (currentPage < 0) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "当前页数小于零");
        }

        // 请求Api
        JobExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                ApiResponse<List<CouponBO>> response= api.listNewCoupon(currentPage, PAGE_SIZE);
                handler.post(new ObjListRunnable<>(listener,response));
            }
        });

    }

}
