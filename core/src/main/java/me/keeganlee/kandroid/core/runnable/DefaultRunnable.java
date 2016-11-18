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

package me.keeganlee.kandroid.core.runnable;

import java.util.List;

import me.keeganlee.kandroid.api.ApiResponse;
import me.keeganlee.kandroid.core.ActionCallbackListener;

public abstract class DefaultRunnable<T> implements Runnable {
    protected ActionCallbackListener<T> listener;
    protected ApiResponse<T> response;

    public DefaultRunnable(ActionCallbackListener<T> listener, ApiResponse<T> response){
        this.listener=listener;
        this.response=response;
    }

    @Override
    public void run() {
        if (response != null) {
            if (response.isSuccess()) {
                onListenerSuccess(listener,response.getObj(),response.getObjList());
            } else {
                listener.onFailure(response.getEvent(), response.getMsg());
            }
        }
    }

    /**
     * 要实现这个抽象方法有三种：
     * 1.在实现的方法里:listener.onSuccess(null);//这种情况适用于请求接口成功后没有返回数据
     * 2.在实现的方法里：listener.onSuccess(obj);//这种情况适用于请求接口成功后返回对象类型的数据
     * 3.在实现的方法里：listener.onSuccess(objList);//这种情况适用于请求接口成功后返回列表类型的数据
     * @param listener 给app层的回调
     * @param obj JSON解析的obj对象
     * @param objList JSON解析的objList对象
     */
    public abstract void onListenerSuccess(ActionCallbackListener<T> listener,T obj,T objList);
}
