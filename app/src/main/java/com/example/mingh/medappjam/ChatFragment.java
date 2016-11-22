package com.example.mingh.medappjam;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Provides UI for the view with Cards.
 */
public class ChatFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ChatFragment.ContentAdapter adapter = new ChatFragment.ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        //public TextView description;
        public Context context;
        public ViewHolder(final LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_chat, parent, false));
           // picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.friend_name);
            //description = (TextView) itemView.findViewById(R.id.card_text);
            //button = (Button) itemView.findViewById(R.id.action_button);
            context = inflater.getContext();

        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ChatFragment.ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] names;
        //private final String[] mGestureDesc;
        private final Drawable[] mchats;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            names  = resources.getStringArray(R.array.message);
            TypedArray a = resources.obtainTypedArray(R.array.message);
            mchats = new Drawable[a.length()];
            for (int i = 0; i < mchats.length; i++) {
                mchats[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ChatFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChatFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ChatFragment.ViewHolder holder, int position) {
           // holder.picture.setImageDrawable(mGesturePictures[position % mGesturePictures.length]);
            holder.name.setText(names[position % names.length]);
            //holder.description.setText(mGestureDesc[position % mGesturePictures.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
