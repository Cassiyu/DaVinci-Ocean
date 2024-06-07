# Ocean

API do Projeto DaVinci Ocean - Sistema de Monitoramento e Conservação dos Habitats Marinhos

## Integrantes
 
- RM550341 - Allef Santos (2TDSPV) 
- RM551491 - Cassio Yuji Hirassike Sakai 
- RM97836  - Debora Damasso Lopes 
- RM550323 - Paulo Barbosa Neto 
- RM552314 - Yasmin Araujo Santos Lopes

## Competência 

- Allef - Java Advanced e Mastering Relational AND Non-Relational DataBase

- Cassio Y.H. Sakai - Java Advanced e Mobile Application Development

- Debora D. Lopes - Advanced Business Development With .NET e Disruptive Architectures: IOT, IOB & GENERATIVE IA 

- Paulo B. Neto - Mastering Relational AND Non-Relational DataBase

- Yasmin A. S. Lopes - Compliance, Quality Assurance & Tests e DevOps Tools & Cloud Computing

## Objetivo do Projeto

O Projeto DaVinci Ocean tem como objetivo desenvolver uma plataforma de monitoramento avançado das temperaturas dos oceanos, visando compreender e mitigar os impactos das mudanças climáticas nos ecossistemas marinhos, especialmente nos corais. A proposta central é coletar dados em tempo real das temperaturas oceânicas em diferentes regiões do mundo e analisar essas informações para identificar padrões, tendências e anomalias que possam afetar a saúde dos corais.

O público-alvo do Projeto DaVinci Ocean são cientistas, pesquisadores e empresas que estão interessados em monitorar e proteger os ecossistemas marinhos, especialmente os recifes de coral. Ao fornecer dados precisos e atualizados sobre as temperaturas dos oceanos, a plataforma permite uma compreensão mais profunda dos impactos das mudanças climáticas nos corais e nas comunidades marinhas, facilitando a implementação de medidas de conservação e adaptação.

## Requisitos

- [ ] CRUD de Sensores
- [ ] Geração de Relatórios 
- [ ] Cadastro e Autenticação de Usuários

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
        "sensor_id": 1,
        "data": "27-05-2024T14:00:00",
        "temperatura": 25.5,
        "localizacao": "Recife 1"
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
    "localizacao": "Recife 1"
}
```

#### Exemplo de Resposta
```js
{
    "sensor_id": 1,
    "data": "27-05-2024T14:00:00",
    "temperatura": 25.5,
    "localizacao": "Recife 1"
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
    "sensor_id": 1,
    "data": "27-05-2024T14:00:00",
    "temperatura": 25.5,
    "localizacao": "Recife 1"
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
    "localizacao": "Recife 1"
}
```

#### Exemplo de Resposta
```js
{
    "sensor_id": 1,
    "data": "28-05-2024T14:00:00",
    "temperatura": 26.0,
    "localizacao": "Recife 1"
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
|data_inicio|datetime|✅|Data e hora de início do período|
|data_fim|datetime|✅|Data e hora de fim do período|
|localizacao|string|✅|Localização para o relatório|

#### Exemplo de Resposta

```json
{
    "data_inicio": "01-05-2024T00:00:00",
    "data_fim": "31-05-2024T23:59:59",
    "localizacao": "Recife 1",
    "temperatura_media": 24.3,
    "temperatura_maxima": 29.5,
    "temperatura_minima": 18.7
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



