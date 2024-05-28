# Ocean

API do Projeto DaVinci Ocean - Sistema de Monitoramento e Conservação dos Habitats Marinhos

## Requisitos

- [ ] CRUD de Sensores
- [ ] CRUD de Relatórios
- [ ] CRUD de Usuários
- [ ] Autenticação
- [ ] Dashboard

## Documentação da API

### Endpoints 

- [Listar Sensores](#listar-sensores)
- [Cadastrar Sensor](#cadastrar-sensor)
- [Detalhar Sensor](#detalhar-sensor)
- [Apagar Sensor](#apagar-sensor)
- [Atualizar Sensor](#atualizar-sensor)
- [Gerar Relatório](#gerar-relatório)
- [Autenticação de Usuário](#autenticação-de-usuário)
- [Cadastrar Usuário](#cadastrar-usuário)

---

### Listar Sensores

`GET` /sensor

Retorna um array com todos os sensores cadastrados.

#### Exemplo de Resposta

```json
[
    {
        "sensorId": 1,
        "data": "27-05-2024T14:00:00",
        "temperatura": 25.5,
        "localizacao": "-23.5505, -46.6333"
    }   
]
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Lista de sensores retornada com sucesso
|401| Não autenticado. Se autentique em /login
---

### Cadastrar Sensor

`POST` /sensor
Cadastra um sensor com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|data|datetime|✅|Data e hora da leitura
|temperatura|double|✅|Temperatura registrada pelo sensor
|localizacao|string|✅|localização do sensor

#### Exemplo de Requisição
```js
// POST /sensor
{
    "data": "27-05-2024T14:00:00",
    "temperatura": 25.5,
    "localizacao": "-23.5505, -46.6333"
}
```

#### Exemplo de Resposta
```js
{
    "sensorId": 1,
    "data": "27-05-2024T14:00:00",
    "temperatura": 25.5,
    "localizacao": "-23.5505, -46.6333"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|201| Sensor criado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login
---

### Detalhar Sensor

`GET` /sensor/`{id}`

Retorna os detalhes do sensor com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /sensor/1
{
    "sensorId": 1,
    "data": "27-05-2024T14:00:00",
    "temperatura": 25.5,
    "localizacao": "-23.5505, -46.6333"
}

```

#### Códigos de Status

|código|descrição
|------|---------
|200| Sensor retornado com sucesso
|401| Não autenticado. Se autentique em /login
|404| Não existe sensor com o `id` informado
---

### Apagar Sensor

`DELETE` /sensor/`{id}`

Apaga o sensor com o `id` informado no path

#### Códigos de Status

|código|descrição
|------|---------
|204| Sensor apagado com sucesso
|401| Não autenticado. Se autentique em /login
|404| Não existe sensor com o `id` informado
---

### Atualizar Sensor

`PUT`  /sensor/`{id}`

Atualiza os dados do sensor com o `id` informado no path, utilizando as informações do corpo da requisição

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|data|datetime|✅|Nova data e hora da leitura
|temperatura|double|✅|Nova temperatura registrada pelo sensor
|localizacao|string|✅|Novas localizações do sensor

#### Exemplo de Requisição
```js
// PUT /sensor/1
{
    "data": "28-05-2024T14:00:00",
    "temperatura": 26.0,
    "localizacao": "-23.5505, -46.6333"
}
```

#### Exemplo de Resposta
```js
{
    "sensorId": 1,
    "data": "28-05-2024T14:00:00",
    "temperatura": 26.0,
    "localizacao": "-23.5505, -46.6333"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Sensor atualizado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
|401| Não autenticado. Se autentique em /login
|404| Não existe sensor com o `id` informado
---

### Gerar Relatório

`GET` /relatorio

Gera um relatório de temperatura para um determinado período e localização.

#### Parâmetros de Consulta

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|dataInicio|datetime|✅|Data e hora de início do período|
|dataFim|datetime|✅|Data e hora de fim do período|
|localizacao|string|✅|Localização para o relatório|

#### Exemplo de Resposta

```json
{
    "dataInicio": "01-05-2024T00:00:00",
    "dataFim": "31-05-2024T23:59:59",
    "localizacao": "Praia de Copacabana",
    "temperaturaMedia": 24.3,
    "temperaturaMaxima": 29.5,
    "temperaturaMinima": 18.7
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Relatório gerado com sucesso
|400| Parâmetros de consulta inválidos
|401| Não autenticado. Se autentique em /login
---

### Autenticação de Usuário

`POST` /login

Autentica o usuário e retorna um token de acesso.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|email|string|✅|E-mail do usuário|
|senha|string|✅|Senha do usuário|

#### Exemplo de Requisição

```json
{
    "email": "usuario@example.com",
    "senha": "senha123"
}
```

#### Exemplo de Resposta

```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3VhcmlvQGV4YW1wbGUuY29tIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Autenticação bem-sucedida
|401| Credenciais inválidas
---

### Cadastrar Usuário

`POST` /usuario

Cadastra um novo usuário com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅|Nome do usuário|
|email|string|✅|E-mail do usuário|
|senha|string|✅|Senha do usuário|

#### Exemplo de Requisição

```json
{
    "nome": "Usuário Exemplo",
    "email": "usuario@example.com",
    "senha": "senha123"
}
```

#### Exemplo de Resposta

```json
{
    "id": 1,
    "nome": "Usuário Exemplo",
    "email": "usuario@example.com"
}
```

#### Códigos de Status

|código|descrição|
|------|---------|
|201| Usuário cadastrado com sucesso|
|400| Validação falhou. Verifique o corpo da requisição|



