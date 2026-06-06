package com.example.bookflow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row

// ── Dados de exemplo ──────────────────────────────────────────────
val sampleBooks = listOf(
    Book(
        title         = "O som vertebrado",
        author        = "Edimilson de Almeida Pereira",
        description   = "Edimilson de Almeida Pereira estreia na José Olympio com livro de poemas " +
                "dedicado a seu amigo Milton Nascimento, que completa 80 anos. Um ano após vencer.[...]",
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
    ),
    Book(
        title         = "História dos judeus de Pernambuco",
        author        = "Jacques Ribemboim",
        description   = "Neste livro, resultado de uma extensa pesquisa, o escritor e economista Jacques Ribemboim[...]",
        price         = "R\$:15,00",
        coverRes      = R.drawable.judeus,
        rentPrice     = "R\$:7,00",
        editora       = "Cepe Editora",
        anoPublicacao = "2019",
        idioma        = "Português",
        tipo          = "PDF",
        tamanho       = "224 pages",
        categoria     = "História",
        rating        = 5
    ),
    Book(
        title         = "O Pequeno Príncipe",
        author        = "Antoine de Saint-Exupéry",
        description   = "Nesta história atemporal, conheça o piloto estagnado no deserto e um pequeno " +
                "príncipe que cuida da amada rosa que vive em seu planeta.[...]",
        price         = "R\$:35,00",
        coverRes      = R.drawable.principe,
        rentPrice     = "R\$:10,00",
        editora       = "Agir",
        anoPublicacao = "2015",
        idioma        = "Português",
        tipo          = "EPUB",
        tamanho       = "96 pages",
        categoria     = "Literatura",
        rating        = 5
    ),
    Book(
        title         = "O Senhor Dos Anéis",
        author        = "J.R.R Tolkien",
        description   = "O volume inicial de O Senhor dos Anéis, lançado originalmente em julho de 1954, " +
                "foi o primeiro grande épico de fantasia moderno, conquistando milhões de leitores.[...]",
        price         = "R\$:85,00",
        coverRes      = R.drawable.anel,
        rentPrice     = "R\$:20,00",
        editora       = "HarperCollins",
        anoPublicacao = "2001",
        idioma        = "Português",
        tipo          = "EPUB",
        tamanho       = "576 pages",
        categoria     = "Fantasia",
        rating        = 5
    )
)

// ── Tela principal SEM vitrine (lista simples) ────────────────────
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

// ── Barra de pesquisa ─────────────────────────────────────────────
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
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
                textStyle     = TextStyle(
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

// ── Previews ──────────────────────────────────────────────────────
@Preview(
    name            = "BookFlow - Tela Principal",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun BookNowScreenPreview() {
    TelaInicial()
}

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