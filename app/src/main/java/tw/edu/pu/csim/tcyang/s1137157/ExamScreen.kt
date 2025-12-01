package tw.edu.pu.csim.tcyang.s1137157

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExamScreen(examViewModel: ExamViewModel = viewModel()) {
    // 取得螢幕寬高
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration) {
        examViewModel.screenWidth = configuration.screenWidthDp
        examViewModel.screenHeight = configuration.screenHeightDp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 圖片
            Image(
                painter = painterResource(id = R.drawable.happy), // 替換成你的圖片
                contentDescription = "Exam Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )

            // 主文字
            Text(
                text = "瑪利亞基金會服務大考驗",
                style = MaterialTheme.typography.headlineMedium
            )

            // 作者
            Text(
                text = "作者: 資科4b 胡雯晴(1)",
                style = MaterialTheme.typography.bodyMedium
            )

            // 螢幕大小
            Text(
                text = "螢幕大小: ${examViewModel.screenWidth} x ${examViewModel.screenHeight} px",
                style = MaterialTheme.typography.bodyMedium
            )

            // 成績
            Text(
                text = "成績: ${examViewModel.score} 分",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


