package com.example.lasttry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lasttry.StartPage.Companion.Startpage
import com.example.lasttry.ui.theme.LasttryTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lasttry.FullResultView.Companion.Fullresultview
import com.example.lasttry.HomePage.Companion.Homepage
import com.example.lasttry.Resultpage.Companion.ResultPage
import com.example.lasttry.SemesterResultView.Companion.SemesterResult
import com.example.lasttry.SubmitPage.Companion.Submit


class MainActivity : ComponentActivity() {
    companion object DataManager {
        // Variables to store student information, result list, and semester
        var studentInfo: StudentInfo? = null
        var resultList: List<Getdata>?= null
        var semester:String?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LasttryTheme {
                // Surface container using the background color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Remembering the nav controller state
                    val navController = rememberNavController()
                    // Setting up navigation host with start destination
                    NavHost(
                        navController = navController,
                        startDestination = "startpage"
                    ) {
                        // Composable for the start page
                        composable("startpage") {
                            Startpage(navController)
                        }
                        // Composable for the home page
                        composable("homepage") {
                            Homepage(navController)
                        }
                        // Composable for the submit page
                        composable("submit") {
                            Submit((navController))
                        }
                        // Composable for the semester result page
                        composable("semesterresult") {
                            SemesterResult( resultList,navController, semester)
                        }
                        composable("fullresult") {
                            Fullresultview( resultList,navController)
                        }
                        // Composable for the result page
                        composable("resultpage") {
                            ResultPage(navController)
                        }
                    }

                }
            }
        }
    }
}





