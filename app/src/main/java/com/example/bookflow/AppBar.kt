package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.Menu,
            contentDescription = "Menu",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier            = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier         = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF5C3A1E)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter            = painterResource(id = R.drawable.img),
                    contentDescription = "logo",
                    modifier           = Modifier.size(120.dp)
                )
            }
            Text(
                text          = "BookFlow",
                color         = TextWhite,
                fontSize      = 12.sp,
                fontWeight    = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}
