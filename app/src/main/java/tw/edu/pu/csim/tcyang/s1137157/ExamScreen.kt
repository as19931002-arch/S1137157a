package tw.edu.pu.csim.tcyang.s1137157

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExamScreen(
    examViewModel: ExamViewModel = viewModel(),
    modifier: Modifier = Modifier // 👈 關鍵修正：將 modifier 放在函式參數列表內
) {
    // 取得螢幕尺寸 (DP)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // 儲存螢幕尺寸到 ViewModel (只執行一次)
    LaunchedEffect(Unit) {
        examViewModel.setScreenSize(screenWidth, screenHeight)
        Log.d("ExamScreen", "Screen width: $screenWidth, height: $screenHeight")
    }

    Box(
        // 👈 關鍵修正：將傳入的 modifier 應用到最外層，然後才接上 fillMaxSize 和 background
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFEB3B)), // 黃色背景
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp) // 元素間距 10dp
        ) {
            // 圖片 (R.drawable.happy 應該對應到您複製到 drawable 資料夾的 happy.png)
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop
            )

            // 中間文字
            Text(
                text = "瑪利亞基金會服務大考驗",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            // 作者資訊 (使用您原始提供的資訊)
            Text(
                text = "作者: 資科四B  胡雯晴",
                fontSize = 16.sp
            )

            // 螢幕尺寸 (從 ViewModel 讀取)
            Text(
                text = "螢幕大小: ${examViewModel.screenWidth}dp x ${examViewModel.screenHeight}dp",
                fontSize = 14.sp
            )

            // 成績 (從 ViewModel 讀取)
            Text(
                text = "成績: ${examViewModel.score}分",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ExamScreen.kt 或相關檔案中

fun getCharacterDrawable(character: Character): Int {
    return when (character) {
        // 假設 R.drawable.role0 已經指向您的 role0.png 檔案
        Character.INFANT -> R.drawable.role0
        Character.ADULT -> R.drawable.role1
        Character.CHILD -> R.drawable.role2
        Character.PUBLIC -> R.drawable.role3
    }
}