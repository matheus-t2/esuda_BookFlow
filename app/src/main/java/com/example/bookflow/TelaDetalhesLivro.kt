package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Tela de Detalhes do Livro ─────────────────────────────────────
@Composable
fun TelaDetalhesLivro(
    book: Book,
    onVoltar: () -> Unit = {}
) {
    fundoTela {
        // AppBar com botão voltar
        TopAppBarComVoltar(onVoltar = onVoltar)

        // Barra de pesquisa (mantém consistência visual com TelaInicial)
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        // Conteúdo rolável
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // ── Card principal com capa + info ───────────────────
            CardDetalhes(book = book)

            Spacer(modifier = Modifier.height(20.dp))

            // ── Metadados do livro ───────────────────────────────
            MetadadosLivro(book = book)

            Spacer(modifier = Modifier.height(28.dp))

            // ── Botões de ação ───────────────────────────────────
            BotoesAcao(book = book)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── AppBar com botão de voltar ────────────────────────────────────
@Composable
private fun TopAppBarComVoltar(onVoltar: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.ArrowBack,
            contentDescription = "Voltar",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
                .clickable { onVoltar() }
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

// ── Card com capa, título, autor, descrição e estrelas ────────────
@Composable
private fun CardDetalhes(book: Book) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .padding(12.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                // Capa do livro
                Box(
                    modifier         = Modifier
                        .size(width = 120.dp, height = 170.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF3B3060)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter            = painterResource(id = book.coverRes),
                        contentDescription = book.title,
                        modifier           = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Título, autor e descrição
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text       = book.title,
                        color      = TextWhite,
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text       = book.author,
                        color      = Color(0xFFEEDDBB),
                        fontSize   = 14.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text       = book.description,
                        color      = Color(0xFFF5ECD6),
                        fontSize   = 14.sp,
                        lineHeight = 19.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ── Estrelas de avaliação ────────────────────────────
            AvaliacaoEstrelas(rating = book.rating)
        }
    }
}

// ── Estrelas de avaliação (1–5) ───────────────────────────────────
@Composable
fun AvaliacaoEstrelas(rating: Int) {
    Row {
        repeat(5) { index ->
            val preenchida = index < rating
            Icon(
                imageVector        = if (preenchida) Icons.Filled.Star else Icons.Outlined.StarOutline,
                contentDescription = if (preenchida) "Estrela preenchida" else "Estrela vazia",
                tint               = if (preenchida) Color(0xFF8B4513) else TextWhite,
                modifier           = Modifier.size(32.dp)
            )
        }
    }
}

// ── Bloco de metadados ────────────────────────────────────────────
@Composable
private fun MetadadosLivro(book: Book) {
    Column(
        modifier            = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        LinhaMetadado(label = "Editora", valor = book.editora)
        LinhaMetadado(label = "Ano de publicação", valor = book.anoPublicacao)
        LinhaMetadado(label = "Idioma", valor = book.idioma)
        LinhaMetadado(label = "Tipo", valor = book.tipo)
        LinhaMetadado(label = "Tamanho", valor = book.tamanho)
        LinhaMetadado(label = "Categoria", valor = book.categoria)
    }
}

// ── Uma linha de metadado: "Label Valor" ──────────────────────────
@Composable
private fun LinhaMetadado(label: String, valor: String) {
    if (valor.isBlank()) return
    Text(
        text       = "$label  $valor",
        color      = TextDark,
        fontSize   = 16.sp,
        lineHeight = 22.sp
    )
}

// ── Botões "comprar" e "alugar" ───────────────────────────────────
@Composable
private fun BotoesAcao(book: Book) {
    Column(
        modifier            = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BotaoAcao(
            texto   = "comprar ${book.price}",
            cor     = CardBrown,
            onClick = {}
        )
        if (book.rentPrice.isNotBlank()) {
            BotaoAcao(
                texto   = "alugar ${book.rentPrice}",
                cor     = MoreButtonBrown,
                onClick = {}
            )
        }
    }
}

// ── Botão pill de ação ────────────────────────────────────────────
@Composable
private fun BotaoAcao(
    texto: String,
    cor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier         = Modifier
            .clip(RoundedCornerShape(50))
            .background(cor)
            .clickable { onClick() }
            .padding(horizontal = 40.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text       = texto,
            color      = TextWhite,
            fontSize   = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Detalhes do Livro",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaDetalhesLivroPreview() {
    TelaDetalhesLivro(
        book = Book(
            title         = "O som vertebrado",
            author        = "Edimilson de Almeida Pereira",
            description   = "Edimilson de Almeida Pereira estreia na José Olympio com livro de poemas " +
                    "dedicado a seu amigo Milton Nascimento, que completa 80 anos. " +
                    "Um ano após vencer.[...]",
            price         = "R\$:10,00",
            coverRes      = R.drawable.o_som,
            rentPrice     = "R\$:5,00",
            editora       = "Editora José Olympio",
            anoPublicacao = "2022",
            idioma        = "Português",
            tipo          = "EPUB",
            tamanho       = "168 pages",
            categoria     = "Poesia",
            rating        = 4
        )
    )
}
