package com.example.lasttry


    import android.content.ContentValues.TAG
    import android.content.Context
    import android.net.Uri
    import android.util.Log
    import android.widget.Toast
    import androidx.activity.compose.rememberLauncherForActivityResult
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.wrapContentSize
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.KeyboardActions
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.Card
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.LocalContentColor
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.saveable.rememberSaveable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontFamily
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.ImeAction
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    //import coil.compose.rememberImagePainter
    //import com.example.rentcare.Components.CButton
   /// import com.example.rentcare.ui.theme.Indigo
    //import com.example.rentcare.ui.theme.SkyBlue
    //import com.google.firebase.auth.FirebaseAuth
    import retrofit2.Callback
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import retrofit2.Call
    import retrofit2.Response
   class Signup {
       companion object {
           @OptIn(ExperimentalMaterial3Api::class)
           @Composable
           fun SignUpVDS(
               navController: NavController
           ) {
               val toastContext = LocalContext.current
               val BASE_URL = "http://192.168.224.116:5001/"
               val apiService: ApiService by lazy {
                   Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
                       .create(ApiService::class.java)
               }
               var name = remember { mutableStateOf(TextFieldValue()) }
               // var address = remember { mutableStateOf(TextFieldValue()) }
               var mobile = remember { mutableStateOf(TextFieldValue()) }
               var email = remember { mutableStateOf(TextFieldValue()) }
               // var bkash = remember { mutableStateOf(TextFieldValue()) }
               // var password by remember { mutableStateOf("") }
               //var con_password by remember { mutableStateOf("") }
               var password = remember { mutableStateOf(TextFieldValue()) }
               Surface(
               ) {
                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(top = 10.dp, bottom = 50.dp)
                   ) {


                   }
                   Box(
                       modifier = Modifier
                           .padding(top = 50.dp)
                   ) {
                       Column(
                           horizontalAlignment = Alignment.CenterHorizontally,
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(horizontal = 16.dp)
                               .padding(top = 50.dp)
                               .verticalScroll(rememberScrollState())
                       ) {

                           OutlinedTextField(
                               value = name.value,
                               onValueChange = { name.value = it },
                               singleLine = true,
                               label = { Text(text = "Enter your name") },
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                               shape = RoundedCornerShape(15.dp),
                               keyboardOptions = KeyboardOptions(
                                   keyboardType = KeyboardType.Text,
                                   imeAction = ImeAction.Next
                               )
                           )

                           OutlinedTextField(
                               value = mobile.value,
                               onValueChange = { mobile.value = it },
                               singleLine = true,
                               label = { Text(text = "Mobile") },
                               //colors = TextFieldDefaults.colors(),
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                               shape = RoundedCornerShape(15.dp),
                               keyboardOptions = KeyboardOptions(
                                   keyboardType = KeyboardType.Text,
                                   imeAction = ImeAction.Next
                               )
                           )
                           OutlinedTextField(
                               value = email.value,
                               onValueChange = { email.value = it },
                               singleLine = true,
                               label = { Text(text = "Email") },
                               //colors = TextFieldDefaults.colors(),
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                               shape = RoundedCornerShape(15.dp),
                               keyboardOptions = KeyboardOptions(
                                   keyboardType = KeyboardType.Text,
                                   imeAction = ImeAction.Next
                               )
                           )

                           OutlinedTextField(
                               value = password.value,
                               onValueChange = { password.value = it },
                               singleLine = true,
                               label = { Text(text = "password") },
                               //colors = TextFieldDefaults.colors(),
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                               shape = RoundedCornerShape(15.dp)
                           )

                           Spacer(modifier = Modifier.height(50.dp))
                           CButton(
                               text = "Sign Up",
                               onClick = {
                                   // Create a RenterInfo object with the entered data
                                   val Info = VDSCreator(
                                       name = name.value.text,
                                       //address = address.value.text,
                                       mobile = mobile.value.text,
                                       email = email.value.text,
                                       // bkash = bkash.value.text,
                                       password = password.value.text
                                   )
                                   // Make the PUT request
                                   apiService.InputVDSCreatorInfo(Info)
                                       .enqueue(object : Callback<Void> {
                                           override fun onResponse(
                                               call: Call<Void>,
                                               response: Response<Void>
                                           ) {
                                               if (response.isSuccessful) {
                                                   MainActivity.VDSCreator =Info
                                                   // ownerInfo.bkash = bkash.value.text
                                                   //   Info.address = address.value.text


                                    navController.navigate("profilepage"){
                                       popUpTo("profilepage"){
                                          inclusive = true
                                        }
                                      }
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


                               },
                               // containerColor = Indigo
                           )
                           Spacer(modifier = Modifier.height(10.dp))
                           Row(modifier = Modifier.padding(top = 12.dp, bottom = 40.dp)) {
                               Text(
                                   "Already have an account?",
                                   style = TextStyle(
                                       fontSize = 18.sp,
                                       color = Color.Black
                                   )
                               )
                               Text("Sign in",
                                   style = TextStyle(
                                       fontSize = 18.sp,
                                       fontWeight = FontWeight(800),
                                       color = Color.Black
                                   ),
                                   modifier = Modifier.clickable {
                                       /*   navController.navigate(Screen.Login.route){
                                    popUpTo(Screen.Login.route){
                                        inclusive = true
                                    }
                                }*/
                                   }
                               )
                           }
                       }
                   }
               }
           }

           private fun showToast(context: Context, message: String) {
               Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
           }

           @Preview(showSystemUi = true, showBackground = true)
           @Composable
           fun SignUpScre() {
               SignUpVDS(navController = rememberNavController())
           }

       }
   }
       @Composable
       fun CButton(
           onClick: () -> Unit = {},
           text: String,
           containerColor: Color = LocalContentColor.current // Default to the current content color
       ) {
           // reusable button
           Button(
               onClick = onClick,
               shape = MaterialTheme.shapes.large,
               colors = ButtonDefaults.buttonColors(containerColor),
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
                   .height(52.dp)
           ) {
               Text(
                   text = text,
                   style = TextStyle(
                       fontSize = 22.sp,
                       fontWeight = FontWeight(500),
                       color = Color.White
                   )
               )
           }
       }

