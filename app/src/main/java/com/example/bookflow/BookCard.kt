package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

// ── Card de livro clicável ────────────────────────────────────────
@Composable
fun BookCard(
    book: Book,
    onClick: () -> Unit = {}   // ← callback de clique
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .clickable { onClick() }   // ← torna o card clicável
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            // ── Capa do livro ────────────────────────────────────
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

            // ── Informações textuais ─────────────────────────────
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

                // ── Badge de preço e botão "mais" ────────────────
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