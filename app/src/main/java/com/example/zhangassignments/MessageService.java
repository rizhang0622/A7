package com.example.zhangassignments;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageService {
    private DatabaseReference databaseReference;

    public MessageService() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void sendMessage(String receiverUsername, String message) {
        // Generate a unique key for each message
        String messageId = databaseReference.child("messages").child(receiverUsername).push().getKey();
        // Set the message to the unique key
        databaseReference.child("messages").child(receiverUsername).child(messageId).setValue(message);
    }

    public void getAllMessagesForUser(String username, ValueEventListener listener) {
        DatabaseReference messagesRef = databaseReference.child("messages").child(username);
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Use 'messages' as needed
                listener.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}