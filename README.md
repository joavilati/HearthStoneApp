# Projeto Hearthstone APP [![Android CI](https://github.com/joavilati/HearthStoneApp/actions/workflows/main.yml/badge.svg)](https://github.com/joavilati/HearthStoneApp/actions/workflows/main.yml)

Este projeto utiliza a API do Hearthstone disponível no RapidAPI. Para garantir o funcionamento correto do projeto, é necessário configurar algumas chaves de API.

## Configuração

1. Acesse [https://rapidapi.com/omgvamp/api/hearthstone](https://rapidapi.com/omgvamp/api/hearthstone) e obtenha sua chave de API (`X_RapidAPI_Key`) e o host (`X_RapidAPI_Host`).

2. No arquivo `local.properties` do seu projeto, adicione as seguintes linhas:

Substitua `sua_chave_aqui` e `sua_host_aqui` pelos valores obtidos no passo 1.

```properties
xRapidAPIKey=sua_chave_aqui
xRapidAPIHost=sua_host_aqui
```

## Importante

- **Nunca** cometa o arquivo `local.properties` em repositórios públicos para evitar a exposição de suas chaves de API.
- Certifique-se de que o arquivo `local.properties` está listado no `.gitignore` para evitar o seu envio acidental.
