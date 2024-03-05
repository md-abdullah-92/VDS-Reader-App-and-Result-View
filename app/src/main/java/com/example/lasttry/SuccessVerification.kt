package com.example.lasttry

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class SuccessVerification {
    companion object {
        @Composable
        fun SuccessfulVerification(navController: NavController,string: String){
            Spacer(modifier = Modifier.height(100.dp))
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

                Column(

                    modifier = Modifier.fillMaxSize(),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(200.dp))
                    // Box containing a clickable Card with an Image (Qr Code Scanner)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(300.dp)
                    ) {
                        //var isSelected = remember { mutableStateOf(false) }
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(

                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            // border = BorderStroke(3.dp, Color.White),
                            modifier = Modifier
                                .size(400.dp)
                        ) {
                            // var isSelected = remember { mutableStateOf(false) }
                            Image(
                                painter = painterResource(id = R.drawable.success),
                                contentDescription = "Card",
                                modifier = Modifier
                                    .size(400.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    /*.background(
                                        if (isSelected.value) Color.Red.copy(alpha = 0.8f) else Color(
                                            0xFFFFA500
                                        ).copy(alpha = 0.8f)
                                    )*/
                                // Set background color to orange
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Spacer between Image and Textrow
                    HomePage.Textrow("Successfully verified.") // Textrow to display text
                    Spacer(modifier = Modifier.height(32.dp))
                    // Image (Certificate) with navigation to "submit" destination on click
                   /* Image(
                        painter = painterResource(id = R.drawable.certificate),
                        contentDescription = "",
                        alignment = Alignment.Center, // Corrected parameter name
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .clickable {
                              //  navController.navigate("submit")
                            }
                    )*/
                    Spacer(modifier = Modifier.height(16.dp))
                    LaunchedEffect(Unit) {
                        delay(2000) // Simulating a 2-second delay
                        navController.navigate("startpage")
                    }

                }
            }

        }
    }

}

