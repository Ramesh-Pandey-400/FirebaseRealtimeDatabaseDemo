package com.rameshpandey.firebaserealtimedatabasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rameshpandey.firebaserealtimedatabasedemo.model.User;

import java.util.List;
//extends arrray adpater for that  and it is for Users
public class UserList extends ArrayAdapter<User> {
//now create a contaxt and we also need a list as list stores all artist
    private Activity context;
    private List<User> userList;
//create constructor and it initialise all the values
    public UserList(Activity context, List<User> userList) {
        //calling of super and pass the context and layout file and a userlist
        super(context, R.layout.user_list_layout,userList);
        //initialise variable
        this.context = context;
        this.userList = userList;
    }
    //now override method getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //here we will create layout inflator object
        /*LayoutInflater is a fundamental component in Android.
        You must use it all the time to turn xml files into view hierarchies
         */
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View listViewItem=layoutInflater.inflate(R.layout.user_list_layout,null,true);
        TextView textViewUserName=(TextView) listViewItem.findViewById(R.id.textViewUserName);
        TextView textViewUserOccupation=(TextView) listViewItem.findViewById(R.id.textViewUserOccupation);

        User user=userList.get(position);

        textViewUserName.setText(user.getUserName());
        textViewUserOccupation.setText(user.getUserOccupation());
        return  listViewItem;
    }
}
