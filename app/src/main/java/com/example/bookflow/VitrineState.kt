package com.example.bookflow

// ── Estado do formulário da vitrine ──────────────────────────────
data class VitrineState(
    val titulo: String          = "",
    val autor: String           = "",
    val descricao: String       = "",
    val preco: String           = "",
    val precoAluguel: String    = "",
    val editora: String         = "",
    val anoPublicacao: String   = "",
    val idioma: String          = "",
    val tipo: String            = "",
    val tamanho: String         = "",
    val categoria: String       = "",
    val fotoCapaUri: String     = "",
    val arquivoLivroUri: String = "",
    val rating: Int             = 0
)

// ── Livro publicado na vitrine (já com ID para exclusão) ──────────
data class LivroVitrine(
    val id: Long,
    val state: VitrineState
)

// ── Resultado da publicação ───────────────────────────────────────
sealed class VitrineResultado {
    object Sucesso : VitrineResultado()
    data class Erro(val mensagem: String) : VitrineResultado()
}

// ── Validação ─────────────────────────────────────────────────────
fun VitrineState.validar(): VitrineResultado {
    return when {
        titulo.isBlank()          -> VitrineResultado.Erro("O título é obrigatório.")
        autor.isBlank()           -> VitrineResultado.Erro("O autor é obrigatório.")
        preco.isBlank()           -> VitrineResultado.Erro("O preço é obrigatório.")
        fotoCapaUri.isBlank()     -> VitrineResultado.Erro("Selecione uma foto de capa.")
        arquivoLivroUri.isBlank() -> VitrineResultado.Erro("Selecione o arquivo do livro.")
        else                      -> VitrineResultado.Sucesso
    }
}

// ── Converte para Book ────────────────────────────────────────────
fun VitrineState.toBook(coverRes: Int = android.R.drawable.ic_menu_gallery): Book =
    Book(
        title         = titulo,
        author        = autor,
        description   = descricao,
        price         = preco,
        coverRes      = coverRes,
        rentPrice     = precoAluguel,
        editora       = editora,
        anoPublicacao = anoPublicacao,
        idioma        = idioma,
        tipo          = tipo,
        tamanho       = tamanho,
        categoria     = categoria,
        rating        = rating
    )
