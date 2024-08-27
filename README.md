# Marketplace de Action Figures

## Descrição do Projeto

Este projeto visa desenvolver um marketplace dedicado à venda de action figures. A plataforma permitirá aos usuários explorar uma vasta coleção de figuras de ação, categorizadas em filmes, super-heróis, seriados, games, personalidades, esportes, animes, desenhos e música. O sistema será desenvolvido utilizando a arquitetura MVC (Model-View-Controller), com uma API REST para comunicação e PostgreSQL para armazenamento dos dados.

## Funcionalidades Principais

- **Cadastro de Action Figures**: Administradores poderão cadastrar novas figuras de ação, incluindo informações como título, categoria, descrição, preço e imagem.
- **Cadastro de Clientes**: Clientes poderão se registrar, fornecendo informações como nome, e-mail e endereço.
- **Gerenciamento de Compras**: Permite aos clientes comprar action figures, com registros de transações no banco de dados.
- **Buscas e Filtragens**: Clientes poderão buscar action figures usando filtros como:
  - **Tema**: Filmes, Super-Heróis, Seriados, Games, Personalidades, Esportes, Animes, Desenhos, Música
  - **Personagens**: Nomes dos personagens
  - **Universo**: Nomes das franquias
  - **Marca**: Nomes das marcas
- **Carrinho de Compras**: Implementação de um carrinho de compras para adição de itens antes da finalização da compra.

## Ficha Técnica de uma Action Figure

- **Marca**: Original Beast Kingdom
- **Personagem**: Alice
- **Material**: Plástico/PVC/Vinil (pode ser feito de mais de um material)
- **Tamanho**: 10 cm
- **Universo**: Alice no País das Maravilhas
- **Tema**: Desenhos

## Relatórios

### Relatório de Vendas por Período
- **Descrição**: Total de vendas realizadas em um determinado período, permitindo análise do desempenho da loja.
- **Dados Incluídos**: Data da venda, título da action figure, quantidade vendida, receita gerada.
- **Consultas SQL Utilizadas**: Agregações (SUM), agrupamentos (GROUP BY), ordenações (ORDER BY).

### Relatório de Action Figures Mais Vendidas
- **Descrição**: Action figures mais vendidas em um período específico.
- **Dados Incluídos**: Título da action figure, total de vendas.
- **Consultas SQL Utilizadas**: Agregações (SUM), agrupamentos (GROUP BY), ordenações (ORDER BY).

### Relatório de Receitas
- **Descrição**: Receita total gerada por vendas em diferentes períodos.
- **Dados Incluídos**: Receita total por período.
- **Consultas SQL Utilizadas**: Agregações (SUM), agrupamentos (GROUP BY), ordenações (ORDER BY).

## Tecnologias Utilizadas

- **Java EE**: Stack principal para o desenvolvimento do backend.
- **PostgreSQL**: Banco de dados relacional para armazenar informações da loja, action figures, clientes e transações.
- **Spring Framework**: Facilita a criação de APIs REST e a organização do código.
- **JSON**: Formato de troca de dados entre frontend e backend.
