package com.rameshpandey.firebaserealtimedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rameshpandey.firebaserealtimedatabasedemo.model.User;

public class MainActivity extends AppCompatActivity {

    Button buttoAddUser;
    Spinner spinnerOccupation;
    EditText editTextUserName;
    DatabaseReference databaseReferenceUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttoAddUser=findViewById(R.id.buttonAddUser);
        spinnerOccupation=findViewById(R.id.spinnerOccupation);
        editTextUserName=findViewById(R.id.editTextTextuserName);
        databaseReferenceUser= FirebaseDatabase.getInstance().getReference("User");
        buttoAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });
    }
    private void addArtist(){

        String userName=editTextUserName.getText().toString();
        String userOccupation=spinnerOccupation.getSelectedItem().toString();

        if(!TextUtils.isEmpty(userName)){
            //to create a unique id in firebase database use push method
            String userId=databaseReferenceUser.push().getKey();
            User user=new User(userId,userName,userOccupation);

            //to store value we use setvalue method
            databaseReferenceUser.child(userId).setValue(user);
            Toast.makeText(this, "User Added!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please Enter a name!!", Toast.LENGTH_SHORT).show();
        }

    }

}