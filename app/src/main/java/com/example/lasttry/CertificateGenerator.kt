package com.example.lasttry

import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lasttry.MainActivity.DataManager.p
import com.example.lasttry.SecurityPage.Companion.encryptText
import com.example.lasttry.SecurityPage.Companion.getPublicKeyFromString
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class CertificateGenerator {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @Composable
        fun VDScreate(navController: NavController) {
            var pk1 = remember { mutableStateOf(TextFieldValue()) }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.vds),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            }
            LazyColumn {

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Textrow1(text = "CERTIFICATE INFO", dp = 15.dp)
                        // var pk1 = remember { mutableStateOf(TextFieldValue()) }
                        OutlinedTextField(
                            value = pk1.value,
                            onValueChange = { pk1.value = it },
                            singleLine = false,
                            label = { Text(text = "") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(15.dp),
                        )
                        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
                        val hash: ByteArray = digest.digest(pk1.value.text.toByteArray())
                        val hexString = hash.joinToString("") { "%02x".format(it) }
                        val cr="HashValue:\n"+hexString+"\n\n"+"Info:\n"+pk1.value.text
                    }
                }
                item {
                    Button(
                        onClick = {
                            val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
                            val hash: ByteArray = digest.digest(pk1.value.text.toByteArray())
                            val hexString = hash.joinToString("") { "%02x".format(it) }
                            val cr="HashValue:\n"+hexString+"\n\n"+"Info:\n"+pk1.value.text+"\n\nSigned By:\n"+ MainActivity.VDSCreator?.name.toString()+"\nEmail: "+MainActivity.VDSCreator?.email+"\nMobile No.: "+MainActivity.VDSCreator?.mobile
                            p=cr;
                            navController.navigate("result")

                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        // colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Generate Certificate",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                }
                item {
                    Button(
                        onClick = {
                            val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
                            val hash: ByteArray = digest.digest(pk1.value.text.toByteArray())
                            val hexString = hash.joinToString("") { "%02x".format(it) }
                            var cr =""
                            cr= "HashValue:\n$hexString\n\nInfo:\n${pk1.value.text}"
                            p=cr;

                            val publickey = MainActivity.VDSdata?.publickey.toString()
                          //  val keyBytes = java.util.Base64.getDecoder().decode(privateKeyBytes)
                          //  val keySpec = X509EncodedKeySpec(keyBytes) // Assuming private key is in X.509 format
                          //  val keyFactory = KeyFactory.getInstance("RSA")
                           // val privateKey: PrivateKey = keyFactory.generatePrivate(keySpec)

                            // Assuming encrypt() is a function that encrypts a string with RSA private key

                           val Publickey =getPublicKeyFromString(publickey)

                            val encryptedData = Publickey?.let { encryptText(cr, it) }

                            if (encryptedData != null) {
                                p=encryptedData
                            }
                            navController.navigate("result")
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        // colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Generate Key",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                }

            }
        }
        private @Composable
        fun Textrow1(text: String, dp: Dp) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(dp)
                )
            }
        }
    }
}