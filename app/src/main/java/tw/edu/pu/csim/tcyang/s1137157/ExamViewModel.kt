package tw.edu.pu.csim.tcyang.s1137157


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExamViewModel : ViewModel() {
    var score by mutableStateOf(0)
    var screenWidth by mutableStateOf(0)
    var screenHeight by mutableStateOf(0)
}
