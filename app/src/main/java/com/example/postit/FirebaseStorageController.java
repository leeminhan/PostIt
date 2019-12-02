package com.example.postit;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class FirebaseStorageController {
    private final static String TAG = "FirebaseStorageCont";
    private static FirebaseStorage firebaseStorage;
    private static StorageReference storageReference;
    private static StorageReference eventStorageRef;

    public static FirebaseStorage getFirebaseStorage() {
        if (firebaseStorage == null) {
            firebaseStorage = FirebaseStorage.getInstance();
        }
        return firebaseStorage;
    }

    public interface UploadImageListener {
        void onSuccess();
        void onError(String err);
    }

    public static void uploadImage(String imagePath, String eventName, UploadImageListener listener) {
        Uri file = Uri.fromFile(new File(imagePath));
        StorageReference bitmapRef = getEventStorageRef().child(String.format("%s.jpg", eventName));
        UploadTask uploadTask = bitmapRef.putFile(file);

        uploadTask.addOnFailureListener((Exception ex) -> {
            listener.onError(ex.getMessage());
        }).addOnSuccessListener((UploadTask.TaskSnapshot taskSnapshot) -> {
            listener.onSuccess();
        });
    }

    private static StorageReference getStorageReference() {
        if (storageReference == null) {
            storageReference = firebaseStorage.getReference();
        }
        return storageReference;
    }

    public static StorageReference getEventStorageRef() {
        if (eventStorageRef == null) {
            eventStorageRef = getStorageReference().child("events");
        }
        return eventStorageRef;
    }
}
