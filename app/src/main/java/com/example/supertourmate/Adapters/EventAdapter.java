package com.example.supertourmate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supertourmate.Entitys.Events;
import com.example.supertourmate.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<Events> eventsList;

    public EventAdapter(Context context, List<Events> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_row,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.eventTitel.setText(eventsList.get(position).getEventName());
        holder.startLoc.setText(eventsList.get(position).getStart_Loc());
        holder.deDate.setText(eventsList.get(position).getDepDate());

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitel,startLoc,deDate;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitel = itemView.findViewById(R.id.row_nameTV);
            startLoc = itemView.findViewById(R.id.row_startLoc);
            deDate = itemView.findViewById(R.id.row_DeDate);
        }
    }
}
