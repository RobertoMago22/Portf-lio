const API_URL = "http://localhost:8080";


const loginForm = document.getElementById("loginForm");


loginForm.addEventListener("submit", async function(event) {

    event.preventDefault();


    const login = document.getElementById("login").value;

    const senha = document.getElementById("senha").value;


    const usuario = {

        login: login,

        senha: senha

    };


    try {

        const resposta = await fetch(`${API_URL}/login`, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(usuario)

        });


        const mensagem = await resposta.text();


        if (resposta.ok) {

            alert(mensagem);

            window.location.href = "index.html";

        } else {

            document.getElementById("mensagem").textContent =
                mensagem;

        }


    } catch (erro) {

        console.error(erro);

        document.getElementById("mensagem").textContent =
            "Erro ao conectar com o servidor.";

    }

});