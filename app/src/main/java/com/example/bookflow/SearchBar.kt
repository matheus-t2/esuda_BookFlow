package com.example.bookflow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Barra de pesquisa ─────────────────────────────────────────────
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(SearchBarColor)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier          = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value         = query,
                onValueChange = { query = it },
                modifier      = Modifier.weight(1f),
                singleLine    = true,
                textStyle     = TextStyle(
                    color    = TextDark,
                    fontSize = 16.sp
                )
            )
            Icon(
                imageVector        = Icons.Default.Search,
                contentDescription = "Pesquisar",
                tint               = TextDark,
                modifier           = Modifier.size(24.dp)
            )
        }
    }
}
