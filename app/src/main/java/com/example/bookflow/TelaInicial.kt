package com.example.bookflow

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ── Tela principal — lista simples de livros (SEM vitrine) ────────
@Composable
fun TelaInicial() {
    var livroSelecionado by remember { mutableStateOf<Book?>(null) }

    if (livroSelecionado != null) {
        TelaDetalhesLivro(
            book     = livroSelecionado!!,
            onVoltar = { livroSelecionado = null }
        )
    } else {
        fundoTela {
            TopAppBar()

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            LazyColumn(
                modifier            = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding      = PaddingValues(vertical = 12.dp)
            ) {
                items(sampleBooks) { book ->
                    BookCard(
                        book    = book,
                        onClick = { livroSelecionado = book }
                    )
                }
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Tela Principal",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaInicialPreview() {
    TelaInicial()
}