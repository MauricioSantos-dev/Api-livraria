API RESTful desenvolvida em Spring Boot, Spring Security, JPA/Hibernate e H2, fornecendo funcionalidades completas de gerenciamento de livros, carrinho, wishlist e autenticação de usuários, alem de ser estruturada no modelo mvc.

Funcionalidades Principais:

- Autenticação & Usuário

POST /login — Login na plataforma

POST /cadastro — Criar nova conta

GET /usuario/nome — Retorna o nome do usuário autenticado

- Livros

GET /livros — Listar todos os livros

GET /livros/{id} — Buscar livro pelo ID

GET /livros/nome/{nome} — Buscar livro pelo nome

GET /livros/categoria/{categoria} — Filtrar livros por categoria

- Wishlist:

POST /wishlist/adicionar — Adicionar livro à wishlist

GET /wishlist — Exibir wishlist do usuário

- Carrinho

POST /carrinho/adicionar — Adicionar livro ao carrinho

GET /carrinho — Exibir carrinho

PUT /carrinho/adicionar-item — Aumentar quantidade de um item

PUT /carrinho/remover-item — Diminuir quantidade de um item

PUT /carrinho/remover-tudo — Remover todos os itens do carrinho

- Tecnologias Utilizadas:

- Java 21

- Spring Boot

- Spring Web

- Spring Security (com token JWT)

- Spring Data JPA

-Hibernate

-H2

-Lombok

-Maven
