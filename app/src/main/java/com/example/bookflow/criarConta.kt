package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Tela Criar Conta ──────────────────────────────────────────────
@Composable
fun CriarConta(
    onCriarClick: () -> Unit = {}
) {
    fundoTela {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            CriarContaLogoSection()

            Spacer(modifier = Modifier.height(48.dp))

            CriarContaTitulo()

            Spacer(modifier = Modifier.height(24.dp))

            CriarContaFormulario(onCriarClick = onCriarClick)
        }
    }
}

// ── Logo + texto bicolor ──────────────────────────────────────────
@Composable
private fun CriarContaLogoSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter            = painterResource(id = R.drawable.img),
            contentDescription = "BookFlow Logo",
            modifier           = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color(0xFF4A2C0A), fontWeight = FontWeight.Bold)) {
                    append("Book")
                }
                withStyle(SpanStyle(color = Color(0xFFB85C00), fontWeight = FontWeight.Bold)) {
                    append("Flow")
                }
            },
            fontSize = 42.sp
        )
    }
}

// ── Pill com título "Criar conta" ─────────────────────────────────
@Composable
private fun CriarContaTitulo() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(SearchBarColor)
            .padding(horizontal = 32.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text       = "Criar conta",
            color      = TextDark,
            fontSize   = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

// ── Formulário: e-mail, senha e botão "Criar" ─────────────────────
@Composable
private fun CriarContaFormulario(onCriarClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier            = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CriarContaCampo(
            value         = email,
            onValueChange = { email = it },
            placeholder   = "e-mail"
        )

        CriarContaCampo(
            value                = senha,
            onValueChange        = { senha = it },
            placeholder          = "Senha:",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Botão "Criar" com clickable funcional
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(SearchBarColor)
                    .clickable { onCriarClick() }
                    .padding(horizontal = 32.dp, vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text     = "Criar",
                    color    = TextDark,
                    fontSize = 15.sp
                )
            }
        }
    }
}

// ── Campo de texto reutilizável ───────────────────────────────────
@Composable
private fun CriarContaCampo(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(SearchBarColor)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        if (value.isEmpty()) {
            Text(
                text     = placeholder,
                color    = TextDark.copy(alpha = 0.6f),
                fontSize = 16.sp
            )
        }
        BasicTextField(
            value                = value,
            onValueChange        = onValueChange,
            modifier             = Modifier.fillMaxWidth(),
            singleLine           = true,
            visualTransformation = visualTransformation,
            textStyle            = TextStyle(
                color    = TextDark,
                fontSize = 16.sp
            )
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Criar Conta",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun CriarContaPreview() {
    CriarConta()
}