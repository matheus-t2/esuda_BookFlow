package com.example.bookflow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        title       = "O Pequeno Principe",
        author      = "Jacques Ribemboim",
        description = "Nesta história atemporal, conheça o piloto estagnado no deserto e um pequeno " +
                "príncipe que cuida da amada rosa que vive em seu planeta. Siga o menininho [...]",
        price       = "R$:35,00",
        coverRes    = R.drawable.principe
    ),
    Book(
        title       = "O Senhor Dos Aneis",
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
        // ── Header (AppBar.kt) ───────────────────────────────────
        TopAppBar()

        // ── Barra de pesquisa ────────────────────────────────────
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        // ── Lista de livros (BookCard.kt) ────────────────────────
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

// ── Barra de pesquisa ─────────────────────────────────────────────
@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
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
