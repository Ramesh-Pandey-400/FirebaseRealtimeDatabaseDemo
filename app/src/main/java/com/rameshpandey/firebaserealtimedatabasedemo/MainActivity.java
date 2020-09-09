package com.rameshpandey.firebaserealtimedatabasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rameshpandey.firebaserealtimedatabasedemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listViewUsers;
    Button buttoAddUser;
    Spinner spinnerOccupation;
    EditText editTextUserName;
    List<User> userList;
    DatabaseReference databaseReferenceUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttoAddUser=findViewById(R.id.buttonAddUser);
        spinnerOccupation=findViewById(R.id.spinnerOccupation);
        editTextUserName=findViewById(R.id.editTextTextuserName);
        listViewUsers=findViewById(R.id.listViewUsers);
        userList=new ArrayList<>();
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

    @Override
    protected void onStart() {
        super.onStart();
        databaseReferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            //on datachange method will be executing every time we will change anything in database
            //used to read data of database
            //as it will fetch all the values at specified refrence
            //it will contain all the data inside datasnapshot object
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot:dataSnapshot.getChildren()){

                    User user=userSnapshot.getValue(User.class);
                    userList.add(user);

                }
                //create array adapter WE CAN also use custom array adapter i.e. UserList

                UserList adpater=new UserList(MainActivity.this,userList);
                listViewUsers.setAdapter(adpater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}