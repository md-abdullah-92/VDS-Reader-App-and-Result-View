package com.example.lasttry

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Resultpage {
    companion object {
        @Composable
        fun ResultPage(navController: NavController) {
            val toastContext = LocalContext.current
            val BASE_URL = "http://192.168.195.116:5001/"
            val apiService: ApiService by lazy {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.sustpic),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
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
                  //  SubmitPage.Textrow1("Semester Wise Result", 15.dp)
                    DropDownBox(navController)
                   // SubmitPage.Textrow1("All Semesters GradeSheet", 15.dp)
                    Button(
                        onClick = {
                            // Handle the click event here
                            // You can add your logic or navigation code
                            navController.navigate("semesterresult")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(30.dp)
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "GradeSheet",
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    //ubmitPage.Textrow1("Certificate", 15.dp)
                    Button(
                        onClick = {
                            // Handle the click event here
                            // You can add your logic or navigation code
                            navController.navigate("semesterresult")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(30.dp)
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = "Certificate",
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }


        @Composable
        fun DropDownBox(navController: NavController) {
            val BASE_URL = "http://192.168.195.116:5001/"
            val apiService: ApiService by lazy {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            val toastContext = LocalContext.current
            var expanded = remember { mutableStateOf(false) }


            Card(
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(30.dp)
                    .padding(16.dp)
                    .clickable { expanded.value = !expanded.value }
            )
            {
                Text(
                    "Semester Wise Result",
                    Modifier.padding(15.dp)
                )
            }


            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.padding(16.dp)
            ) {
                // Create DropdownMenuItems for each option
                DropdownMenuItem(
                    text = {
                        Text(text = "1st Semester")
                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            //val BASE_URL = "http://10.200.195.252:5001/"
                            /*val apiService: ApiService by lazy {
                                Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(ApiService::class.java)
                            }*/
                            val call: Call<List<Getdata>> = apiService.getResults("1st",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="1st"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )

                DropdownMenuItem(
                    text = {
                        Text(text = "2nd Semester")
                    },

                    onClick = {
                         val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            val call: Call<List<Getdata>> = apiService.getResults("2nd",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                           MainActivity.resultList=resultList
                                            MainActivity.semester="2nd"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },

                    )
                DropdownMenuItem(
                    text = {
                            Text(text = "3rd Semester")

                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){

                            val call: Call<List<Getdata>> = apiService.getResults("3rd",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="3rd"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "4th Semester")
                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            val call: Call<List<Getdata>> = apiService.getResults("4th",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="4th"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "5th Semester")
                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            val call: Call<List<Getdata>> = apiService.getResults("5th",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="5th"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "6th Semester")
                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){

                            val call: Call<List<Getdata>> = apiService.getResults("6th",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="6th"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "7th Semester")
                    },

                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            val call: Call<List<Getdata>> = apiService.getResults("7th",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="7th"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "8th Semester")
                    },
                    onClick = {
                        val regNo= MainActivity.studentInfo?.reg_no.toString()
                        if(regNo.isNotEmpty() ){
                            val call: Call<List<Getdata>> = apiService.getResults("8th",regNo.toInt())

                            call.enqueue(object : Callback<List<Getdata>> {
                                override fun onResponse(call: Call<List<Getdata>>, response: Response<List<Getdata>>) {
                                    //Handle successful response
                                    val resultList: List<Getdata>? = response.body()
                                    if (response.isSuccessful) {
                                        // Handle successful response
                                        val resultList: List<Getdata>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.resultList=resultList
                                            MainActivity.semester="8th"
                                            navController.navigate("semesterresult")
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

                                override fun onFailure(call: Call<List<Getdata>>, t: Throwable) {
                                    // Handle network failure
                                    // You might want to show an error message or take appropriate action
                                    Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                        else{
                            Toast.makeText(toastContext,"Please Insert All The Value", Toast.LENGTH_SHORT).show()
                        }
                    },
                )
            }
        }
    }
}