# Api-livraria

API RESTful para uma livraria, desenvolvida em **Java 21** com **Spring Boot**, oferecendo recursos de **autenticação com JWT**, **catálogo de livros**, **wishlist** e **carrinho de compras**.

---

## Descrição do projeto
Foi desenvolvida uma **API RESTful** utilizando **Spring Boot (Web, Security, Validation)** e **Spring Data JPA/Hibernate**, com **banco H2 em memória** para persistência e um conjunto de endpoints para:
- autenticação e cadastro de usuários;
- listagem e busca de livros;
- gerenciamento de wishlist do usuário autenticado;
- gerenciamento de carrinho de compras do usuário autenticado.

---

## Tecnologias
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web**
- **Spring Security**
- **JWT** (biblioteca `com.auth0:java-jwt`)
- **Spring Data JPA / Hibernate**
- **H2 Database (em memória)**
- **Lombok**
- **Maven**

---

## Estrutura do projeto

### Raiz do repositório
- `README.md` — documentação do projeto
- `livraria-api/` — aplicação Spring Boot

### Dentro de `livraria-api/`
- `pom.xml` — dependências e build (Maven)
- `mvnw` / `mvnw.cmd` — Maven Wrapper
- `.mvn/` — arquivos do Maven Wrapper
- `src/main/java/...` — código-fonte da aplicação
- `src/main/resources/application.properties` — configurações da aplicação
- `src/main/resources/import.sql` — carga inicial de dados (seed)
- `src/test/` — testes

---

## Como executar o projeto

### Pré-requisitos
- Java **21+**

### Executar com Maven Wrapper (recomendado)
No diretório `livraria-api/`:

```bash
./mvnw spring-boot:run
```

No Windows:
```bash
mvnw.cmd spring-boot:run
```

A aplicação inicia com o nome:
- `spring.application.name=livraria-api`

---

## Configurações principais (H2 + JWT)

Arquivo: `livraria-api/src/main/resources/application.properties`

- Banco H2 em memória:
  - `spring.datasource.url=jdbc:h2:mem:livraria`
  - `spring.datasource.username=sa`
  - `spring.datasource.password=` (vazio)

- Console do H2 habilitado:
  - `spring.h2.console.enabled=true`
  - `spring.h2.console.path=/h2-console`

- Token JWT:
  - `api.security.token.secret=${JWT_SECRET:my-secret-key}`

Ou seja: se a variável de ambiente `JWT_SECRET` não estiver configurada, será usado `my-secret-key`.

---

## Classe principal (Spring Boot)
Arquivo:
- `livraria-api/src/main/java/com/uninassau/livraria_api/LivrariaApiApplication.java`

Responsável por iniciar a aplicação via `SpringApplication.run(...)`.

---

## Endpoints (principais)

### Autenticação (prefixo `/auth`)
Controller: `AuthController`

- `POST /auth/login` — login e retorno do token JWT
- `POST /auth/register` — criação de conta

### Usuário / Wishlist
Controller: `UserController`

- `GET /user` — retorna o nome do usuário autenticado
- `POST /addwishlist/{bookId}` — adiciona livro na wishlist
- `GET /wishlist` — lista wishlist do usuário

### Livros
Controller: `BookController`

- `GET /books` — lista todos os livros
- `GET /{id}/book` — busca livro por ID
- `GET /search?title=...` — busca por título
- `GET /category/{category}` — filtra por categoria

### Carrinho
Controller: `ShoppingCartController`

- `POST /{bookId}/addcartitem` — adiciona livro ao carrinho (com body `CartItemRequestDTO`)
- `GET /shopcart` — exibe carrinho do usuário autenticado
- `PUT /{cartItemID}/additem` — aumenta quantidade do item
- `PUT /{cartItemID}/removeitem` — diminui quantidade do item
- `PUT /{ShoppingCartId}/removeall` — remove todos os itens do carrinho

> Observação: alguns endpoints dependem de autenticação (uso de `Principal` para recuperar o usuário via e-mail).

---

## Observações sobre o projeto
- O banco é **H2 em memória**, então os dados são recriados a cada reinicialização.
- Existe um `import.sql` em `src/main/resources/` para popular dados iniciais automaticamente.
