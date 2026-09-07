const API_URL = "http://localhost:8080";


/*
====================================================
CADASTRAR PRODUTO
====================================================
*/

const produtoForm = document.getElementById("produtoForm");

produtoForm.addEventListener("submit", async function(event) {

    event.preventDefault();


    const nome = document.getElementById("nome").value;

    const preco = document.getElementById("preco").value;

    const quantidade = document.getElementById("quantidade").value;


    const produto = {

        nome: nome,

        preco: Number(preco),

        quantidade: Number(quantidade)

    };


    try {

        const resposta = await fetch(`${API_URL}/produtos`, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(produto)

        });


        if (!resposta.ok) {

            throw new Error("Erro ao cadastrar produto");

        }


        alert("Produto cadastrado com sucesso!");


        produtoForm.reset();


        listarProdutos();


    } catch (erro) {

        console.error(erro);

        alert("Erro ao cadastrar produto.");

    }

});


/*
====================================================
LISTAR PRODUTOS
====================================================
*/

async function listarProdutos() {

    try {

        const resposta = await fetch(`${API_URL}/produtos/lista`);


        if (!resposta.ok) {

            throw new Error("Erro ao buscar produtos");

        }


        const produtos = await resposta.json();


        mostrarProdutos(produtos);


    } catch (erro) {

        console.error(erro);

        mostrarMensagem("Erro ao carregar produtos.");

    }

}


/*
====================================================
MOSTRAR PRODUTOS NA TABELA
====================================================
*/

function mostrarProdutos(produtos) {

    const tabela = document.getElementById("tabelaProdutos");

    tabela.innerHTML = "";


    produtos.forEach(produto => {

        const linha = document.createElement("tr");


        linha.innerHTML = `

            <td>${produto.id}</td>

            <td>${produto.nome}</td>

            <td>R$ ${produto.preco.toFixed(2)}</td>

            <td>${produto.quantidade}</td>

            <td>

                <button
                    class="btn-editar"
                    onclick="editarProduto(${produto.id})">
                    Editar
                </button>

                <button
                    class="btn-excluir"
                    onclick="excluirProduto(${produto.id})">
                    Excluir
                </button>

            </td>

        `;


        tabela.appendChild(linha);

    });

}


/*
====================================================
BUSCAR PRODUTOS
====================================================
*/

async function buscarProdutos() {

    const nome = document
        .getElementById("campoBusca")
        .value;


    if (nome.trim() === "") {

        listarProdutos();

        return;

    }


    try {

        const resposta = await fetch(
            `${API_URL}/produtos/buscar?nome=${encodeURIComponent(nome)}`
        );


        if (!resposta.ok) {

            throw new Error("Erro na busca");

        }


        const produtos = await resposta.json();


        mostrarProdutos(produtos);


    } catch (erro) {

        console.error(erro);

        mostrarMensagem("Erro ao buscar produtos.");

    }

}


/*
====================================================
EXCLUIR PRODUTO
====================================================
*/

async function excluirProduto(id) {

    const confirmar = confirm(
        "Deseja realmente excluir este produto?"
    );


    if (!confirmar) {

        return;

    }


    try {

        const resposta = await fetch(
            `${API_URL}/produtos/${id}`,
            {
                method: "DELETE"
            }
        );


        if (!resposta.ok) {

            throw new Error("Erro ao excluir produto");

        }


        alert("Produto excluído com sucesso!");


        listarProdutos();


    } catch (erro) {

        console.error(erro);

        alert("Erro ao excluir produto.");

    }

}


/*
====================================================
EDITAR PRODUTO
====================================================
*/

async function editarProduto(id) {

    const nome = prompt("Digite o novo nome:");

    if (nome === null) {

        return;

    }


    const preco = prompt("Digite o novo preço:");

    if (preco === null) {

        return;

    }


    const quantidade = prompt("Digite a nova quantidade:");

    if (quantidade === null) {

        return;

    }


    const produto = {

        nome: nome,

        preco: Number(preco.replace(",", ".")),

        quantidade: Number(quantidade)

    };


    try {

        const resposta = await fetch(
            `${API_URL}/produtos/${id}`,
            {

                method: "PUT",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(produto)

            }
        );


        if (!resposta.ok) {

            throw new Error("Erro ao editar produto");

        }


        alert("Produto atualizado com sucesso!");


        listarProdutos();


    } catch (erro) {

        console.error(erro);

        alert("Erro ao atualizar produto.");

    }

}


/*
====================================================
MENSAGEM
====================================================
*/

function mostrarMensagem(texto) {

    const mensagem = document.getElementById("mensagem");

    mensagem.textContent = texto;

}


/*
====================================================
CARREGAR PRODUTOS AO ABRIR A PÁGINA
====================================================
*/

listarProdutos();