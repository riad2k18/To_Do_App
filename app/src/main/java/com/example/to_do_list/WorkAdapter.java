package com.example.to_do_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkHolder> {
    private OnitemclickListener listener;
    private List<Work> works = new ArrayList<>();

    @NonNull
    @Override
    public WorkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        return new WorkHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkHolder holder, final int position) {
        final Work currentwork = works.get(position);
        holder.ed_1.setText(currentwork.getEvent_name());
        holder.ed_2.setText(currentwork.getEvent_date());
        holder.ed_3.setText(currentwork.getEvent_duetime());
        holder.textViewpriority.setText(String.valueOf(currentwork.getPriority()));


    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public void setWorks(List<Work> works) {
        this.works = works;
        notifyDataSetChanged();
    }

    public Work getWorkAt(int position) {
        return works.get(position);
    }

    public class WorkHolder extends RecyclerView.ViewHolder {
        private TextView ed_1;
        private TextView ed_2;
        private TextView ed_3;
        private TextView textViewpriority;
        ImageButton delete, edit;

        public WorkHolder(@NonNull View itemView) {
            super(itemView);

            ed_1 = itemView.findViewById(R.id.txt1);
            ed_2 = itemView.findViewById(R.id.txt2);
            ed_3 = itemView.findViewById(R.id.txt3);
            textViewpriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                       listener.onitemclick(works.get(position));
                    }

                    }
                });
            }
        }

        public interface OnitemclickListener {
            void onitemclick(Work work);
        }

        public void setOnItemClickListener(OnitemclickListener listener) {
            this.listener = listener;
        }
    }
