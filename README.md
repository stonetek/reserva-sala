# Reserva de Salas API

API REST desenvolvida com Java e Spring Boot para gerenciamento de reservas de salas de reunião e auditórios.

O sistema foi criado para centralizar e organizar o processo de agendamento de ambientes corporativos, evitando conflitos de horário, inconsistências e retrabalho operacional.

---

# Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation
- Lombok
- Swagger/OpenAPI
- JUnit 5
- Mockito

---

# Funcionalidades

- Cadastro de salas
- Criação de reservas
- Validação de conflito de horários
- Cancelamento de reservas
- Consulta de agenda diária
- Consulta de salas disponíveis
- Tratamento global de exceções
- Validações com Bean Validation
- Testes unitários

---

# Regras de Negócio

## Reservas

- Não é permitido criar reservas com horários conflitantes
- A hora final deve ser maior que a hora inicial
- Não é permitido criar reservas para datas passadas
- Reservas canceladas deixam de bloquear a sala

---

# Tipos de Sala

- COLETIVA
- INDIVIDUAL
- AUDITORIO

---

# Status da Reserva

- ATIVA
- CANCELADA

---

# Como Executar

## Clonar projeto

```bash
git clone https://github.com/stonetek/reserva-sala.git
```

## Executar aplicação

```bash
mvn spring-boot:run
```

---

# Swagger

```text
http://localhost:8098/swagger-ui/index.html
```

---

# H2 Console

```text
http://localhost:8098/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:reservadb
```

Usuário:

```text
sa
```

Senha:

```text
(vazio)
```

---

# Demonstração

## 📌 Documentação da API (Swagger)

![Swagger Endpoints](./screenshot/api_endpoints.png)

---

## 🗄️ Banco de Dados (H2 Console)

![Tabela SALA](./screenshot/salas.png)

![Tabela RESERVA](./screenshot/reservas.png)

---

## ✅ Testes Unitários

![Testes passando](./screenshot/tests.png)

---

# Exemplos de Uso

## Criar sala

```http
POST /salas
```

```json
{
  "nome": "Sala 101",
  "tipo": "COLETIVA",
  "capacidade": 15
}
```

---

## Criar reserva

```http
POST /reservas
```

```json
{
  "responsavel": "Pedro",
  "data": "2026-06-01",
  "horaInicio": "10:00:00",
  "horaFim": "12:00:00",
  "salaId": 2
}
```

---

## Cancelar reserva

```http
PATCH /reservas/{id}/cancelar
```

---

## Consultar salas livres

```http
GET /salas/livres?data=2026-06-01&horaInicio=10:00:00&horaFim=12:00:00
```

---

# Autor

Desenvolvido por Pedro Paulo.