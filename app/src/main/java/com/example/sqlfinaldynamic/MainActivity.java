package com.example.sqlfinaldynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBUser dbUser = new DBUser(this);
        User user = new User();
        user.setId(23);
        user.setFirstName("Chris");
        user.setLastName("Bjork");
        user.setEmail("Testing@live.se");
        user.setPhone("+46720925146");

        if(dbUser.addSingle(user))
        {
            Toast.makeText(this, "Successfully created user", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Failed to create user", Toast.LENGTH_SHORT).show();
        }
    }
}