package com.example.bookflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ═══════════════════════════════════════════════════════════════════
// AppBar com ícone de pasta (abre vitrine)
// ═══════════════════════════════════════════════════════════════════
@Composable
fun TopAppBarVitrine(
    onVitrineClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.Menu,
            contentDescription = "Menu",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
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

        // Ícone de pasta (direita) → abre vitrine
        Icon(
            imageVector        = Icons.Default.Folder,
            contentDescription = "Minha Vitrine",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterEnd)
                .size(28.dp)
                .clickable { onVitrineClick() }
        )
    }
}

// ═══════════════════════════════════════════════════════════════════
// Tela da Vitrine — lista livros publicados + botão para adicionar
// ═══════════════════════════════════════════════════════════════════
@Composable
fun TelaVitrine(
    livros: List<LivroVitrine>,
    onExcluir: (Long) -> Unit,
    onAdicionarClick: () -> Unit,
    onVoltar: () -> Unit
) {
    fundoTela {
        VitrineAppBar(onAdicionarClick = onAdicionarClick, onVoltar = onVoltar)

        if (livros.isEmpty()) {
            // ── Estado vazio ─────────────────────────────────────
            Box(
                modifier         = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text     = "Sua vitrine está vazia",
                        color    = TextDark.copy(alpha = 0.6f),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(CardBrown)
                            .clickable { onAdicionarClick() }
                            .padding(horizontal = 28.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text       = "Publicar livro",
                            color      = TextWhite,
                            fontSize   = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        } else {
            // ── Lista de livros publicados ────────────────────────
            LazyColumn(
                modifier            = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding      = PaddingValues(vertical = 12.dp)
            ) {
                items(livros, key = { it.id }) { livro ->
                    VitrineCardPublicado(
                        livro     = livro,
                        onExcluir = onExcluir
                    )
                }
            }
        }
    }
}

// ── AppBar interna da vitrine ─────────────────────────────────────
@Composable
private fun VitrineAppBar(
    onAdicionarClick: () -> Unit,
    onVoltar: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Ícone pasta (esquerda) → volta
        Icon(
            imageVector        = Icons.Default.Folder,
            contentDescription = "Voltar",
            tint               = TextWhite,
            modifier           = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
                .clickable { onVoltar() }
        )

        // Centro: logo
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

        // Direita: "+" para adicionar novo livro
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(50))
                .background(MoreButtonBrown)
                .clickable { onAdicionarClick() }
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("+", color = TextWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// ═══════════════════════════════════════════════════════════════════
// Tela de formulário: Publicar livro na Vitrine
// ═══════════════════════════════════════════════════════════════════
@Composable
fun TelaPublicarNaVitrine(
    onCancelar: () -> Unit = {},
    onPublicar: (VitrineState) -> Unit = {}
) {
    var state        by remember { mutableStateOf(VitrineState()) }
    var erroMensagem by remember { mutableStateOf<String?>(null) }

    fundoTela {
        // Diálogo de erro sobreposto ao conteúdo
        if (erroMensagem != null) {
            AlertDialog(
                onDismissRequest = { erroMensagem = null },
                title            = { Text("Atenção", color = TextDark, fontWeight = FontWeight.Bold) },
                text             = { Text(erroMensagem!!, color = TextDark) },
                confirmButton    = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(CardBrown)
                            .clickable { erroMensagem = null }
                            .padding(horizontal = 24.dp, vertical = 10.dp)
                    ) {
                        Text("OK", color = TextWhite, fontWeight = FontWeight.SemiBold)
                    }
                },
                containerColor = Color(0xFFF2EAD8)
            )
        }

        FormularioVitrineAppBar(onVoltar = onCancelar)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Preview em tempo real
            VitrinePreviewCard(state = state)

            // Seletores de mídia
            VitrineSeletorFotoCapa(
                fotoUri           = state.fotoCapaUri,
                onFotoSelecionada = { state = state.copy(fotoCapaUri = it) }
            )

            VitrineSeletorArquivo(
                arquivoUri           = state.arquivoLivroUri,
                onArquivoSelecionado = { state = state.copy(arquivoLivroUri = it) }
            )

            // Campos de texto
            VitrineCampoTexto(state.titulo,        { state = state.copy(titulo = it) },        "Título do livro *")
            VitrineCampoTexto(state.autor,         { state = state.copy(autor = it) },         "Autor *")
            VitrineCampoTexto(state.descricao,     { state = state.copy(descricao = it) },     "Descrição", singleLine = false)
            VitrineCampoTexto(state.preco,         { state = state.copy(preco = it) },         "Preço (ex: R\$:10,00) *")
            VitrineCampoTexto(state.precoAluguel,  { state = state.copy(precoAluguel = it) },  "Preço de aluguel (ex: R\$:5,00)")
            VitrineCampoTexto(state.editora,       { state = state.copy(editora = it) },       "Editora")
            VitrineCampoTexto(state.anoPublicacao, { state = state.copy(anoPublicacao = it) }, "Ano de publicação")
            VitrineCampoTexto(state.idioma,        { state = state.copy(idioma = it) },        "Idioma")
            VitrineCampoTexto(state.tipo,          { state = state.copy(tipo = it) },          "Tipo (ex: EPUB, PDF)")
            VitrineCampoTexto(state.tamanho,       { state = state.copy(tamanho = it) },       "Tamanho (ex: 200 pages)")
            VitrineCampoTexto(state.categoria,     { state = state.copy(categoria = it) },     "Categoria")

            // Rating
            VitrineSeletorRating(
                rating         = state.rating,
                onRatingChange = { state = state.copy(rating = it) }
            )

            // Botões cancelar / publicar
            VitrineBotoes(
                onCancelar = onCancelar,
                onPublicar = {
                    when (val resultado = state.validar()) {
                        is VitrineResultado.Sucesso -> onPublicar(state)
                        is VitrineResultado.Erro    -> erroMensagem = resultado.mensagem
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── AppBar do formulário de publicação ────────────────────────────
@Composable
private fun FormularioVitrineAppBar(onVoltar: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBrown)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.Folder,
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

// ── Previews ──────────────────────────────────────────────────────
@Preview(
    name            = "Vitrine - Formulário",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaPublicarVitrinePreview() {
    TelaPublicarNaVitrine()
}

@Preview(
    name            = "Vitrine - Lista publicados",
    showBackground  = true,
    backgroundColor = 0xFFF2EAD8,
    widthDp         = 380,
    heightDp        = 820
)
@Composable
fun TelaVitrineListaPreview() {
    TelaVitrine(
        livros = listOf(
            LivroVitrine(
                id = 1L,
                state = VitrineState(
                    titulo        = "História dos judeus de Pernambuco",
                    autor         = "Jacques Ribemboim",
                    anoPublicacao = "2022",
                    rating        = 3
                )
            )
        ),
        onExcluir        = {},
        onAdicionarClick = {},
        onVoltar         = {}
    )
}