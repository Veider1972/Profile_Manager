package ru.veider.profilemanager.ui.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.permissions.*
import ru.veider.profilemanager.R
import java.security.Permission

@ExperimentalPermissionsApi
@Composable
fun PermissionsRequest(
    activity: Activity,
    onGranted: @Composable () -> Unit
) {
    val permissionsList = arrayListOf(Manifest.permission.CHANGE_WIFI_STATE,
                                      Manifest.permission.ACCESS_WIFI_STATE/*,
                                      Manifest.permission.WRITE_SECURE_SETTINGS,
                                      Manifest.permission.UPDATE_DEVICE_STATS,
                                      Manifest.permission.INTERNET,
                                      Manifest.permission.WAKE_LOCK*/
    )
    val permissionsState = rememberMultiplePermissionsState(permissions = permissionsList)
    val context = LocalContext.current

    when {
        permissionsState.allPermissionsGranted && Settings.System.canWrite(context) -> {
            onGranted()
        }
        else                                                                        -> {
            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(1f),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(LocalContext.current.resources.getString(R.string.permission_title), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(20.dp))
                if (!Settings.System.canWrite(context)) {
                    Button(modifier = Modifier.fillMaxWidth(),
                           onClick = {
                               val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                                   data = Uri.parse("package:${activity.packageName}")

                               }
                               context.startActivity(intent)
                           }) {
                        Text(text = LocalContext.current.resources.getString(R.string.permission_WRITE_SETTINGS))
                    }
                }
                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
                                     permission = Manifest.permission.CHANGE_WIFI_STATE, buttonText = R.string.permission_CHANGE_WIFI_STATE
                )
                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
                                     permission = Manifest.permission.ACCESS_WIFI_STATE, buttonText = R.string.permission_ACCESS_WIFI_STATE
                )
//                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
//                                     permission = Manifest.permission.WRITE_SECURE_SETTINGS, buttonText = R.string.permission_WRITE_SECURE_SETTINGS
//                )
//
////                if (!permissionsState.permissions[permissionsList.indexOf(Manifest.permission.WRITE_SECURE_SETTINGS)].status.isGranted) {
////                    Button(modifier = Modifier.fillMaxWidth(),
////                           onClick = {
////                               val intent = Intent(Settings.ACTION_SECURITY_SETTINGS).apply {
////                                   data = Uri.parse("package:${activity.packageName}")
////
////                               }
////                               context.startActivity(intent)
////                           }) {
////                        Text(text = LocalContext.current.resources.getString(R.string.permission_WRITE_SECURE_SETTINGS))
////                    }
////                }
//
//                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
//                                     permission = Manifest.permission.UPDATE_DEVICE_STATS, buttonText = R.string.permission_WRITE_SECURE_SETTINGS
//                )
//                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
//                                     permission = Manifest.permission.INTERNET, buttonText = R.string.permission_INTERNET
//                )
//                ShowPermissionButton(permissionsState = permissionsState, permissionsList = permissionsList,
//                                     permission = Manifest.permission.WAKE_LOCK, buttonText = R.string.permission_WAKE_LOCK
//                )
                Spacer(modifier = Modifier.height(50.dp))
                TextButton(modifier = Modifier.fillMaxWidth(), onClick = { (context as Activity).finish() }) {
                    Text(LocalContext.current.resources.getString(R.string.permission_cancelled))
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun ShowPermissionButton(permissionsState: MultiplePermissionsState, permissionsList: List<String>, permission: String, buttonText: Int) {
    val permissionIndex = permissionsList.indexOf(permission)
    if (!permissionsState.permissions[permissionIndex].status.isGranted) {
        Button(modifier = Modifier.fillMaxWidth(),
               onClick = {
                   permissionsState.permissions[permissionIndex].launchPermissionRequest()
               }) {
            Text(text = LocalContext.current.resources.getString(buttonText))
        }
    }
}