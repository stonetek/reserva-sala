Reserva de Salas API

API REST desenvolvida com Java e Spring Boot para gerenciamento de reservas de salas de reunião e auditórios.

O sistema foi criado para centralizar e organizar o processo de agendamento de ambientes corporativos, evitando conflitos de horário, inconsistências e retrabalho operacional.
Tecnologias Utilizadas
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
H2 Database
Bean Validation
Lombok
Swagger/OpenAPI

Funcionalidades:

Cadastro de salas,
Criação de reservas,
Validação de conflito de horários,
Cancelamento de reservas,
Consulta de agenda diária,
Consulta de salas disponíveis,
Tratamento global de exceções,
Validações com Bean Validation,
Testes unitários.

Regras de Negócio

Reservas

Não é permitido criar reservas com horários conflitantes.
A hora final deve ser maior que a hora inicial.
Não é permitido criar reservas para datas passadas.
Reservas canceladas deixam de bloquear a sala.

Tipos de Sala

COLETIVA,

INDIVIDUAL,

AUDITORIO.

Status da Reserva

ATIVA

CANCELADA