const API_URL = "http://localhost:8080";


const cadastroForm =
    document.getElementById("cadastroForm");


cadastroForm.addEventListener("submit", async function(event) {

    event.preventDefault();


    const login =
        document.getElementById("login").value;


    const senha =
        document.getElementById("senha").value;


    const confirmarSenha =
        document.getElementById("confirmarSenha").value;


    if (senha !== confirmarSenha) {

        document.getElementById("mensagem").textContent =
            "As senhas não são iguais.";

        return;

    }


    const usuario = {

        login: login,

        senha: senha

    };


    try {

        const resposta = await fetch(
            `${API_URL}/login/usuarios`,
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(usuario)

            }
        );


        const mensagem = await resposta.text();


        if (resposta.ok) {

            alert(mensagem);

            window.location.href = "login.html";

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