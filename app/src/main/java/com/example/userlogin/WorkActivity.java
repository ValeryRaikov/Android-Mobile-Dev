package com.example.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class WorkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_work);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView textUsers = (TextView) findViewById(R.id.searchBtn);
        Button Logout = (Button) findViewById(R.id.logoutBtn);
        Button showAllUsers = (Button) findViewById(R.id.showUsersBtn);
        Button addUser = (Button) findViewById(R.id.addUserBtn);
        Button deleteUser = (Button) findViewById(R.id.deleteUserBtn);

        final Database db = new Database(this);
        final List<User> users = db.getAllUsers();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(
                new Intent(WorkActivity.this, AddUserActivity.class));
            }
        });

        showAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textUsers.setText("");
                for (User us : users) {
                    String log = textUsers.getText() +"\n" + "Id: " + us.getId() + " ,Name: "
                    + us.getUserName() + " ,Pass: " + us.getPassword();

                    textUsers.setText(log);
                }

                Toast.makeText(WorkActivity.this,"All users is loaded!",Toast.LENGTH_LONG).show();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}