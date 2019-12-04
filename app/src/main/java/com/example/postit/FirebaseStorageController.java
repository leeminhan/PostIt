package com.example.postit;

import android.net.Uri;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;

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
        void onSuccess(Uri uploadedImgUrl);
        void onError(String err);
    }

    public static void uploadImage(Uri imagePath, String eventName, UploadImageListener listener) {
        StorageReference bitmapRef = getEventStorageRef().child(String.format("%s.jpg", eventName));
        UploadTask uploadTask = bitmapRef.putFile(imagePath);

        uploadTask.addOnFailureListener((Exception ex) -> {
            listener.onError(ex.getMessage());
        }).addOnSuccessListener((UploadTask.TaskSnapshot taskSnapshot) -> {
            uploadTask.continueWithTask((Task<UploadTask.TaskSnapshot> task) -> bitmapRef.getDownloadUrl())
                    .addOnCompleteListener((Task<Uri> task) -> listener.onSuccess(task.getResult()));
        });
    }

    private static StorageReference getStorageReference() {
        if (storageReference == null) {
            storageReference = getFirebaseStorage().getReference();
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
