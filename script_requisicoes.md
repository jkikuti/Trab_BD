# Método Cliente

## Visualisação de todos os clientes
- **Method:** GET
- **URL:** http://localhost:8080/clientes

## Visualisação de um cliente
- **Method:** GET
- **URL:** http://localhost:8080/clientes/{idCliente}

## Cadastro de um cliente
- **Method:** POST
- **URL:** http://localhost:8080/clientes
- **Body:** 

```json
{
    "nome": "Rodrigo Marcelo",
    "cpf": "000.000.000-00",
    "email": "rodrigo.marcelo@exemplo.com"
}
```

## Atualização de um cliente
- **Method:** PUT
- **URL:** http://localhost:8080/clientes/{idCliente}
- **Body:** 

```json
{
    "nome": "Rodrigo Marcelo Kikuti",
    "cpf": "000.000.000-00",
    "email": "rodrigo.marcelo.kikuti@exemplo.com"
}
```

## Exclusão de um cliente
- **Method:** DELETE
- **URL:** http://localhost:8080/clientes/{idCliente}

# Método Endereço

## Visualisação de todos os endereços
- **Method:** GET
- **URL:** http://localhost:8080/endereco-completo

## Visualisação de um endereço
- **Method:** GET
- **URL:** http://localhost:8080/endereco-completo/{idEndereco}

## Cadastro de um endereço
- **Method:** POST
- **URL:** http://localhost:8080/endereco-completo
- **Body:** 

```json
{
    "estado": "PR",
    "cidade": "Londrina",
    "cep": "86065-400",
    "logradouro": "Rua Serra do Maracju",
    "tipoLogradouro": "Avenida",
    "numero": 469,
    "complemento": "Casa",
    "bairro": "Bandeirantes"
}
```

```json
{
    "estado": "RS",
    "cidade": "Porto Alegre",
    "cep": "90050-000",
    "logradouro": "Avenida Borges de Medeiros",
    "tipoLogradouro": "Avenida",
    "numero": 2500,
    "complemento": "Bloco B",
    "bairro": "Centro Histórico"
}
```

## Atualização de um endereço
- **Method:** PUT
- **URL:** http://localhost:8080/endereco-completo/{idEndereco}
- **Body:** 

```json
{
    "estado": "PR",
    "cidade": "Londrina",
    "cep": "86065-400",
    "logradouro": "Rua Serra do Maracaju",
    "tipoLogradouro": "Rua",
    "numero": 468,
    "complemento": "Casa Geminada",
    "bairro": "Bandeirantes"
}
```

## Exclusão de um endereço
- **Method:** DELETE
- **URL:** http://localhost:8080/endereco-completo/{idEndereco}

# Método Assossiação de Endereço com Cliente

## Visualisação de todos os endereços de um cliente
- **Method:** GET
- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}

## Associar cliente a um endereço
- **Method:** POST
- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}/endereco/{idEndereco}

## Desassociar cliente a um endereço
- **Method:** DELETE
- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}/endereco/{idEndereco}

# Método Fabricante

## Visualisação de todos os fabricantes
- **Method:** GET
- **URL:** http://localhost:8080/fabricante

## Visualisação de um fabricante
- **Method:** GET
- **URL:** http://localhost:8080/fabricante/{idFabricante}

## Cadastro de um fabricante
- **Method:** POST
- **URL:** http://localhost:8080/fabricante
- **Body:** 

```json
{
    "nomeFabricante": "Kikuti Company"
}
```

## Atualização de um fabricante
- **Method:** PUT
- **URL:** http://localhost:8080/fabricante/{idFabricante}
- **Body:** 

```json
{
    "nomeFabricante": "Kikuti Company LTDA"
}
```

## Exclusão de um fabricante
- **Method:** DELETE
- **URL:** http://localhost:8080/fabricante/{idFabricante}

# Método Action Figure

## Visualisação de todos os Action Figures
- **Method:** GET
- **URL:** http://localhost:8080/action-figure

## Visualisação de um Action Figure
- **Method:** GET
- **URL:** http://localhost:8080/action-figure/{idActionFigure}

## Cadastro de um Action Figure
- **Method:** POST
- **URL:** http://localhost:8080/action-figure
- **Body:** 

```json
{
    "personagem": "Superman",
    "universo": "Marvel",
    "tamanho": 30,
    "preco": 129.99,
    "descricao": "Figura articulada do Superman",
    "categoria": "Filmes",
    "estoque": 6,
    "idFabricante": 4
}
```

## Atualização de um Action Figure
- **Method:** PUT
- **URL:** http://localhost:8080/action-figure/{idActionFigure}
- **Body:** 

```json
{
    "personagem": "Superman",
    "universo": "DC",
    "tamanho": 30,
    "preco": 129.99,
    "descricao": "Action Figure do personagem Superman da série DC",
    "categoria": "Filmes",
    "estoque": 6,
    "idFabricante": 4
}
```

## Exclusão de um Action Figure
- **Method:** DELETE
- **URL:** http://localhost:8080/action-figure/{idActionFigure}

## Filtrar Action Figures
- **Method:** GET
- **URLS:**
    - **URL:** http://localhost:8080/action-figure/filtrar?idFabricante=2
    - **URL:** http://localhost:8080/action-figure/filtrar?categoria=Filmes
    - **URL:** http://localhost:8080/action-figure/filtrar?universo=DC
    - **URL:** http://localhost:8080/action-figure/filtrar?personagem=Sora
    - **URL:** http://localhost:8080/action-figure/filtrar?idFabricante=4&categoria=Filmes&universo=DC&personagem=Superman

# Método Imagem

## Visualisação de todas as imagens
- **Method:** GET
- **URL:** http://localhost:8080/imagens

## Visualisação de uma imagem
- **Method:** GET
- **URL:** http://localhost:8080/imagens/{idImagem}

## Cadastro de uma imagem
- **Method:** POST
- **URL:** http://localhost:8080/imagens
- **Body:** 

```json
{
    "imagemUrl": "http://exemplo.com/imagem.png",
    "idActionFigure": 1
}
```

## Atualização de uma imagem
- **Method:** PUT
- **URL:** http://localhost:8080/imagens/{idImagem}
- **Body:** 

```json
{
    "imagemUrl": "http://exemplo.com/imagem.png",
    "idActionFigure": 2
}
```

## Exclusão de uma imagem
- **Method:** DELETE
- **URL:** http://localhost:8080/imagens/{idImagem}

# Método Material

## Visualisação de todos os materiais
- **Method:** GET
- **URL:** http://localhost:8080/materiais

## Visualisação de um material
- **Method:** GET
- **URL:** http://localhost:8080/materiais/{idMaterial}

## Cadastro de um material
- **Method:** POST
- **URL:** http://localhost:8080/materiais
- **Body:** 

```json
{
    "tipoMaterial": "PVC"
}
```

## Atualização de um material
- **Method:** PUT
- **URL:** http://localhost:8080/materiais/{idMaterial}
- **Body:** 

```json
{
    "tipoMaterial": "Argila"
}
```

## Exclusão de um material
- **Method:** DELETE
- **URL:** http://localhost:8080/materiais/{idMaterial}

# Método Action Figure Material

## Visualisação de todos os materiais de todas Action Figure
- **Method:** GET
- **URL:** http://localhost:8080/action-figure-material

## Associar material a um Action Figure
- **Method:** POST
- **URL:** http://localhost:8080/action-figure-material
- **Body:** 

```json
{
    "idActionFigure": 1,
    "idMaterial": 4
}
```

## Desassociar material a um Action Figure
- **Method:** DELETE
- **URL:** http://localhost:8080/action-figure-material/af/{idActionFigure}/material/{idMaterial}