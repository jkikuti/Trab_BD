# Método Cliente

## Visualisação de todos os clientes
-- **Method:** GET
-- **URL:** http://localhost:8080/clientes

## Visualisação de um cliente
-- **Method:** GET
-- **URL:** http://localhost:8080/clientes/{idCliente}

## Cadastro de um cliente
-- **Method:** POST
-- **URL:** http://localhost:8080/clientes
-- **Body:** 

```json
{
    "nome": "Rodrigo Marcelo",
    "cpf": "000.000.000-00",
    "email": "rodrigo.marcelo@exemplo.com"
}
```

## Atualização de um cliente
-- **Method:** PUT
-- **URL:** http://localhost:8080/clientes/{idCliente}
-- **Body:** 

```json
{
    "nome": "Rodrigo Marcelo Kikuti",
    "cpf": "000.000.000-00",
    "email": "rodrigo.marcelo.kikuti@exemplo.com"
}
```

## Exclusão de um cliente
-- **Method:** DELETE
-- **URL:** http://localhost:8080/clientes/{idCliente}

# Método Endereço

## Visualisação de todos os endereços
-- **Method:** GET
-- **URL:** http://localhost:8080/endereco-completo

## Visualisação de um endereço
-- **Method:** GET
-- **URL:** http://localhost:8080/endereco-completo/{idEndereco}

## Cadastro de um endereço
-- **Method:** POST
-- **URL:** http://localhost:8080/endereco-completo

-- **Body:** 

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

## Atualização de um endereço
-- **Method:** PUT
-- **URL:** http://localhost:8080/endereco-completo/{idEndereco}
-- **Body:** 

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
-- **Method:** DELETE
-- **URL:** http://localhost:8080/endereco-completo/{idEndereco}

# Método Assossiação de Endereço com Cliente

## Visualisação de todos os endereços de um cliente
-- **Method:** GET
-- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}

## Associar cliente a um endereço
-- **Method:** POST
-- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}/endereco/{idEndereco}

## Desassociar cliente a um endereço
-- **Method:** DELETE
-- **URL:** http://localhost:8080/cliente-endereco/cliente/{idCliente}/endereco/{idEndereco}

# Método Fabricante

## Visualisação de todos os fabricantes
-- **Method:** GET
-- **URL:** http://localhost:8080/fabricante

## Visualisação de um fabricante
-- **Method:** GET
-- **URL:** http://localhost:8080/fabricante/{idFabricante}

## Cadastro de um fabricante
-- **Method:** POST
-- **URL:** http://localhost:8080/fabricante
-- **Body:** 

```json
{
    "nomeFabricante": "Kikuti Company"
}
```

## Atualização de um fabricante
-- **Method:** PUT
-- **URL:** http://localhost:8080/fabricante/{idFabricante}
-- **Body:** 

```json
{
    "nomeFabricante": "Kikuti Company LTDA"
}
```

## Exclusão de um fabricante
-- **Method:** DELETE
-- **URL:** http://localhost:8080/fabricante/{idFabricante}



