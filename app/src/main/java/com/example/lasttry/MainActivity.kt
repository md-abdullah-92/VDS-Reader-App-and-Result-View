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
import com.example.lasttry.HomePage.Companion.Homepage
import com.example.lasttry.Resultpage.Companion.ResultPage
import com.example.lasttry.SemesterResultView.Companion.SemesterResult
import com.example.lasttry.SubmitPage.Companion.Submit


class MainActivity : ComponentActivity() {
    companion object DataManager {
        var studentInfo: StudentInfo? = null
        var resultList: List<Getdata>?= null
        var semester:String?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LasttryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "startpage"
                    ) {
                        composable("startpage") {
                            Startpage(navController)
                        }
                        composable("homepage") {
                            Homepage(navController)
                        }

                        composable("submit") {
                            Submit((navController))
                        }
                        composable("semesterresult") {
                            SemesterResult( resultList,navController, semester)
                        }
                        composable("resultpage") {
                            ResultPage(navController)
                        }
                    }

                }
            }
        }
    }
}





