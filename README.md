# CapivaraFinance

- CapivaraFinance é uma API de controle de finanças pessoais que possui as funções básicas de uma API destinada a essa área

# Funcionalidades

- Ela permite que você cadastre seus ganhos e gastos por meio do endpoint "financial_entries" e também que você veja o quanto você possui no seu saldo atualmente

<br>

- Possui autenticação com token JWT para trazer mais segurança para os usuários que forem utilizar a aplicação que consumirá essa API

<br>

- Possui capacidade de filtrar os dados cadastrados por tipo (gastos ou ganhos) e por data (data máxima e data mínima)

<br>

- Executa validação nos emails registrados para cada conta

# Tecnologias utilizadas
- Spring Boot
- Java
- PostgreSQL
- JUnit e Mockito nos testes unitários
- Email Validator do Apache Commons para validar os emails
- Diferente das outras APIS que eu desenvolvi, nessa API utilizei JPQL para fazer as requisições customizadas nos Repositories, o que resultou em um código muito mais limpo e legível
- Também utilizei o padrão de projeto DTO em multiplas ocasiões

# Observação
- Ainda estou trabalhando em testes unitários para essa API
