package com.example.emergencylink.ui.theme.screens.fire

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.emergencylink.R


fun firescreen(navController: NavHostController){

    class MainActivity : AppCompatActivity() {

        private val fireEmergencyNumber = "911" // Change it to the specific emergency number for fire department in your area

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val callButton: Button = findViewById(R.id.callButton)

            callButton.setOnClickListener {
                if (isPermissionGranted()) {
                    makeCall()
                } else {
                    requestCallPermission()
                }
            }
        }

        private fun isPermissionGranted(): Boolean {
            return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        }

        private fun requestCallPermission() {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSION_REQUEST_CALL_PHONE
            )
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == PERMISSION_REQUEST_CALL_PHONE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall()
                } else {
                    Toast.makeText(
                        this,
                        "Permission denied. Cannot make a call.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        private fun makeCall() {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$fireEmergencyNumber")
            startActivity(intent)
        }

        companion object {
            private const val PERMISSION_REQUEST_CALL_PHONE = 1
        }
    }


}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun firescreenPreview(){
    firescreen(rememberNavController())

}