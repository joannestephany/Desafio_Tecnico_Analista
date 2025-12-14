# Frontend - Gestão de Férias

Frontend desenvolvido em **React** com **Vite** para o Sistema de Gestão de Férias.

## 🚀 Como Rodar

### Pré-requisitos
- **Node.js** 18+ instalado
- **Backend** rodando em `http://localhost:8080`

⚠️ Ou seja, seguir a ordem de execução do README do backend primeiro

### Instalação e Execução

```bash
# 1. Acesse a pasta do frontend
cd frontend

# 2. Instale as dependências
npm install

# 3. Inicie o servidor de desenvolvimento
npm run dev
```

O frontend estará disponível em: **http://localhost:3000**

⚠️  O backend precisa estar rodando para as chamadas de API funcionarem.

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| React | 19 | Biblioteca UI |
| Vite | 7 | Build tool |
| JavaScript | ES6+ | Linguagem |
| CSS3 | - | Estilização |

## 📁 Estrutura do Projeto

```
frontend/
├── src/
│   ├── components/           # Componentes React
│   │   ├── Login.jsx         # Tela de login
│   │   ├── Dashboard.jsx     # Dashboard principal
│   │   ├── VacationTable.jsx # Tabela de férias
│   │   ├── DetailsModal.jsx  # Modal de detalhes
│   │   └── NewVacationModal.jsx # Modal nova solicitação
│   ├── services/
│   │   └── api.js            # Chamadas à API
│   ├── App.jsx               # Componente raiz
│   ├── App.css               # Estilos globais
│   └── main.jsx              # Entry point
├── vite.config.js            # Configuração Vite
└── package.json              # Dependências
```

## ⚙️ Configuração

O Vite está configurado para:
- **Proxy**: Requisições `/api` redirecionadas para `http://localhost:8080`
- **Build**: Saída para `../gestaoferiasAPI/src/main/resources/static`

## 📦 Comandos Disponíveis

| Comando | Descrição |
|---------|-----------|
| `npm install` | Instala as dependências |
| `npm run dev` | Inicia servidor de desenvolvimento (porta 3000) |
| `npm run build` | Gera build de produção |
| `npm run preview` | Visualiza build de produção |

## 🧩 Componentes

| Componente | Descrição |
|------------|-----------|
| `Login` | Autenticação por matrícula |
| `Dashboard` | Estatísticas e lista de férias |
| `VacationTable` | Tabela com ações (ver/deletar) |
| `DetailsModal` | Detalhes e aprovação/negação |
| `NewVacationModal` | Criar nova solicitação |

## 🔗 API

O módulo `services/api.js` faz as seguintes conecxões:
- `servidoresApi` - Operações de servidores
- `feriasApi` - Operações de férias
- `statusApi` - Listagem de status
