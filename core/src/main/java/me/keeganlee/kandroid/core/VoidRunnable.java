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

package me.keeganlee.kandroid.core;

import me.keeganlee.kandroid.api.ApiResponse;

/**
 * 当T为Void的时候适用
 * @param <T>
 */
public class VoidRunnable<T> extends DefaultRunnable<T> {
    public VoidRunnable(ActionCallbackListener<T> listener, ApiResponse<T> response) {
        super(listener, response);
    }

    /**
     * 请求接口成功后没有返回数据
     * @param listener 给app层的回调
     * @param obj JSON解析的obj对象
     * @param objList JSON解析的objList对象
     */
    @Override
    public void onListenerSuccess(ActionCallbackListener<T> listener, T obj, T objList) {
            listener.onSuccess(null);
    }
}
