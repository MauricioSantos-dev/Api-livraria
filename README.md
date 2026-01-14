API RESTful desenvolvida em Spring Boot, Spring Security, JPA/Hibernate e H2, fornecendo funcionalidades completas de gerenciamento de livros, carrinho, wishlist e autenticação de usuários, alem de ser estruturada no modelo mvc.

Funcionalidades Principais:

- Autenticação & Usuário

POST /login — Login na plataforma

POST /register — Criar nova conta

GET /user — Retorna o nome do usuário autenticado

- Livros

GET /books — Listar todos os livros

GET /{id}/book — Buscar livro pelo ID

GET /search — Buscar livro pelo nome

GET /category/{category} — Filtrar livros por categoria

- Wishlist:

POST /addwishlist/{bookId} — Adicionar livro à wishlist

GET /wishlist — Exibir wishlist do usuário

- Carrinho

POST /{bookId}/addcartitem — Adicionar livro ao carrinho

GET /shopcart — Exibir carrinho

PUT /{cartItemID}/additem— Aumentar quantidade de um item

PUT /{cartItemID}/removeitem — Diminuir quantidade de um item

PUT /{ShoppingCartId}/removeall — Remover todos os itens do carrinho

Tecnologias Utilizadas:

- Java 21

- Spring Boot

- Spring Web

- Spring Security (com token JWT)

- Spring Data JPA

- Hibernate

- H2

- Lombok

- Maven
