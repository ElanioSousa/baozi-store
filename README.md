# Baozi Store - API REST

Trabalho da disciplina Desenvolvimento Web Back-End.

API REST simples para a Baozi Store, uma loja de pão chinês, com cadastro de clientes, produtos e pedidos.

## Tecnologias

- Java 17
- Spring Boot 3.3
- Spring Data JPA
- MySQL
- Postman (testes)

## Estrutura (MVC)

```
src/main/java/com/baozistore/api
├── BaoziStoreApplication.java
├── model/        Cliente, Produto, Pedido (entidades JPA)
├── repository/   ClienteRepository, ProdutoRepository, PedidoRepository
└── controller/   ClienteController, ProdutoController, PedidoController
```

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | /clientes | Cria cliente |
| GET | /clientes | Lista clientes |
| GET | /clientes/{id} | Consulta cliente por ID |
| PUT | /clientes/{id} | Atualiza cliente (opcional) |
| DELETE | /clientes/{id} | Apaga cliente |
| POST | /produtos | Cria produto |
| GET | /produtos | Lista produtos |
| GET | /produtos/{id} | Consulta produto por ID |
| PUT | /produtos/{id} | Atualiza produto (opcional) |
| DELETE | /produtos/{id} | Apaga produto |
| POST | /pedidos | Registra pedido |
| GET | /pedidos | Lista pedidos |
| GET | /pedidos/{id} | Consulta pedido por ID |
| PUT | /pedidos/{id} | Atualiza pedido (opcional) |
| DELETE | /pedidos/{id} | Apaga pedido |

## Como executar

1. Crie o usuário no MySQL:
   ```sql
   CREATE USER 'baozi'@'localhost' IDENTIFIED BY 'baozi123';
   GRANT ALL PRIVILEGES ON baozi_store.* TO 'baozi'@'localhost';
   FLUSH PRIVILEGES;
   ```
2. Rode a classe `BaoziStoreApplication` (no STS: botão direito > Run As > Spring Boot App)
   ou no terminal: `mvn spring-boot:run`.
3. A API fica em `http://localhost:8080`.

Para testar, importe no Postman o arquivo `baozi-store.postman_collection.json`.
