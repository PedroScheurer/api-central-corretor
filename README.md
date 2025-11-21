# API Central do Corretor
Uma API RESTful robusta desenvolvida para auxiliar corretores de imóveis na gestão de seus cadastros de imóveis, clientes e vendas. Construída com Spring Boot e foco em segurança via JWT.

## Funcionalidades Principais
O sistema oferece um conjunto completo de operações CRUD (Create, Read, Update, Delete) para as entidades principais: Usuários, Imóveis, Clientes e Vendas.

👥 Gestão de Usuários (Autenticação)
-Autocadastro de novos usuários.
-Login para obtenção do token de acesso (JWT).

🏡 Gestão de Imóveis
-Cadastrar um novo imóvel.
-Buscar todos os imóveis cadastrados pelo usuário.
-Buscar imóvel específico por nome e usuário.
-Atualizar dados de um imóvel existente.
-Deletar um imóvel.

🧑‍💼 Gestão de Clientes
-Cadastrar um novo cliente.
-Buscar todos os clientes cadastrados pelo usuário.
-Buscar cliente específico por nome e usuário.
-Deletar um cliente.

💰 Gestão de Vendas
-Cadastrar uma nova venda (vinculando cliente e imóvel).
-Buscar todas as vendas realizadas pelo usuário.
-Deletar um registro de venda.

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
A aplicação utiliza as seguintes dependências principais do ecossistema Spring:
-spring-boot-web: Para criar serviços web RESTful.
-spring-boot-jpa: Para persistência de dados (ORM com Hibernate).
-spring-boot-security: Para controle de acesso e autenticação.
-postgresql: Driver de conexão com o banco de dados.
-jjwt-api, jjwt-imp, jjwt-jackson: Implementação do JWT para criação e validação de tokens.

## Autenticação (Endpoints Abertos)
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
