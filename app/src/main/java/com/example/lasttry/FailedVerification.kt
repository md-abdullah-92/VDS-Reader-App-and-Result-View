package com.example.lasttry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class FailedVerification {
    companion object {
        @Composable
        fun Failedvarification() {
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
            )
            {

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
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(20.dp),
                            // border = BorderStroke(3.dp, Color.White),
                            modifier = Modifier
                                .size(400.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.failed),
                                contentDescription = "Card",
                                modifier = Modifier
                                    .size(400.dp)
                                    .clip(RoundedCornerShape(15.dp))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HomePage.Textrow("Failed.")
                    Spacer(modifier = Modifier.height(32.dp))
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }
    }
}