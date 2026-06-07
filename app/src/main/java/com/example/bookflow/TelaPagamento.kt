package com.example.bookflow

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Tela de Pagamento ─────────────────────────────────────────────
@Composable
fun TelaPagamento(
    valor: String = "10,00",
    onVoltar: () -> Unit = {}
) {
    fundoTela {
        TopAppBarComVoltar(onVoltar = onVoltar)

        Box(
            modifier         = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CardPagamento(valor = valor)
        }
    }
}

// ── Card central de pagamento ─────────────────────────────────────
@Composable
private fun CardPagamento(valor: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardBrown)
            .padding(28.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Título
            Text(
                text       = "forma de pagamento:",
                color      = TextWhite,
                fontSize   = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            // Ícones PayPal + Visa
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment     = Alignment.CenterVertically
            ) {
                BadgePayPal()
                BadgeVisa()
            }

            // QR Code simulado
            QrCodeBox()

            // Valor
            Text(
                text       = "Valor: $valor",
                color      = TextWhite,
                fontSize   = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ── Badge PayPal ──────────────────────────────────────────────────
@Composable
private fun BadgePayPal() {
    Box(
        modifier         = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF003087))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text       = "P",
                color      = Color(0xFF009CDE),
                fontSize   = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text       = "P",
                color      = Color(0xFF012169),
                fontSize   = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

// ── Badge Visa ────────────────────────────────────────────────────
@Composable
private fun BadgeVisa() {
    Box(
        modifier         = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFFFFFF))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text       = "VISA",
            color      = Color(0xFF1A1F71),
            fontSize   = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
    }
}

// ── QR Code simulado com células ─────────────────────────────────
@Composable
private fun QrCodeBox() {
    val tamanhoCell = 8.dp
    val grade = listOf(
        listOf(1,1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,1,1),
        listOf(1,0,0,0,0,0,1,0,0,1,1,0,0,1,0,0,0,0,0,1),
        listOf(1,0,1,1,1,0,1,0,1,0,1,0,0,1,0,1,1,1,0,1),
        listOf(1,0,1,1,1,0,1,0,0,1,0,1,0,1,0,1,1,1,0,1),
        listOf(1,0,1,1,1,0,1,0,1,1,0,0,0,1,0,1,1,1,0,1),
        listOf(1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0,0,0,1),
        listOf(1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1),
        listOf(0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0),
        listOf(1,0,1,1,0,1,1,1,0,1,1,0,1,1,0,1,1,0,1,0),
        listOf(0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,1),
        listOf(1,1,0,1,0,1,1,0,0,1,1,0,1,0,0,1,0,0,1,1),
        listOf(0,0,1,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0),
        listOf(1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,0),
        listOf(0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,1,0,0,1,0),
        listOf(1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1),
        listOf(1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0),
        listOf(1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0),
        listOf(1,0,1,1,1,0,1,0,0,1,0,0,0,1,0,1,0,0,0,1),
        listOf(1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,0,1,1,0,0),
        listOf(1,1,1,1,1,1,1,0,0,1,0,1,0,0,0,1,0,0,1,1),
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            grade.forEach { linha ->
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    linha.forEach { celula ->
                        Box(
                            modifier = Modifier
                                .size(tamanhoCell)
                                .background(if (celula == 1) Color.Black else Color.White)
                        )
                    }
                }
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Pagamento",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaPagamentoPreview() {
    TelaPagamento(valor = "10,00")
}
