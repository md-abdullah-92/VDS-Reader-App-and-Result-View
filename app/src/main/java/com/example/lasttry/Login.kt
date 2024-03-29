package com.example.lasttry

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login {

    companion object{
        @Composable
        fun login(navController: NavController){
            val toastContext = LocalContext.current
            Box(modifier = Modifier.fillMaxWidth()){
                Image(painter = painterResource(id = R.drawable.sustpic),
                    contentDescription = "",
                    contentScale= ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            }

            Column {
                Spacer(modifier = Modifier.height(200.dp))
                Card(
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = .5f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Textrow1(text = "Email : ",10.dp)
                    // Spacer(modifier = Modifier.height(10.dp))
                    var email = remember { mutableStateOf(TextFieldValue()) }
                    OutlinedTextField(
                        value =email.value ,
                        onValueChange ={ email.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your Email") },
                        colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),
                       /* keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.None
                        )*/

                    )

                    Textrow1(text = "Password : ",10.dp)
                    //Spacer(modifier = Modifier.height(10.dp))
                    var dateofbirth = remember { mutableStateOf(TextFieldValue()) }
                    OutlinedTextField(
                        value =dateofbirth.value ,
                        onValueChange ={ dateofbirth.value=it},
                        singleLine = true,
                        label = { Text(text = "Password") },
                        //            colors = TextFieldDefaults.colors()
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(),
                        /* keyboardOptions = KeyboardOptions(
                             keyboardType = KeyboardType.Number,
                             imeAction = ImeAction.Done,
                             capitalization = KeyboardCapitalization.None
                         ),*/
                        // keyboardOpotion=

                    )
                    Button(
                        onClick = {
                            if(email.value.text.isNotEmpty() and dateofbirth.value.text.isNotEmpty()){
                                val BASE_URL = "http://192.168.224.116:5001/"
                                val apiService: ApiService by lazy {
                                    Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(ApiService::class.java)
                                }
                                val email = email.value.text
                                val getStudentInfoCall: Call<List<VDSCreator>> =
                                    apiService.getVDSCreatorInfo(
                                        email,
                                        dateofbirth.value.text
                                    )

                                getStudentInfoCall.enqueue(object : Callback<List<VDSCreator>> {
                                    override fun onResponse(
                                        call: Call<List<VDSCreator>>,
                                        response: Response<List<VDSCreator>>
                                    ) {
                                        if (response.isSuccessful) {
                                            // Handle successful response
                                            val resultList: List<VDSCreator>? = response.body()
                                            if (resultList != null && resultList.isNotEmpty()) {
                                                MainActivity.VDSCreator = resultList.first()
                                                navController.navigate("profilepage")

                                            } else {
                                                // Handle the case where the response body is empty or null
                                                // You might want to show an error message or take appropriate action
                                            }
                                        } else {
                                            // Handle unsuccessful response
                                            Toast.makeText(toastContext, "API call failed. Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                                        }

                                        // Always navigate to "resultpage" whether the API call was successful or not
                                    }

                                    override fun onFailure(
                                        call: Call<List<VDSCreator>>,
                                        t: Throwable
                                    ) {
                                        // Handle network failure
                                        // You might want to show an error message or take appropriate action
                                        Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                    }
                                })}
                            else{
                                Toast.makeText(toastContext,"Please Insert All The Value",Toast.LENGTH_LONG).show()
                            }
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.White,
                            fontSize = 20.sp
                            // fontWeight = FontWeight.ExtraBold  // Uncomment this line if you want to set the font weight
                        )
                    }



                }


            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp),
                onClick = {
                    navController.navigate("signup")
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = "Create an Account",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    }
        }

        }
        @Composable
        fun Textrow1(text:String,dp: Dp){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.White // Set the font color to white
                    ),
                    modifier = Modifier.padding(dp)

                )
            }
        }

