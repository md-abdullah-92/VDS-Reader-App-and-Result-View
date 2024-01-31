package com.example.lasttry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lasttry.SemesterResultView.Companion.GetPersonInfo
import com.example.lasttry.SemesterResultView.Companion.ResultItem
import com.example.lasttry.SemesterResultView.Companion.TableCell
import com.example.lasttry.SemesterResultView.Companion.TableHeader
import com.example.lasttry.SemesterResultView.Companion.TableRow


class FullResultView {
    companion object {
        @Composable
        fun Fullresultview(
            results: List<Getdata>?,
            navController: NavController,
        ) {
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
                    Image(
                        painter = painterResource(id = R.drawable.logosust),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
                item {
                    Text(
                        text = "SHAHJALAL UNIVERSITY OF SCIENCE & TECHNOLOGY, SYLHET, BANGLADESH",
                        fontSize = 11.5.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF4B0082),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    GetPersonInfo(
                        name = MainActivity.studentInfo?.name ?: "",
                        regName = (MainActivity.studentInfo?.reg_no.toString() )?: "", // Corrected typo
                        dept = MainActivity.studentInfo?.dept.toString() ?: "",
                        session = MainActivity.studentInfo?.session ?: "",
                    )
                }
                item {
                    Text(
                        text = "1st Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "1st")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "1st")
                    Spacer(modifier = Modifier.height(32.dp))
                }


                item {
                    Text(
                        text = "2nd Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "2nd")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "2nd")
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Text(
                        text = "3rd Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "3rd")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "3rd")
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Text(
                        text = "4th Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "4th")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "4th")
                    Spacer(modifier = Modifier.height(32.dp))
                }


                item {
                    Text(
                        text = "5th Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "5th")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "5th")
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Text(
                        text = "6th Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "6th")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "6th")
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Text(
                        text = "7th Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "7th")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "7th")
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Text(
                        text = "8th Semester Examination",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Magenta,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                item {
                    TableHeader()
                }
                if (results != null) {
                    items(results) { result ->
                        if (result.semester == "8th")
                            ResultItem(result = result)
                    }
                }
                item {
                    Sum(results, "8th")
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

        }

        private @Composable
        fun Sum(results: List<Getdata>?, s: String) {

            fun getLetterGrade(gpa: Double): String {
                return when {
                    gpa >= 4.0 -> "A+"
                    gpa >= 3.75 -> "A"
                    gpa >= 3.5 -> "A-"
                    gpa >= 3.25 -> "B+"
                    gpa >= 3.0 -> "B"
                    gpa >= 2.75 -> "B-"
                    gpa >= 2.5 -> "C+"
                    gpa >= 2.25 -> "C"
                    gpa >= 2.00 -> "C-"
                    else -> "F"
                }
            }

            val sumOfCourseCredit =
                results?.filter { it.GPA != 0.0 && it.semester == s }
                    ?.sumByDouble { it.course_credit.toDouble() }
            val sumOfWeightedCredits =
                results?.filter { it.GPA != 0.0 && it.semester == s }
                    ?.sumByDouble { it.course_credit.toDouble() * it.GPA }
            var formattedGpa: Double? = null

            if (sumOfCourseCredit != null && sumOfWeightedCredits != null && sumOfCourseCredit != 0.0) {
                formattedGpa = sumOfWeightedCredits / sumOfCourseCredit
            }

            val Gpa = formattedGpa?.let { String.format("%.2f", it) } ?: ""
            val Grade = formattedGpa?.let { getLetterGrade(gpa = it) } ?: ""


            TableRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize()
            ) {
                TableCell(
                    text = "Total",
                    weight = 0.49f,
                    alignment = TextAlign.Left,
                    title = false
                )
                TableCell(
                    text = sumOfCourseCredit?.toString() ?: "",
                    weight = 0.185f,
                    alignment = TextAlign.Left,
                    title = false
                )
                TableCell(
                    text = "GPA: " + Gpa,
                    weight = 0.185f,
                    alignment = TextAlign.Left,
                    title = false
                )
                TableCell(
                    text = Grade,
                    weight = 0.185f,
                    alignment = TextAlign.Left,
                    title = false
                )
            }

        }

    }
}
