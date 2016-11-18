/*
 * Copyright (C) 2016. Keegan小钢（http://keeganlee.me）
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

package me.keeganlee.kandroid.core.base;

import android.os.Handler;
import android.os.Looper;

import me.keeganlee.kandroid.core.ActionCallbackListener;

/**
 * Action的工具类
 */
public class BaseAction {
    protected Handler handler=new Handler(Looper.getMainLooper());

    /**
     *
     * @param listener Action的处理结果回调监听器
     * @param errorEvent 错误码
     * @param message 错误信息
     */
    protected void onFailure(ActionCallbackListener<?> listener, String errorEvent, String message){
        if(listener!=null){
            listener.onFailure(errorEvent,message);
        }
    }
}
