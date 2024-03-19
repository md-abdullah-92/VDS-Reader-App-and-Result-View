package com.example.lasttry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

class certificate {
    companion object {
        @Composable
        fun CRpage(navController: NavController,string: String) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.sustlogo),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize(),
                    alpha = .1f
                )

            }
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
                    Text(
                        text = "SHAHJALAL UNIVERSITY OF SCIENCE & TECHNOLOGY, SYLHET, BANGLADESH",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF123DB4),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {

                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = string,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
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
