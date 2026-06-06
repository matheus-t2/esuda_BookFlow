package com.example.bookflow

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

// ── Campo de texto padrão ─────────────────────────────────────────
@Composable
fun VitrineCampoTexto(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(SearchBarColor)
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        if (value.isEmpty()) {
            Text(text = placeholder, color = TextDark.copy(alpha = 0.5f), fontSize = 15.sp)
        }
        BasicTextField(
            value         = value,
            onValueChange = onValueChange,
            modifier      = Modifier.fillMaxWidth(),
            singleLine    = singleLine,
            textStyle     = TextStyle(color = TextDark, fontSize = 15.sp)
        )
    }
}

// ── Seletor de foto de capa ───────────────────────────────────────
@Composable
fun VitrineSeletorFotoCapa(
    fotoUri: String,
    onFotoSelecionada: (String) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? -> uri?.let { onFotoSelecionada(it.toString()) } }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text       = "Foto da capa",
            color      = TextDark,
            fontSize   = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier   = Modifier.padding(start = 8.dp, bottom = 6.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SearchBarColor)
                .border(1.dp, TextDark.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (fotoUri.isNotBlank()) {
                Image(
                    painter            = rememberAsyncImagePainter(fotoUri),
                    contentDescription = "Capa selecionada",
                    modifier           = Modifier.fillMaxSize(),
                    contentScale       = ContentScale.Crop
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector        = Icons.Default.Photo,
                        contentDescription = "Selecionar foto",
                        tint               = TextDark.copy(alpha = 0.5f),
                        modifier           = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Toque para selecionar", color = TextDark.copy(alpha = 0.5f), fontSize = 13.sp)
                }
            }
        }
    }
}

// ── Seletor de arquivo do livro ───────────────────────────────────
@Composable
fun VitrineSeletorArquivo(
    arquivoUri: String,
    onArquivoSelecionado: (String) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? -> uri?.let { onArquivoSelecionado(it.toString()) } }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text       = "Arquivo do livro (PDF/EPUB)",
            color      = TextDark,
            fontSize   = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier   = Modifier.padding(start = 8.dp, bottom = 6.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(SearchBarColor)
                .clickable { launcher.launch("*/*") }
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector        = Icons.Default.AttachFile,
                    contentDescription = "Arquivo",
                    tint               = TextDark.copy(alpha = 0.6f),
                    modifier           = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text  = if (arquivoUri.isNotBlank())
                        arquivoUri.substringAfterLast("/").take(40)
                    else "Selecionar arquivo...",
                    color    = if (arquivoUri.isNotBlank()) TextDark else TextDark.copy(alpha = 0.5f),
                    fontSize = 15.sp
                )
            }
        }
    }
}

// ── Seletor de rating ─────────────────────────────────────────────
@Composable
fun VitrineSeletorRating(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text       = "Avaliação",
            color      = TextDark,
            fontSize   = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier   = Modifier.padding(start = 8.dp, bottom = 6.dp)
        )
        Row {
            repeat(5) { index ->
                val preenchida = index < rating
                Icon(
                    imageVector        = if (preenchida) Icons.Filled.Star else Icons.Outlined.StarOutline,
                    contentDescription = "Estrela ${index + 1}",
                    tint               = if (preenchida) Color(0xFF8B4513) else TextDark.copy(alpha = 0.4f),
                    modifier           = Modifier
                        .size(36.dp)
                        .clickable { onRatingChange(index + 1) }
                )
            }
        }
    }
}

// ── Botões cancelar / publicar na vitrine ─────────────────────────
@Composable
fun VitrineBotoes(
    onCancelar: () -> Unit,
    onPublicar: () -> Unit
) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(50))
                .background(SearchBarColor)
                .clickable { onCancelar() }
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("cancelar", color = TextDark, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(50))
                .background(CardBrown)
                .clickable { onPublicar() }
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("publicar", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

// ── Preview card do formulário ────────────────────────────────────
@Composable
fun VitrinePreviewCard(state: VitrineState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier         = Modifier
                    .size(width = 90.dp, height = 130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3B3060)),
                contentAlignment = Alignment.Center
            ) {
                if (state.fotoCapaUri.isNotBlank()) {
                    Image(
                        painter            = rememberAsyncImagePainter(state.fotoCapaUri),
                        contentDescription = "Capa",
                        modifier           = Modifier.fillMaxSize(),
                        contentScale       = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector        = Icons.Default.Photo,
                        contentDescription = "Sem capa",
                        tint               = TextWhite.copy(alpha = 0.4f),
                        modifier           = Modifier.size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = state.titulo.ifBlank { "Título do livro" },
                    color      = TextWhite,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(state.autor.ifBlank { "Autor" }, color = Color(0xFFEEDDBB), fontSize = 13.sp)
                if (state.anoPublicacao.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text       = "Ano de publicação\n${state.anoPublicacao}",
                        color      = Color(0xFFF5ECD6),
                        fontSize   = 13.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════════════
// Card do livro publicado na vitrine — com ⓘ e botão Excluir
// ═══════════════════════════════════════════════════════════════════
@Composable
fun VitrineCardPublicado(
    livro: LivroVitrine,
    onExcluir: (Long) -> Unit,
    onInfo: (LivroVitrine) -> Unit = {}
) {
    val s = livro.state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            // ── Capa ─────────────────────────────────────────────
            Box(
                modifier         = Modifier
                    .size(width = 90.dp, height = 130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3B3060)),
                contentAlignment = Alignment.Center
            ) {
                if (s.fotoCapaUri.isNotBlank()) {
                    Image(
                        painter            = rememberAsyncImagePainter(s.fotoCapaUri),
                        contentDescription = s.titulo,
                        modifier           = Modifier.fillMaxSize(),
                        contentScale       = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector        = Icons.Default.Photo,
                        contentDescription = "Sem capa",
                        tint               = TextWhite.copy(alpha = 0.4f),
                        modifier           = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // ── Informações + botão Excluir ───────────────────────
            Column(modifier = Modifier.weight(1f)) {

                // Linha título + ícone ⓘ
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text       = s.titulo,
                        color      = TextWhite,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier   = Modifier.weight(1f),
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    // Ícone ⓘ (info)
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(MoreButtonBrown)
                            .clickable { onInfo(livro) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector        = Icons.Default.Info,
                            contentDescription = "Informações",
                            tint               = TextWhite,
                            modifier           = Modifier.size(14.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(s.autor, color = Color(0xFFEEDDBB), fontSize = 13.sp)

                if (s.anoPublicacao.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text       = "Ano de publicação\n${s.anoPublicacao}",
                        color      = Color(0xFFF5ECD6),
                        fontSize   = 13.sp,
                        lineHeight = 17.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Estrelas de avaliação
                Row {
                    repeat(5) { index ->
                        val preenchida = index < s.rating
                        Icon(
                            imageVector        = if (preenchida) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            contentDescription = null,
                            tint               = if (preenchida) Color(0xFF8B4513) else TextWhite.copy(alpha = 0.5f),
                            modifier           = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botão Excluir alinhado à direita
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(MoreButtonBrown)
                            .clickable { onExcluir(livro.id) }
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text       = "Excluir",
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
