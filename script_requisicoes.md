# Método Cliente

## Visualisação de todos os clientes
-- **Method:** GET
-- **URL:** http://localhost:8080/clientes

## Visualisação de um cliente
-- **Method:** GET
-- **URL:** http://localhost:8080/clientes/{id}

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
-- **URL:** http://localhost:8080/clientes/{id}
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
-- **URL:** http://localhost:8080/clientes/{id}