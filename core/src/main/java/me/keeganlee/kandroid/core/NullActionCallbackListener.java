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

/**
 * 回调的空实现，不想回调的时候就传这个,如果你想回调还是使用{@link ActionCallbackListener}
 * @param <T>
 */
public final class NullActionCallbackListener<T> implements ActionCallbackListener<T> {
    @Override
    public void onSuccess(T data) {

    }

    @Override
    public void onFailure(String errorEvent, String message) {

    }
}
