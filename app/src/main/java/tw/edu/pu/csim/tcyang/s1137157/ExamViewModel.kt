package tw.edu.pu.csim.tcyang.s1137157

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

// 定義四個角色及其對應的圖示資源 ID (假設您已經將圖片放入 drawable 資料夾)
// 角色圖片假設為 r_角色名稱.png// ExamViewModel.kt 或單獨的類型定義檔案中
// ExamViewModel.kt 或單獨的類型定義檔案中
enum class Character {
    INFANT,     // 嬰幼兒 (對應 role0.png)
    ADULT,      // 成人 (對應 role1.png)
    CHILD,      // 兒童 (對應 role2.png)
    PUBLIC      // 一般民眾 (對應 role3.png)
}
// 掉落的服務圖示 (假設您有 s_服務名稱.png)
enum class ServiceIcon {
    SERVICE1, SERVICE2, SERVICE3, SERVICE4, SERVICE5, // 假設有五種服務圖示
    // TODO: 請根據您的實際服務圖示數量和名稱在這裡定義
}

// 管理掉落圖示的狀態
data class DroppingIconState(
    val icon: ServiceIcon = ServiceIcon.SERVICE1, // 當前掉落的服務圖示類型
    val x: Float = 0f, // 掉落圖示的水平位置 (DP)
    var y: Float = -300f, // 掉落圖示的垂直位置 (DP, 負值表示在螢幕上方)
    val width: Int = 300, // 圖示寬度 (300px，我們在 DP 環境下暫定 300dp)
    val height: Int = 300, // 圖示高度 (300px，我們在 DP 環境下暫定 300dp)
    val isDropping: Boolean = false // 是否正在掉落
)

class ExamViewModel : ViewModel() {
    // ------------------ 螢幕和分數狀態 (保留) ------------------
    var screenWidth by mutableStateOf(0)
        private set
    var screenHeight by mutableStateOf(0)
        private set
    var score by mutableStateOf(0)
        private set
    fun setScreenSize(width: Int, height: Int) {
        screenWidth = width
        screenHeight = height
    }
    fun setScore(newScore: Int) {
        score = newScore
    }
    // ------------------ 角色放置狀態 (第三題) ------------------

    // 儲存四個角色圖示的 (x, y) 座標，座標值將在 ExamScreen 中計算
    val charPositions = mutableStateOf(mapOf<Character, Pair<Float, Float>>())

    // ------------------ 服務圖示掉落狀態 (第四題) ------------------

    var droppingIconState by mutableStateOf(DroppingIconState())
        private set

    /**
     * 隨機產生一個服務圖示並從螢幕上方中央開始掉落。
     * 這是第四題的起始步驟。
     */
    fun startNewDrop(screenWidth: Int) {
        val randomIcon = ServiceIcon.entries.random()
        val initialX = (screenWidth / 2f) - (droppingIconState.width / 2f)

        droppingIconState = DroppingIconState(
            icon = randomIcon,
            x = initialX,
            y = -droppingIconState.height.toFloat(), // 從螢幕上方，圖示高度之外開始
            isDropping = true
        )
    }

    /**
     * 根據時間間隔更新掉落圖示的垂直位置。
     * 每 0.1 秒往下掉 20dp (近似於 20px)
     */
    fun updateDropPosition(dropAmount: Float = 20f) {
        if (!droppingIconState.isDropping) return

        val newY = droppingIconState.y + dropAmount

        if (newY > screenHeight) {
            // 碰到螢幕下方，重新開始掉落
            startNewDrop(screenWidth)
        } else {
            // 繼續往下掉
            droppingIconState = droppingIconState.copy(y = newY)
        }
    }

    /**
     * 處理服務圖示的水平拖曳。
     */
    fun updateDropHorizontalPosition(deltaX: Float) {
        if (!droppingIconState.isDropping) return

        var newX = droppingIconState.x + deltaX

        // 限制 X 軸不要超出螢幕範圍
        newX = newX.coerceIn(
            minimumValue = 0f,
            maximumValue = (screenWidth - droppingIconState.width).toFloat()
        )

        droppingIconState = droppingIconState.copy(x = newX)
    }
}