package com.route.ecommercekmp.feature.commerce.ui.screen.cart.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.route.ecommercekmp.feature.commerce.domain.model.Cart

@Composable
internal fun CartTotalBar(cart: Cart) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Total price",
                style = TextStyle(color = Blue.copy(alpha = 0.7f), fontSize = 13.sp)
            )
            Text(
                text = "EGP ${cart.totalPrice}",
                style = TextStyle(color = Blue, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }

        Box(
            modifier = Modifier
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Blue)
                .padding(horizontal = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Check Out  →",
                style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}
