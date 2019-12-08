package com.example.supertourmate.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.supertourmate.Adapters.EventAdapter;
import com.example.supertourmate.AddEvent;
import com.example.supertourmate.Entitys.Events;
import com.example.supertourmate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    private RecyclerView eventRV;
    private FloatingActionButton fbutton;
    private Context context;
    private DatabaseReference rootRf,userRf,userIdRf,eventRf;
    private FirebaseUser currentUser;
    private EventAdapter adapter;


    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootRf = FirebaseDatabase.getInstance().getReference();
        userRf = rootRf.child("Users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userIdRf = userRf.child(currentUser.getUid());
        eventRf = userIdRf.child("Events");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventRV = view.findViewById(R.id.eventRecyclerView);
        fbutton = view.findViewById(R.id.fab);

        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddEvent.class));
            }
        });

        eventRf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Events> eventsList = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Events e = d.getValue(Events.class);
                    eventsList.add(e);
                }

                adapter = new EventAdapter(context,eventsList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                eventRV.setLayoutManager(layoutManager);
                eventRV.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
