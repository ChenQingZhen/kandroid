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

package me.keeganlee.kandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.keeganlee.kandroid.R;

public class TestRecyclerAdapter extends BaseRecyclerAdapter<String,TestRecyclerAdapter.ViewHolder> {

    public TestRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= mLayoutInflater.inflate(R.layout.item_list_coupon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str= mItemList.get(position);
        holder.titleTv.setText(str);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv= (TextView) itemView.findViewById(R.id.text_item_title);
        }
    }
}
