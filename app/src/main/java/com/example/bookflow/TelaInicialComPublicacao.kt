package com.example.bookflow

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ── Tela principal COM vitrine (publicação de livros) ─────────────
@Composable
fun TelaInicialComPublicacao() {
    var livroSelecionado    by remember { mutableStateOf<Book?>(null) }
    var mostrandoVitrine    by remember { mutableStateOf(false) }
    var mostrandoFormulario by remember { mutableStateOf(false) }
    var livrosVitrine       by remember { mutableStateOf<List<LivroVitrine>>(emptyList()) }
    var proximoId           by remember { mutableStateOf(1L) }

    when {
        // ── Formulário de publicação ─────────────────────────────
        mostrandoFormulario -> {
            TelaPublicarNaVitrine(
                onCancelar = { mostrandoFormulario = false },
                onPublicar = { state ->
                    val novo = LivroVitrine(id = proximoId++, state = state)
                    livrosVitrine       = livrosVitrine + novo
                    mostrandoFormulario = false
                    mostrandoVitrine    = true
                }
            )
        }

        // ── Vitrine (lista de livros publicados) ─────────────────
        mostrandoVitrine -> {
            TelaVitrine(
                livros           = livrosVitrine,
                onExcluir        = { id -> livrosVitrine = livrosVitrine.filter { it.id != id } },
                onAdicionarClick = { mostrandoFormulario = true },
                onVoltar         = { mostrandoVitrine = false }
            )
        }

        // ── Detalhes de livro ────────────────────────────────────
        livroSelecionado != null -> {
            TelaDetalhesLivro(
                book     = livroSelecionado!!,
                onVoltar = { livroSelecionado = null }
            )
        }

        // ── Lista geral de livros ────────────────────────────────
        else -> {
            fundoTela {
                TopAppBarVitrine(
                    onVitrineClick = { mostrandoVitrine = true }
                )
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
}

// ── Preview ───────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Tela com Publicação",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaInicialComPublicacaoPreview() {
    TelaInicialComPublicacao()
}
