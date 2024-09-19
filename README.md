# Marketplace de Action Figures

## Descrição do Projeto

Este projeto visa desenvolver um marketplace dedicado à venda de action figures. A plataforma permitirá aos usuários explorar uma vasta coleção de figuras de ação, categorizadas em filmes, seriados, games, animes, desenhos e diversos temas como música, personalidades e esportes. O sistema será desenvolvido utilizando a arquitetura MVC (Model-View-Controller), com uma API REST para comunicação e PostgreSQL para armazenamento dos dados.

## Funcionalidades Principais

- **Cadastro de Action Figures**: Administradores poderão cadastrar novas figuras de ação, incluindo informações como título, categoria, descrição, preço, imagem, fabricante, materiais e estoque.
- **Cadastro de Clientes**: Clientes poderão se registrar, fornecendo informações como nome, CPF, e-mail e endereço. O endereço agora inclui dados detalhados, como **estado**, **cidade**, **bairro**, **logradouro** e **cep**.
- **Gerenciamento de Compras**: Clientes podem comprar action figures, com transações registradas no banco de dados e associadas aos respectivos pedidos. O preço da action figure no momento da compra é registrado por meio do campo `valor_unitario` para maior precisão.
- **Buscas e Filtragens**: Clientes poderão buscar action figures usando filtros como:
  - **Categoria**: Filmes, Seriados, Games, Animes, Desenhos, Diversos (Música, Personalidades, Esportes)
  - **Personagens**: Nome do personagem
  - **Universo**: Nome da franquia
  - **Fabricante**: Nome do fabricante
- **Carrinho de Compras**: Implementação de um carrinho de compras para adição de itens antes da finalização da compra.

## Estrutura de Endereços no Cadastro de Clientes

A tabela de **endereço** agora depende de uma estrutura hierárquica:
- **Estado**
- **Cidade** (relacionada a um Estado)
- **Bairro** (relacionado a uma Cidade)
- **Logradouro** (relacionado a uma Cidade)
- **CEP** (relacionado a um Logradouro e um Bairro)

## Ficha Técnica de uma Action Figure

- **id**: 8
- **personagem**: "Shiro"
- **universo**: "No Game No Life"
- **fabricante**: "Good Smile Company"
- **tamanho**: 12
- **preco**: 49.99
- **descricao**: "Action figure do personagem Shiro da série No Game No Life"
- **categoria**: "Anime"
- **estoque**: 5
- **materiais**: ["Plástico"]
- **imagens**: ["https://example.com/shiro1.jpg", "https://example.com/shiro2.jpg"]

## Relatórios

### Relatório de Vendas por Período
- **Descrição**: Total de vendas realizadas em um determinado período, permitindo análise do desempenho da loja.
- **Dados Incluídos**: Data da venda, título da action figure, quantidade vendida, receita gerada (calculada utilizando `valor_unitario`).
- **Consultas SQL Utilizadas**:
  - **Agregações**: `SUM` para calcular a receita gerada.
  - **Agrupamentos**: Agrupamento por data da venda e título da action figure.
  - **Ordenações**: Ordenação por data da venda e título da action figure.

### Relatório de Action Figures Mais Vendidas
- **Descrição**: Lista das action figures mais vendidas em um período específico.
- **Dados Incluídos**: Título da action figure, total de vendas (quantidade vendida).
- **Consultas SQL Utilizadas**:
  - **Agregações**: `SUM` para calcular o total de vendas por action figure.
  - **Agrupamentos**: Agrupamento pelo título da action figure.
  - **Ordenações**: Ordenação por total de vendas em ordem decrescente.

### Relatório de Receitas
- **Descrição**: Receita total gerada por vendas em diferentes períodos.
- **Dados Incluídos**: Receita total por período (como mensal ou trimestral).
- **Consultas SQL Utilizadas**:
  - **Agregações**: `SUM` para calcular a receita total, com base no `valor_unitario` registrado no momento da compra.
  - **Agrupamentos**: Agrupamento por período (por exemplo, mês).
  - **Ordenações**: Ordenação por período.

## Tecnologias Utilizadas

- **React**: Biblioteca JavaScript para a criação de interfaces de usuário.
- **Java EE**: Stack principal para o desenvolvimento do backend.
- **PostgreSQL**: Banco de dados relacional para armazenar informações da loja, action figures, clientes, endereços e transações.
- **Spring Framework**: Facilita a criação de APIs REST e a organização do código.
- **JSON**: Formato de troca de dados entre frontend e backend.
