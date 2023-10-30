package kreativemynds.mvvmarchdemo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kreativemynds.mvvmarchdemo.R

val Prompt = FontFamily(
    Font(R.font.prompt_regular),
    Font(R.font.prompt_medium, FontWeight.Medium),
    Font(R.font.prompt_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Prompt,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)