package com.example.lasttry

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfilePage {
    companion object{
        @Composable
        fun Profilepage(navController: NavController) {
            val toastContext = LocalContext.current

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.vds),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize(),
                   // alpha = .1f
                )

            }
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Image(

                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(15.dp))
                }
                item {
                    GetPersonInfo(
                        name = MainActivity.VDSCreator?.name ?: "",
                        regName = MainActivity.VDSCreator?.email.toString() ?: "", // Corrected typo
                        dept = MainActivity.VDSCreator?.mobile.toString() ?: ""
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                }

                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    CButton(text = "Create Key Pair", onClick = {
                        navController.navigate("createkey") {
                            popUpTo("createkey") {
                               // inclusive = true
                            }
                        }
                    },
                       // containercolor = indigo
                    )

                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    CButton(text = "Create Certificate", onClick = {
                        navController.navigate("VDScreate") {
                            popUpTo("VDScreate") {
                                // inclusive = true
                            }
                        }
                    },
                        // containercolor = indigo
                    )

                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    CButton(text = "Check Keys", onClick = {

                            val BASE_URL = "http://192.168.224.116:5001/"
                            val apiService: ApiService by lazy {
                                Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(ApiService::class.java)
                            }
                            var x= MainActivity.VDSdata?.name.toString();
                            var y=MainActivity.VDSdata?.email.toString();
                            val getStudentInfoCall: Call<List<VDSdata>> =
                                apiService.getPKIInfo(
                                    x,
                                    y
                                )

                            getStudentInfoCall.enqueue(object : Callback<List<VDSdata>> {
                                override fun onResponse(
                                    call: Call<List<VDSdata>>,
                                    response: Response<List<VDSdata>>
                                ) {
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<VDSdata>? = response.body()

                                            MainActivity.pkiList=resultList
                                            navController.navigate("pki")

                                    } else {
                                        // Handle unsuccessful response
                                        Toast.makeText(toastContext, "API call failed. Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                                    }

                                    // Always navigate to "resultpage" whether the API call was successful or not
                                }

                                override fun onFailure(
                                    call: Call<List<VDSdata>>,
                                    t: Throwable
                                ) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })

                    },
                    )


                }


                // Result Table
            }
        }
        @Composable
        fun CButton(
            onClick: () -> Unit = {},
            text: String,
           // containerColor: Color = LocalContentColor.current // Default to the current content color
        ) {
            // reusable button
            Button(
                onClick = onClick,
                shape = MaterialTheme.shapes.large,
              //  colors = ButtonDefaults.buttonColors(""),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                )
            }
        }



        @Composable
        fun GetPersonInfo(name: String, regName: String,dept:String) {
            Personalinfo(string = "Name :$name")
            Personalinfo(string = "Email :$regName")
            Personalinfo(string = "Mobile Number :$dept")

        }
        @Composable
        fun  Personalinfo(string: String){
            Text(
                text = string,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color= Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp)

            )
        }

    }
}