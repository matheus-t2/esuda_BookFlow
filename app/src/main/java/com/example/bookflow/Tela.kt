package com.example.bookflow

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


// ── Dados de exemplo ──────────────────────────────────────────────
private val sampleBooks = listOf(
    Book(
        title       = "O som vertebrado",
        author      = "Edimilson de Almeida Pereira",
        description = "Edimilson de Almeida Pereira estreia na José Olympio com livro de poemas dedicado a" +
                " seu amigo Milton Nascimento, que completa 80 anos. Um ano após vencer.[...]",
        price       = "R$:10,00",
        coverRes    = R.drawable.o_som
    ),
    Book(
        title       = "História dos judeus de Pernambuco",
        author      = "Jacques Ribemboim",
        description = "Neste livro, resultado de uma extensa pesquisa, o escritor e economista Jacques Ribemboim[...]",
        price       = "R$:15,00",
        coverRes    = R.drawable.judeus
    ),
    Book(
        title     = "O Pequeno Principe",
        author      = "Jacques Ribemboim",
        description = "Nesta história atemporal, conheça o piloto estagnado no deserto e um pequeno " +
                "príncipe que cuida da amada rosa que vive em seu planeta. Siga o menininho [...]",
        price       = "R$:35,00",
        coverRes    = R.drawable.principe
    ),
    Book(
        title     = "O Senhor Dos Aneis",
        author      = "J.R.R Tokien ",
        description = "O volume inicial de O Senhor dos Anéis, lançado originalmente em julho de 1954," +
                " foi o primeiro grande épico de fantasia moderno, conquistando milhões de leitores" +
                " e se tornando o padrão de referência para todas as outras obras do gênero até hoje." +
                " A imaginação prodigiosa de J.R.R. Tolkien e seu conhecimento profundo das antigas " +
                "mitologias da Europa permitiram que ele criasse um universo tão complexo e convincente " +
                "quanto o mundo real.",
        price       = "R$:85,00",
        coverRes    = R.drawable.anel
    )
)

// ── Tela principal ────────────────────────────────────────────────
@Composable
fun Tela() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        // ── Header ───────────────────────────────────────────────
        TopAppBar()

        // ── Barra de pesquisa ────────────────────────────────────
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        // ── Lista de livros com scrollbar customizada ────────────
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(sampleBooks) { book ->
                BookCard(book = book)
            }
        }

        }
}

// ── Header ────────────────────────────────────────────────────────
@Composable
private fun TopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Menu
        Icon(
            imageVector        = Icons.Default.Menu,
            contentDescription = "Menu",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
        )

        // Logo "BookNow" centralizado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier            = Modifier.align(Alignment.Center)
        ) {
            // Ícone de livro simulado com texto estilizado
            Box(
                modifier            = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF5C3A1E)),
                contentAlignment    = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "log",
                    modifier = Modifier.size(120.dp)
                )

            }
            Text(
                text       = "BookFlow",
                color      = TextWhite,
                fontSize   = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

// ── Barra de pesquisa ─────────────────────────────────────────────
@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(SearchBarColor)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {                                         // ← abre chave aqui
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier          = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value         = query,
                onValueChange = { query = it },
                modifier      = Modifier.weight(1f),
                singleLine    = true,
                textStyle     = androidx.compose.ui.text.TextStyle(
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
    }                                           // ← fecha o Box aqui
}


// ── Card de livro ─────────────────────────────────────────────────
@Composable
private fun BookCard(book: Book) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBrown)
            .padding(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Capa do livro (placeholder colorido)
            Box(
                modifier         = Modifier
                    .size(width = 90.dp, height = 130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF3B3060)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = book.coverRes),
                    contentDescription = book.title,
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Informações do livro
            Column(
                modifier = Modifier.weight(1f)
            ) {
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
                    text     = book.description,
                    color    = Color(0xFFF5ECD6),
                    fontSize = 13.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Botões de preço e "mais"
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Preço
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

                    // Botão "mais"
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

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookNow - Tela Principal",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun BookNowScreenPreview() {
    Tela()
}

