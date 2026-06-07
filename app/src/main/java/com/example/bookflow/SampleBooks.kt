package com.example.bookflow

// ── Dados de exemplo compartilhados entre telas ───────────────────
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
