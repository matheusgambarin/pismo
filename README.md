Para executar a aplicação existem 2 formas

# Script

Executar o script na pasta raiz build-and-start-app-script.sh, onde o mesmo gera um jar do projeto builda uma imagem docker e em seguida sobe o container docker podendo ser testado no localhost:8090.

# Docker compose

docker-compose pull

docker-compose up

A aplicação estara exposta na porta 8090 para ser acessada.

# Documentação API

Toda a documentação da api e seus respectivos endpoints encontram-se no swagger. Ao levantar a aplicação acessar interface do swagger disponibilizada no endereço localhost:8090/swagger-ui.html.
