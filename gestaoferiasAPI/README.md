# Backend - Gestão de Férias API

Backend desenvolvido em **Java 17** com **Spring Boot 3** para o Sistema de Gestão de Férias.

## 🚀 Como Rodar

### Pré-requisitos
- **Java 17** instalado
- **Maven** instalado (ou use o wrapper `mvnw`)
- **MySQL 8.0** ou **Docker**

### Opção 1: Docker (Recomendado)

```bash
# 1. Acesse a pasta do backend
cd gestaoferiasAPI

# 2. Execute com Docker Compose
docker-compose up --build
```

Isso iniciará:
- 📦 MySQL na porta `3306`
- 🚀 API na porta `8080`

### Opção 2: Maven (Desenvolvimento)

```bash
# 1. Acesse a pasta do backend
cd gestaoferiasAPI

# 2. Configure o MySQL local (variáveis de ambiente)
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_USER=root
export MYSQL_PASSWORD=sua_senha

# 3. Execute a aplicação
./mvnw clean spring-boot:run
```

A API estará disponível em: **http://localhost:8080**

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.8 | Framework web |
| Spring Data JPA | - | ORM/Persistência |
| MySQL | 8.0 | Banco de dados |
| Lombok | - | Redução de boilerplate |
| Docker | - | Containerização |

## 📁 Arquitetura

O backend segue o padrão de arquitetura em camadas:
- **Controller** - Endpoints REST
- **Service** - Lógica de negócio
- **Repository** - Acesso a dados
- **Entity** - Mapeamento de tabelas

## 🗄️ Banco de Dados

As tabelas principais são:

| Tabela | Descrição |
|--------|-----------|
| `servidor` | Dados dos funcionários (Nome, Matrícula) |
| `periodo_ferias` | Intervalos de férias (vinculado a servidor e status) |
| `status_solicitacao` | Domínio de status (Solicitado, Aprovado, Concluído, Negado) |
| `pagamento` | Informações financeiras (Valor Bruto, Líquido) |


( O status poderia ser ENUM, mas nesse projeto foi criado como tabela para maior flexibilidade)

( A ausencia de tabela de usuarios é devido a não obrigatoriedade do desafio)

### Configuração
- Arquivo: `src/main/resources/application.yml`
- Inicialização: `data.sql` popula o banco automaticamente  (poucos dados para testes)

## 📡 API Endpoints

### Servidores
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/servidores` | Lista todos |
| GET | `/api/servidores/{id}` | Busca por ID |
| GET | `/api/servidores/matricula/{matricula}` | Busca por matrícula |
| GET | `/api/servidores/{id}/ferias` | Lista férias do servidor |
| POST | `/api/servidores` | Cadastro |
| PUT | `/api/servidores/{id}` | Atualização |
| DELETE | `/api/servidores/{id}` | Remoção |

### Férias
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/ferias` | Lista todas |
| GET | `/api/ferias/{id}` | Detalhes (inclui pagamento) |
| GET | `/api/ferias?status={status}` | Filtra por status |
| POST | `/api/ferias` | Nova solicitação |
| PUT | `/api/ferias/{id}` | Atualizar status/datas |
| DELETE | `/api/ferias/{id}` | Cancelar solicitação |

### Pagamentos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/pagamentos` | Listagem geral |
| GET | `/api/pagamentos/periodo/{id}` | Busca por período |

## 🧪 Dados de Teste

O arquivo `data.sql` inclui dados de exemplo:
- **Matrícula**: `123456` (João da Silva)
- **Matrícula**: `654321` (Maria da Graça)

Ambos podem ser acessados pelo frontend, sem necessidade de senha, apenas matricula.
