package com.example.zhangassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class A7Activity extends AppCompatActivity {

    private MessageService messageService;
    private EditText receiverEt;

    private RecyclerView recyclerView;
    private StickerAdapter stickerAdapter;
    private Map<String, Integer> lastMessageCounts = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7);


        createNotificationChannel();
        // Initialize the MessageService
        messageService = new MessageService();
        receiverEt = findViewById(R.id.a7_et_receivername);

        recyclerView = findViewById(R.id.a7_rv_stickers);

        findViewById(R.id.iv1).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "1";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv2).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "2";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv3).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "3";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv4).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "4";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });

        findViewById(R.id.iv5).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "5";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });


        findViewById(R.id.iv6).setOnClickListener(view -> {
            if(isEmpty(receiverEt)){
                Toast.makeText(A7Activity.this, "Enter receiver username first", Toast.LENGTH_LONG).show();
                return;
            }
            String receiverUsername = receiverEt.getText().toString(); // Assuming you have these from user input
            String message = "6";
            messageService.sendMessage(receiverUsername, message);
            Toast.makeText(A7Activity.this, "Sticker sent successfully", Toast.LENGTH_LONG).show();

        });
        // Example: Retrieve messages for user1
        messageService.getAllMessagesForUser(getIntent().getStringExtra("username"), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> messages = new ArrayList<>();
                for (DataSnapshot sn : snapshot.getChildren()) {
                    messages.add(sn.getValue(String.class));
                }

                // Check if there are new messages
                Integer lastCount = lastMessageCounts.get(getIntent().getStringExtra("username"));
                if (lastCount == null) lastCount = 0;

                if (messages.size() > lastCount) {
                    showNotification(getIntent().getStringExtra("username"), "You have new messages!");
                }
                lastMessageCounts.put(getIntent().getStringExtra("username"), messages.size());


                recyclerView.setLayoutManager(new LinearLayoutManager(A7Activity.this));


                stickerAdapter = new StickerAdapter(A7Activity.this, messages);
                recyclerView.setAdapter(stickerAdapter);

                recyclerView.scrollToPosition(messages.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(A7Activity.this, error.getDetails(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "A7";
            String description = "Stickers";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String username, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")// replace with your own icon
                .setSmallIcon(R.drawable.img1)
                .setContentTitle("New sticker")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(new Random().nextInt(), builder.build());
    }
}