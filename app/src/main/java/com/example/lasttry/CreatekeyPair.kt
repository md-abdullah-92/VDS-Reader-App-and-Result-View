package com.example.lasttry

import android.content.Context
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lasttry.SecurityPage.Companion.copyToClipboard
import com.example.lasttry.SecurityPage.Companion.generateKeyPair
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.PrivateKey
import java.security.PublicKey

class CreatekeyPair {

    companion object {
        @Composable
        fun Createkeypair(navController: NavController) {
            val toastContext = LocalContext.current
            var pk1 = remember { mutableStateOf(TextFieldValue()) }
            var pk2 = remember { mutableStateOf(TextFieldValue()) }
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
                        Textrow1(text = "Praivate Key: ", dp = 15.dp)
                       // var pk1 = remember { mutableStateOf(TextFieldValue()) }
                        OutlinedTextField(
                            value = pk1.value,
                            onValueChange = { pk1.value = it },
                            singleLine = true,
                            label = { Text(text = "") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(15.dp),
                        )
                    }
                }
                item {
                   // Spacer(modifier = Modifier.height(10.dp))
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
                        Textrow1(text = "Public Key", dp = 10.dp)
                       // var pk2 = remember { mutableStateOf(TextFieldValue()) }
                        OutlinedTextField(
                            value = pk2.value,
                            onValueChange = { pk2.value = it },
                            singleLine = true,
                            label = { Text(text = "") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(15.dp),
                        )
                    }
                }
                item {
                    Button(
                        onClick = {
                            if (pk1.value.text.isNotEmpty() && pk2.value.text.isNotEmpty()) {
                                val BASE_URL = "http://192.168.224.116:5001/"
                                val apiService: ApiService by lazy {
                                    Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(ApiService::class.java)
                                }
                                val personalinfo= MainActivity.VDSCreator
                                // Create a RenterInfo object with the entered data
                                val Info = VDSdata(
                                    name = personalinfo?.name ?: "",
                                    //address = address.value.text,
                                    email = personalinfo?.email?:"",
                                    // bkash = bkash.value.text,
                                    publickey = pk1.value.text.toString(),
                                    privateKey = pk2.value.text.toString()
                                )
                                // Make the PUT request
                                apiService.InputVDSdataInfo(Info)
                                    .enqueue(object : Callback<Void> {
                                        override fun onResponse(
                                            call: Call<Void>,
                                            response: Response<Void>
                                        ) {
                                            if (response.isSuccessful) {
                                                MainActivity.VDSdata = Info
                                                // ownerInfo.bkash = bkash.value.text
                                                //   Info.address = address.value.text

                                                showToast(toastContext, "Saved")

                                            } else {
                                                // Handle error
                                                val errorMessage = "Error: ${response.code()}"
                                                // Show error message to the user
                                                showToast(toastContext, errorMessage)
                                            }
                                        }

                                        override fun onFailure(call: Call<Void>, t: Throwable) {
                                            val errorMessage =
                                                "Failed to update: ${t.localizedMessage}"
                                            // Show error message to the user
                                            showToast(toastContext, errorMessage)
                                        }
                                    })
                            }

                            },
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                       // colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                }
                item {
                    Button(
                        onClick = {
                            val keyPair = generateKeyPair()
                            val keyText = Base64.encodeToString(keyPair.public.encoded, Base64.DEFAULT)
                          //  val privateKey: PrivateKey = keyPair.private
                          //  val publicKey: PublicKey = keyPair.public
                        //    val keyText = Base64.encodeToString(keyPair.public.encoded, Base64.DEFAULT)
                            // Convert keys to string representation
                            val privateKeyString = Base64.encodeToString(keyPair.private.encoded, Base64.DEFAULT)
                            val publicKeyString = Base64.encodeToString(keyPair.public.encoded, Base64.DEFAULT)

                            // Update TextField values
                            pk1.value = TextFieldValue(privateKeyString)
                            pk2.value = TextFieldValue(publicKeyString)

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
                item {
                    Button(
                        onClick = {
                            copyToClipboard(toastContext, pk2.value.text)

                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        // colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Copy Public Key",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
        private fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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

