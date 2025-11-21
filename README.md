# API Central do Corretor
Uma API RESTful robusta desenvolvida para auxiliar corretores de imóveis na gestão de seus cadastros de imóveis, clientes e vendas. Construída com Spring Boot e foco em segurança via JWT.

## Funcionalidades Principais
O sistema oferece um conjunto completo de operações CRUD (Create, Read, Update, Delete) para as entidades principais: Usuários, Imóveis, Clientes e Vendas.

👥 Gestão de Usuários (Autenticação)

-Autocadastro de novos usuários.<br>
-Login para obtenção do token de acesso (JWT).<br>

🏡 Gestão de Imóveis

-Cadastrar um novo imóvel.<br>
-Buscar todos os imóveis cadastrados pelo usuário.<br>
-Buscar imóvel específico por nome e usuário.<br>
-Atualizar dados de um imóvel existente.<br>
-Deletar um imóvel.<br>

🧑‍💼 Gestão de Clientes

-Cadastrar um novo cliente.<br>
-Buscar todos os clientes cadastrados pelo usuário.<br>
-Buscar cliente específico por nome e usuário.<br>
-Deletar um cliente.<br>

💰 Gestão de Vendas

-Cadastrar uma nova venda (vinculando cliente e imóvel).<br>
-Buscar todas as vendas realizadas pelo usuário.<br>
-Deletar um registro de venda.<br>

## Tecnologias utilizadas
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Categoria</th>
            <th>Tecnologia</th>
            <th>Versão/Plataforma</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Framework</td>
            <td>Spring Boot</td>
            <td>Java</td>
        </tr>
        <tr>
            <td>Segurança</td>
            <td>JSON Web Token (JWT)</td>
            <td>Padrão</td>
        </tr>
        <tr>
            <td>Banco de Dados</td>
            <td>PostgreSQL</td>
            <td>v.18</td>
        </tr>
        <tr>
            <td>Containerização</td>
            <td>Docker</td>
            <td>Padrão</td>
        </tr>
        <tr>
            <td>Deploy (PaaS)</td>
            <td>Koyeb</td>
            <td>Nuvem</td>
        </tr>
        <tr>
            <td>DB em Nuvem</td>
            <td>Supabase</td>
            <td>Nuvem</td>
        </tr>
    </tbody>
</table>

## Segurança aplicada
A segurança é implementada utilizando JSON Web Tokens (JWT) para autenticação, seguindo o padrão Stateless (sem sessão), ideal para APIs REST:

Autenticação via Token (Bearer): O acesso aos endpoints protegidos (/ws/**) exige um token JWT válido no cabeçalho Authorization.

CORS/OPTIONS: Requisições HttpMethod.OPTIONS são liberadas para garantir a compatibilidade com diferentes front-ends.

## Conexão com Banco de Dados
O banco de dados PostgreSQL é hospedado na nuvem (Supabase) e a conexão é estabelecida no Spring Boot por meio de variáveis de ambiente (Environment Keys), garantindo que as credenciais do banco de dados não fiquem expostas no código.

## Principais Dependências
A aplicação utiliza as seguintes dependências principais do ecossistema Spring:<br>
-spring-boot-web: Para criar serviços web RESTful.<br>
-spring-boot-jpa: Para persistência de dados (ORM com Hibernate).<br>
-spring-boot-security: Para controle de acesso e autenticação.<br>
-postgresql: Driver de conexão com o banco de dados.<br>
-jjwt-api, jjwt-imp, jjwt-jackson: Implementação do JWT para criação e validação de tokens.<br>

### Autenticação (Endpoints Abertos)
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Método</th>
            <th>Endpoint</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>POST</td>
            <td>/auth/signup</td>
            <td>Autocadastro de usuário.</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/auth/signin</td>
            <td>Login e obtenção do JWT.</td>
        </tr>
    </tbody>
</table>

### Imóveis (Endpoints Protegidos: Requer Authorization: Bearer ${token})
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Método</th>
            <th>Endpoint</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>POST</td>
            <td>/ws/imovel</td>
            <td>Cadastrar novo imóvel.</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/ws/imovel</td>
            <td>Buscar todos os imóveis do usuário.</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/ws/imovel?nome={NomeImovel}</td>
            <td>Buscar imóvel por nome e usuário.</td>
        </tr>
        <tr>
            <td>PUT/UPDATE</td>
            <td>/ws/imovel</td>
            <td>Atualizar dados de um imóvel.</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/ws/imovel/{idImovel}</td>
            <td>Deletar imóvel por ID.</td>
        </tr>
    </tbody>
</table>

### Clientes (Endpoints Protegidos: Requer Authorization: Bearer ${token})
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Método</th>
            <th>Endpoint</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>POST</td>
            <td>/ws/clientes</td>
            <td>Cadastrar novo cliente.</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/ws/clientes</td>
            <td>Buscar todos os clientes do usuário.</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/ws/clientes?nome={NomeCliente}</td>
            <td>Buscar cliente por nome e usuário.</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/ws/clientes/{idCliente}</td>
            <td>Deletar cliente por ID.</td>
        </tr>
    </tbody>
</table>

### Vendas (Endpoints Protegidos: Requer Authorization: Bearer ${token})
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Método</th>
            <th>Endpoint</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>POST</td>
            <td>/ws/vendas</td>
            <td>Cadastrar nova venda.</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/ws/vendas</td>
            <td>Buscar todas as vendas do usuário.</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/ws/vendas/{idVenda}</td>
            <td>Deletar registro de venda por ID.</td>
        </tr>
    </tbody>
</table>

## Links do Projeto
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Recurso</th>
            <th>Link</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Repositório GitHub (Código Fonte)</td>
            <td><a href="https://github.com/PedroScheurer/api-central-corretor" target="_blank">https://github.com/PedroScheurer/api-central-corretor</a></td>
        </tr>
        <tr>
            <td>DockerHub (Imagem Docker)</td>
            <td>pedroscheurer/api-central-corretor:latest</td>
        </tr>
        <tr>
            <td>API em Produção (Koyeb)</td>
            <td><a href="https://civic-sarajane-pedroscheurer-fd914fc3.koyeb.app" target="_blank">https://civic-sarajane-pedroscheurer-fd914fc3.koyeb.app</a></td>
        </tr>
    </tbody>
</table>

## Como Executar Localmente

Clone o repositório: git clone https://github.com/PedroScheurer/api-central-corretor.git<br>
Configure as variáveis de ambiente (URL do DB, credenciais JWT)<br>
Execute a aplicação via Docker: docker-compose up --build<br>
A API estará disponível em http://localhost:8080.<br>
