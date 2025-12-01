package tw.edu.pu.csim.tcyang.s1137157

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import tw.edu.pu.csim.tcyang.s1137157.ui.theme.S1137157Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 強制螢幕為直式 (PORTRAIT)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 2. 隱藏上方狀態列及下方導覽列
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

        // 隱藏狀態列
        windowInsetsController.hide(WindowInsetsControllerCompat.Type.statusBars())
        // 隱藏導覽列
        windowInsetsController.hide(WindowInsetsControllerCompat.Type.navigationBars())

        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


        enableEdgeToEdge()
        setContent {
            S1137157Theme {
                // 初始化 ExamViewModel
                val examViewModel: ExamViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 呼叫您的 ExamScreen 函式
                    ExamScreen(
                        examViewModel = examViewModel,
                        modifier = Modifier.padding(innerPadding) // 應用 Scaffold 提供的邊距
                    )
                }
            }
        }
    }
}