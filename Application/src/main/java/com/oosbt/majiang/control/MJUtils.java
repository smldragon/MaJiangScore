package com.oosbt.majiang.control;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Frank on 1/14/2016.
 */
public class MJUtils {

    public static void requestDangerousPermission(Activity actAskingForPerm, String permissionName, int requestCalledBackID) {
        int permissionCheck = ContextCompat.checkSelfPermission(actAskingForPerm, permissionName);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(actAskingForPerm, permissionName)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.e("DEBUG", "---------------------------------shouldShowRequestPermissionRationale is true");

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(actAskingForPerm,
                        new String[]{permissionName}, requestCalledBackID);

                Log.e("DEBUG", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!shouldShowRequestPermissionRationale is false");
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
