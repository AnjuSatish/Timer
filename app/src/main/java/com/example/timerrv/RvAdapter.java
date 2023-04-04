package com.example.timerrv;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private List<Long> mTimeList;
    private CountDownTimer mCountDownTimer;
    private List<String> mListitems;
    public RvAdapter(List<Long> timeList) {
        mTimeList = timeList;
    }
    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rv, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        long remainingTime = mTimeList.get(position);
        holder.timer_tv.setText(formatTime(remainingTime));
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(remainingTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeList.set(position, millisUntilFinished);
                    holder.timer_tv.setText(formatTime(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    mTimeList.set(position, 0L);
                    holder.timer_tv.setText("00:00:00");
                }
            }.start();
        } else {
            mCountDownTimer.start();
        }
    }


    private String formatTime(long millis) {
        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        return hms;
    }
    @Override
    public int getItemCount() {
        return mTimeList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView reward_tv,timer_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reward_tv = (TextView) itemView.findViewById(R.id.reward_tv);
            timer_tv = (TextView) itemView.findViewById(R.id.timer_tv);
        }
    }
}
