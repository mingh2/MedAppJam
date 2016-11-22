package com.example.mingh.medappjam;

/**
 * Created by jessicazeng1127 on 11/15/16.
 */
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Provides UI for the view with Cards.
 */
public class ActivityFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView picture;
        public TextView name;
        public TextView description;
        public Button button;
        public ViewHolder(final LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_activity, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            button = (Button) itemView.findViewById(R.id.action_button);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 10;
        private final String[] mGesture;
        private final String[] mGestureDesc;
        private final Drawable[] mGesturePictures;
        private final Context context;
        public ContentAdapter(Context context) {
            this.context = context;
            Resources resources = context.getResources();
            mGesture = resources.getStringArray(R.array.gesture);
            mGestureDesc = resources.getStringArray(R.array.gesture_desc);
            TypedArray a = resources.obtainTypedArray(R.array.gesture_picture);
            mGesturePictures = new Drawable[a.length()];
            for (int i = 0; i < mGesturePictures.length; i++) {
                mGesturePictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
            holder.picture.setImageDrawable(mGesturePictures[position % mGesturePictures.length]);
            holder.name.setText(mGesture[position % mGesture.length]);
            holder.description.setText(mGestureDesc[position % mGesturePictures.length]);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MotionActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}