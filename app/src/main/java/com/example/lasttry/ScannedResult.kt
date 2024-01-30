package com.example.lasttry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class ScannedResult {
    companion object{
        @Composable
        fun Scannedresult(string: String){
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.sustpic),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            }
            Card(
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(.5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = .5f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = string,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
    }
}