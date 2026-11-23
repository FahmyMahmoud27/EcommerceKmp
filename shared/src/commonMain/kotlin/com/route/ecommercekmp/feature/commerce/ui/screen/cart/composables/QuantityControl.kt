package com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.ecommercekmp.feature.Blue
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun QuantityControl(
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Blue)
                .clickable { onDecrease() },
            contentAlignment = Alignment.Center
        ) {
            Text("−", color = Color.White, fontSize = 16.sp)
        }

        Text(
            text = quantity.toString(),
            style = TextStyle(color = Blue, fontSize = 14.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Blue)
                .clickable { onIncrease() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = "Increase quantity",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
