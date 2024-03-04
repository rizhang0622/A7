package com.example.zhangassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class A7Activity extends AppCompatActivity {


    private EditText receiverEt;

    private RecyclerView recyclerView;
    private StickerAdapter stickerAdapter;
    private Map<String, Integer> lastMessageCounts = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7);



        receiverEt = findViewById(R.id.a7_et_receivername);

        recyclerView = findViewById(R.id.a7_rv_stickers);

        findViewById(R.id.iv1).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "1";
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv2).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "2";
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv3).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "3";
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv4).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "4";

            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv5).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "5";

            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });


        findViewById(R.id.iv6).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "6";

            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });
        // Example: Retrieve messages for user1

   }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}