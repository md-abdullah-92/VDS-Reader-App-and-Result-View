package com.example.lasttry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class ScannedResult {
    companion object {
        @Composable
        fun Scannedresult(navController: NavController, string: String) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        //shape = RoundedCornerShape(20.dp),
                        brush = Brush.linearGradient(
                            colors = listOf(

                                Color(0xFFFFFFFF),
                                Color(0xFFFFFFFF)
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite,
                            tileMode = TileMode.Decal
                        )
                    )
            )
            LazyColumn {
                item {
                    //Spacer(modifier = Modifier.height(200.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logosust),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                   // Spacer(modifier = Modifier.height(50.dp))

                    Card(

                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(20.dp),
                         border = BorderStroke(3.dp, Color.Blue),
                        modifier = Modifier
                            .size(350.dp),
                    ){
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = string,
                            fontSize = 15.sp,
                          //  fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color= Color.Black,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                    }
                }

            }
        }
    }
}
