package com.example.supertourmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.supertourmate.Entitys.Events;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText eventNameET,startLocET,destinationET,departureDateET,budgetET;
    private DatabaseReference rootRf,userRf,userIdRf,eventRf;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Create Event");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        eventNameET = findViewById(R.id.eventNameET);
        startLocET = findViewById(R.id.start_locationET);
        destinationET = findViewById(R.id.destinationET);
        departureDateET = findViewById(R.id.departure_dateET);
        budgetET = findViewById(R.id.budgetET);

        rootRf = FirebaseDatabase.getInstance().getReference();
        userRf = rootRf.child("Users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userIdRf = userRf.child(currentUser.getUid());
        eventRf = userIdRf.child("Events");
    }

    public void SaveEvent(View view) {

        String eventName = eventNameET.getText().toString();
        String strLoc = startLocET.getText().toString();
        String destination =destinationET.getText().toString();
        String depDate = departureDateET.getText().toString();
        String budget = budgetET.getText().toString();
        String eventID = eventRf.push().getKey();

        Events event = new Events(eventID,eventName,strLoc,destination,depDate,(int) Double.parseDouble(budget));
        eventRf.child(eventID).setValue(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(AddEvent.this,HomeActivity.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddEvent.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                     }
        });



    }

    public void gatDate(View view) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,AddEvent.this,year,month +1,day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i2 + " / " + i1 + " / " + i;
        departureDateET.setText(date);

    }
}
