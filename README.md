# API de Gerenciamento de Clientes

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 📝 Descrição

API REST desenvolvida com Spring Boot para gerenciamento de clientes, seguindo o padrão arquitetural MVC. O projeto foi criado como parte do Bootcamp de Arquiteto(a) de Software, demonstrando a aplicação prática de conceitos de arquitetura de software, design patterns e boas práticas de desenvolvimento.

## 🏗️ Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** com as seguintes camadas:

- **Controller**: Gerencia requisições HTTP e respostas REST
- **Service**: Implementa a lógica de negócio
- **Repository**: Interface de acesso aos dados usando Spring Data JPA
- **Model**: Entidades de domínio
- **DTO**: Objetos de transferência de dados
- **Exception Handler**: Tratamento global de exceções

## 🚀 Tecnologias

- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **Spring Web MVC**
- **Spring Validation**
- **PostgreSQL 16**
- **Lombok**
- **Swagger/OpenAPI 3**
- **Docker & Docker Compose**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose
- Git

## 🔧 Instalação e Execução

### 1. Clone o repositório
```bash
git clone https://github.com/Gabriel-Miranda-98/spring-mvc-api.git
cd spring-mvc-api
```

### 2. Inicie o banco de dados
```bash
docker-compose up -d
```

### 3. Execute a aplicação
```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

A documentação interativa da API está disponível através do Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🔌 Endpoints

### Cliente Controller

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| GET | `/api/clientes/buscar?nome={nome}` | Busca clientes por nome |
| GET | `/api/clientes/count` | Retorna o total de clientes |
| POST | `/api/clientes` | Cria um novo cliente |
| PUT | `/api/clientes/{id}` | Atualiza um cliente |
| DELETE | `/api/clientes/{id}` | Remove um cliente |

### Exemplo de Requisição

#### Criar Cliente
```json
POST /api/clientes
Content-Type: application/json

{
  "nome": "João da Silva",
  "email": "joao.silva@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123 - São Paulo, SP"
}
```

## 🐳 Docker

O projeto inclui um `docker-compose.yml` com:

- **PostgreSQL 16**: Banco de dados principal
- **pgAdmin 4**: Interface web para administração do banco

### Credenciais padrão:

**PostgreSQL:**
- Host: localhost:5432
- Database: spring-api-db
- Username: spring
- Password: spring

**pgAdmin:**
- URL: http://localhost:5050
- Email: teste@teste.com
- Password: spring

## 📂 Estrutura do Projeto

```
spring-mvc-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/gabriel/api/spring_mvc_api/
│   │   │       ├── config/         # Configurações (Swagger)
│   │   │       ├── controller/     # Controladores REST
│   │   │       ├── dto/           # Data Transfer Objects
│   │   │       ├── exception/     # Tratamento de exceções
│   │   │       ├── model/         # Entidades JPA
│   │   │       ├── repository/    # Repositórios
│   │   │       └── service/       # Serviços
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── .gitattributes
├── compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## ✅ Validações

O projeto implementa validações robustas usando Bean Validation:

- **Nome**: Obrigatório, entre 2 e 100 caracteres
- **Email**: Obrigatório, formato válido
- **Telefone**: Obrigatório, formato brasileiro (XX) XXXXX-XXXX
- **Endereço**: Opcional, máximo 255 caracteres

## 🔄 Tratamento de Erros

Implementação de tratamento global de exceções com respostas padronizadas:

```json
{
  "errors": {
    "nome": "Nome é obrigatório",
    "email": "Email deve ter um formato válido"
  }
}
```





## 👤 Autor

**Gabriel Miranda**

- GitHub: [@Gabriel-Miranda-98](https://github.com/Gabriel-Miranda-98)
