package com.example.lasttry

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController


class HomePage {

    companion object{
        @Composable
        fun Homepage(navController: NavController) {
            // Composable function to display the main UI of the home page
            // Function to handle the home page UI layout
            val context = LocalContext.current
          //  Spacer(modifier = Modifier.height(35.dp))
            // Box with a linear gradient background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        //shape = RoundedCornerShape(20.dp),
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF00FFFF),
                                Color(0xFF4169e1)
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite,
                            tileMode = TileMode.Decal
                        )
                    )
            ) {
                // Column to arrange UI elements vertically
                Column(

                    modifier = Modifier.fillMaxSize(),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(

                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(25.dp))
                        var isSelected = remember { mutableStateOf(false) }
                        Card(

                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            // border = BorderStroke(3.dp, Color.White),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("loginpage")
                                }
                                .size(100.dp)
                        ) {
                            // var isSelected = remember { mutableStateOf(false) }
                            Image(
                                painter = painterResource(id = R.drawable.homescreen),
                                contentDescription = "Card",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(
                                        if (isSelected.value) Color.Red.copy(alpha = 0.8f) else Color(
                                            0xFFFFA500
                                        ).copy(alpha = 0.8f)
                                    )
                                // Set background color to orange
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Spacer between Image and Textrow
                    Textrow("Create Seal") // Textrow to display text
                    Spacer(modifier = Modifier.height(32.dp))

                    // Box containing a clickable Card with an Image (Qr Code Scanner)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        var isSelected = remember { mutableStateOf(false) }
                        Card(

                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            // border = BorderStroke(3.dp, Color.White),
                            modifier = Modifier
                                .clickable {
                                    val intent = Intent(context, Qrscann::class.java)
                                    context.startActivity(intent)
                                }
                                .size(100.dp)
                        ) {
                            // var isSelected = remember { mutableStateOf(false) }
                            Image(
                                painter = painterResource(id = R.drawable.homescreen),
                                contentDescription = "Card",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(
                                        if (isSelected.value) Color.Red.copy(alpha = 0.8f) else Color(
                                            0xFFFFA500
                                        ).copy(alpha = 0.8f)
                                    )
                                // Set background color to orange
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Spacer between Image and Textrow
                    Textrow("Scan Qr Code") // Textrow to display text
                    Spacer(modifier = Modifier.height(32.dp))
                    // Image (Certificate) with navigation to "submit" destination on click
                    Image(
                        painter = painterResource(id = R.drawable.certificate),
                        contentDescription = "",
                        alignment = Alignment.Center, // Corrected parameter name
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .clickable {
                                navController.navigate("submit")
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Textrow for "Check Results"
                    Textrow("Check Results")

                }
            }
        }

        // Composable function to display a row of text
        @Composable
        fun Textrow(text:String){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp,
                    style = TextStyle(
                        color = Color.White // Set the font color to white
                    ),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }


    }
}