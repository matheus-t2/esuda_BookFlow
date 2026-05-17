package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookCard(book: Book) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Capa do livro
            Box(
                modifier         = Modifier
                    .size(width = 90.dp, height = 130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3B3060)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter            = painterResource(id = book.coverRes),
                    contentDescription = book.title,
                    modifier           = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Informações do livro
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = book.title,
                    color      = TextWhite,
                    fontSize   = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines   = 2,
                    overflow   = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text     = book.author,
                    color    = Color(0xFFEEDDBB),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text       = book.description,
                    color      = Color(0xFFF5ECD6),
                    fontSize   = 13.sp,
                    maxLines   = 4,
                    overflow   = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Botões de preço e "mais"
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Box(
                        modifier         = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(PriceBadge)
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text       = book.price,
                            color      = TextWhite,
                            fontSize   = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier         = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(MoreButtonBrown)
                            .padding(horizontal = 18.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text       = "mais",
                            color      = TextWhite,
                            fontSize   = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
