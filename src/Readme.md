# Transação API

Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas. A API foi desenvolvida com Java e Spring Boot.




## Tecnologias Utilizadas

Java: JDK 21+.
Maven: Versão 3.8.1+.
Git: Para clonar o repositório.
Docker (opcional).

##  Como Configurar o Projeto

1. Clone o Repositório

2. Compile o Projeto

```bash
 mvn clean install
```

3. Execute o Projeto

```bash
mvn spring-boot:run
```

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloSegundos` | `integer` | **Não Obrigatório** O padrão default é 60s  |