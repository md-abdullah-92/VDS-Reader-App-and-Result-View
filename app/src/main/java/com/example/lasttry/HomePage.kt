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
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(35.dp))
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
                Column(

                    modifier = Modifier.fillMaxSize(),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
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
                                .size(150.dp)
                        ) {
                            // var isSelected = remember { mutableStateOf(false) }
                            Image(
                                painter = painterResource(id = R.drawable.homescreen),
                                contentDescription = "Card",
                                modifier = Modifier
                                    .size(150.dp)
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
                    Spacer(modifier = Modifier.height(16.dp)) // Add some space between Image and Textrow
                    Textrow("Scan Qr Code") // Include the Textrow here
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(id = R.drawable.certificate),
                        contentDescription = "",
                        alignment = Alignment.Center, // Corrected parameter name
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .clickable {
                                navController.navigate("submit")
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Textrow("Check Results")

                }
            }
        }
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