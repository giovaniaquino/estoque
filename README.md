# Estoque API

Este repositório contém a API de backend para um sistema de gerenciamento de estoque. Desenvolvida com Java e Spring Boot, fornece funcionalidades para gerenciamento de usuários, catalogação de produtos e controle de inventário por meio de lotes de produtos.

## Funcionalidades

*   **Gerenciamento de Usuários**: Cadastrar, atualizar, excluir e listar usuários.
*   **Autenticação**: Endpoints seguros utilizando JWT (JSON Web Tokens) para autenticação de usuários.
*   **Gerenciamento de Produtos**: Criar, atualizar e listar produtos.
*   **Controle de Lotes**: Adicionar e listar lotes para cada produto, incluindo quantidade, data de fabricação e data de validade.
*   **Cálculo Dinâmico de Estoque**: Calcula automaticamente a quantidade total de um produto com base nos seus lotes não vencidos.
*   **Migrações de Banco de Dados**: Utiliza o Flyway para gerenciar e versionar o esquema do banco de dados.
*   **Conteinerização**: Inclui um `Dockerfile` para fácil implantação.

## Tecnologias Utilizadas

*   **Backend**: Java 21, Spring Boot
*   **Banco de Dados**: PostgreSQL
*   **Autenticação**: Spring Security, JWT
*   **ORM**: Spring Data JPA (Hibernate)
*   **Migração de Banco de Dados**: Flyway
*   **Ferramenta de Build**: Maven
*   **Mapeamento de Objetos**: MapStruct
*   **Conteinerização**: Docker

## Primeiros Passos

### Pré-requisitos

*   Java 21
*   Maven
*   PostgreSQL
*   Docker (opcional)

### Instalação e Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/giovaniaquino/estoque.git
    cd estoque
    ```

2.  **Configure as Variáveis de Ambiente:**
    Crie um arquivo `.env` no diretório raiz do projeto copiando o arquivo de exemplo:
    ```bash
    cp .env.example .env
    ```
    Edite o arquivo `.env` com as credenciais do seu banco de dados e um segredo para o JWT:
    ```
    DB_NAME=nome_do_seu_banco
    DB_USERNAME=usuario_do_banco
    DB_PASSWORD=senha_do_banco
    JWT_SECRET=sua_chave_secreta_jwt
    ```

3.  **Configuração do Banco de Dados:**
    Certifique-se de ter um servidor PostgreSQL em execução e crie o banco de dados especificado no seu arquivo `.env`. A aplicação utiliza o Flyway para criar e migrar automaticamente as tabelas necessárias na inicialização.

4.  **Execute a aplicação:**
    Você pode executar a aplicação usando o wrapper do Maven:
    ```bash
    ./mvnw spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

### Executando com Docker

1.  **Construa a imagem Docker:**
    ```bash
    docker build -t estoque-api .
    ```

2.  **Execute o contêiner Docker:**
    Certifique-se de passar as variáveis de ambiente do seu arquivo `.env` para o contêiner.
    ```bash
    docker run -p 8080:8080 \
      -e DB_NAME=nome_do_seu_banco \
      -e DB_USERNAME=usuario_do_banco \
      -e DB_PASSWORD=senha_do_banco \
      -e JWT_SECRET=sua_chave_secreta_jwt \
      --name estoque-api-container \
      estoque-api
    ```
    *Observação: A URL do banco de dados dentro do `application.properties` aponta para `localhost`. Para que o contêiner Docker se conecte a uma instância PostgreSQL na sua máquina host, pode ser necessário ajustar a URL do banco de dados (por exemplo, usando `host.docker.internal` em vez de `localhost`).*

## Endpoints da API

Todos os endpoints autenticados exigem um token `Bearer` no cabeçalho `Authorization`.

### Autenticação (`/auth`)

*   **`POST /auth/registrar`**: Registra um novo usuário.
    *   **Body:**
        ```json
        {
          "nome": "João Silva",
          "email": "joao.silva@exemplo.com",
          "senha": "senha123"
        }
        ```

*   **`POST /auth/login`**: Autentica um usuário e retorna um JWT.
    *   **Body:**
        ```json
        {
          "email": "joao.silva@exemplo.com",
          "senha": "senha123"
        }
        ```
    *   **Resposta:**
        ```json
        {
            "token": "ey..."
        }
        ```

### Usuários (`/usuario`)

*   **`GET /usuario/all`**: Retorna a lista de todos os usuários.
*   **`GET /usuario?nome={nome}`**: Busca usuários pelo nome.
*   **`PATCH /usuario?email={email}`**: Atualiza os dados de um usuário.
    *   **Body:**
        ```json
        {
            "nome": "João S. Silva",
            "email": "joao.s@exemplo.com"
        }
        ```
*   **`DELETE /usuario?email={email}`**: Exclui um usuário.

### Produtos (`/produto`)

*   **`POST /produto`**: Cria um novo produto.
    *   **Body:**
        ```json
        {
          "codigo": "PROD-001",
          "nome": "Produto Exemplo"
        }
        ```

*   **`GET /produto/all`**: Retorna a lista de todos os produtos, incluindo a quantidade total proveniente dos lotes não vencidos.
*   **`GET /produto?nome={nome}`**: Busca produtos pelo nome.
*   **`PATCH /produto?codigo={codigo}`**: Atualiza os dados de um produto.
    *   **Body:**
        ```json
        {
            "nome": "Produto Exemplo Atualizado"
        }
        ```

### Lotes (`/lotes`)

*   **`POST /lotes`**: Cria um novo lote para um produto existente.
    *   **Body:**
        ```json
        {
            "codigoProduto": "PROD-001",
            "lote": "LOTE2024A",
            "quantidade": 100,
            "fabricacao": "2024-01-01",
            "validade": "2025-01-01"
        }
        ```
*   **`GET /lotes/lista?codigoProduto={codigoProduto}`**: Retorna a lista de todos os lotes de um produto específico.
