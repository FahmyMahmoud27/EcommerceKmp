package com.route.ecommercekmp.feature.commerce.ui.screen.main_bottom_nav_view.tabs.home_tab.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.ecommercekmp.feature.Blue
import ecommercekmp.shared.generated.resources.Res
import ecommercekmp.shared.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchTextField(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = "What do you search for",
                color = Color.Gray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = Blue, shape = RoundedCornerShape(40.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true
    )
}