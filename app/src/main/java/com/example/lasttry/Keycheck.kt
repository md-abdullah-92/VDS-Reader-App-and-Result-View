package com.example.lasttry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



class Keycheck {
    companion object{
        @Composable
        fun KeyCheck(results: List<VDSdata>?,navController: NavController) {


            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.vds),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize(),
                    alpha = .1f
                )

            }
            LazyColumn {





                // Result Table
                item {
                    TableHeader()
                }
                if(results!=null) {
                    items(results) { result ->
                        ResultItem(result = result)
                    }
                }



            }
        }
        /**
         * Composable function to display an individual examination result item.
         *
         * @param result Individual examination result.
         */
        @Composable
        fun ResultItem(result: VDSdata?) {
            TableRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TableCell(text = result?.name, weight = 0.2f, alignment = TextAlign.Left, title = false)
                TableCell(text = result?.email, weight = 0.2f, alignment = TextAlign.Left, title = false)
                TableCell(text = result?.publickey.toString(), weight = .3f, alignment = TextAlign.Left, title = false)
                TableCell(text = result?.privateKey.toString(), weight = 0.3f, alignment = TextAlign.Left, title = false)
              //  TableCell(text = result?.Grade, weight = 0.185f, alignment = TextAlign.Left, title = false)
            }
        }

        /**
         * Composable function to create a row in the table layout.
         *
         * @param modifier Modifier for the row.
         * @param content Row content.
         */
        @Composable
        fun TableRow(
            modifier: Modifier = Modifier,
            content: @Composable RowScope.() -> Unit
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.primaryContainer),
                content = content
            )
        }

        /**
         * Composable function to create a table cell within a row.
         *
         * @param text Text content of the cell.
         * @param weight Weight of the cell.
         * @param alignment Text alignment within the cell.
         * @param title Indicates whether the cell is a title cell.
         */
        @Composable
        fun RowScope.TableCell(
            text: String?,

            weight: Float,
            alignment: TextAlign = TextAlign.Center,
            title: Boolean = false,
        ) {
            if (text != null) {
                Text(
                    text = text,
                    Modifier
                        .weight(weight)
                        .padding(10.dp),
                    fontWeight = if (title) FontWeight.Bold else FontWeight.SemiBold,
                    textAlign = alignment,
                    fontSize = 11.sp
                )
            }
        }

        /**
         * Composable function to create the header row of the result table.
         */
        @Composable
        fun TableHeader() {
            TableRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxSize()
            ) {
                TableCell(text = "Name", weight = 0.2f, alignment = TextAlign.Left, title = true)
                TableCell(text = "Email", weight = 0.2f, alignment = TextAlign.Left, title = true)
                TableCell(text = "PublicKey", weight = 0.3f, alignment = TextAlign.Left, title = true)
                TableCell(text = "PrivateKey", weight = 0.3f, alignment = TextAlign.Left, title = true)
               // TableCell(text = "Letter Grade", weight = 0.185f, alignment = TextAlign.Left, title = true)
            }
        }

        /**
         * Composable function to display the entire result table.
         *
         * @param results List of examination results to be displayed in the table.
         */
        @Composable
        fun ResultTable(results: List<VDSdata>?) {
            LazyColumn {
                item {
                    TableHeader()
                }
                if(results!=null) {
                    items(results) { result ->
                        ResultItem(result = result)
                    }
                }
            }
        }
        /**
         * Composable function to display personal information of a student.
         *
         * @param name Student's name.
         * @param regName Student's registration name.
         * @param session Session information.
         */
        @Composable
        fun GetPersonInfo(name: String, regName: String, session: String,dept:String) {
            Personalinfo(string = "Name of Student :$name")
            Personalinfo(string = "Registration No :$regName")
            Personalinfo(string = "Depertment :$dept")
            Personalinfo("Session :$session")

        }
        @Composable
        fun  Personalinfo(string: String){
            Text(
                text = string,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp)
            )
        }

    }
}