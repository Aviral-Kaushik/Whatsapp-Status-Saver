package com.aviral.whatsappstatussaver.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class PermissionsManager {

    private static final String TAG = "PermissionManager";

    private static final int VERIFY_PERMISSION_REQUEST = 1;

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_STORAGE_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final String[] ACCESS_NETWORK_STATE = {
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    private static boolean checkPermission(String permission, Context context) {

        int permissionRequest = ActivityCompat.checkSelfPermission(context, permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkPermission: Permission Not Granted for " + permission);
            return false;
        } else {
            Log.d(TAG, "checkPermission: Permission Granted for " + permission);
            return true;
        }

    }

    public static void requestPermission(String[] permissions, Activity activity) {
        ActivityCompat.requestPermissions(
                activity,
                permissions,
                VERIFY_PERMISSION_REQUEST
        );
    }

    public static boolean checkAndVerifyPermission(String[] permissions, Context context) {

        for (String permission : permissions) {
            if (checkPermission(permission, context)) {
                return false;
            }
        }

        return true;

    }

}
