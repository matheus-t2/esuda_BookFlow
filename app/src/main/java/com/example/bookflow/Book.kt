package com.example.bookflow

data class Book(
    val title: String,
    val author: String,
    val description: String,
    val price: String,
    val coverRes: Int,
    // ── Campos extras para a tela de detalhes ─────────────────────
    val rentPrice: String    = "",
    val editora: String      = "",
    val anoPublicacao: String = "",
    val idioma: String       = "",
    val tipo: String         = "",
    val tamanho: String      = "",
    val categoria: String    = "",
    val rating: Int          = 0   // 0–5 estrelas
)
