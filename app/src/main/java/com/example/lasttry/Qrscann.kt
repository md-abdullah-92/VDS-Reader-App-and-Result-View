package com.example.lasttry

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.lasttry.ScannedResult.Companion.Scannedresult
import com.example.lasttry.SecurityPage.Companion.differentiate
import com.example.lasttry.ui.theme.LasttryTheme
import java.security.MessageDigest

class Qrscann : ComponentActivity() {
    private lateinit var codeScanner: CodeScanner
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),123)
        }else{
            startscan()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startscan(){
        val scannerView :CodeScannerView=findViewById(R.id.scan)
        codeScanner = CodeScanner(this,scannerView)
        codeScanner.camera=CodeScanner.CAMERA_BACK
        codeScanner.formats=CodeScanner.ALL_FORMATS
        codeScanner.scanMode=ScanMode.CONTINUOUS
        codeScanner.autoFocusMode=AutoFocusMode.CONTINUOUS
        codeScanner.scanMode=ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled=true
        codeScanner.isFlashEnabled=true
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                // val intent = Intent(this, NewPage::class.java).apply {
                //  putExtra("QR_RESULT", it.text)
                // }
                // startActivity(intent)
                setContent {
                    LasttryTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                           val result: Map<String, Any> =differentiate(it.toString())
                            val info = result["Info"]
                            val PublicKey=result["PublicKey"]
                            val HashValue=result["HashValue"]
                            var s ="";
                            if (result["Info"] is Map<*, *>) {
                                val infoMap = result["Info"] as Map<*, *>
                                infoMap.forEach { (key, value) ->

                                        if (key is String && value is String) {
                                            if(s=="")
                                            s += ("$key: $value")
                                            else {
                                                s+=("\n$key: $value");
                                            }
                                        }

                                }
                            }
                            val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
                            val hash: ByteArray = digest.digest(s.toByteArray())
                            val hexString = hash.joinToString("") { "%02x".format(it) }
                            Scannedresult(PublicKey.toString()+"\n"+HashValue.toString()+"\n"+hexString+s+result.toString())
                           // Scannedresult(result.toString());
                        }
                    }
                }
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}

