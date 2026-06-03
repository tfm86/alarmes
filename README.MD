Projeto: Sistema de Processamento de Alarmes  
Empresa: InovaTech Solutions  
Autor: Tiago Fernandes  
Cargo: Engenheiro de Software  
Data: 03/06/2026  
Versão: 1.0

# 🚨 Sistema de Processamento Assíncrono de Alarmes

📌 **Resumo**

O Sistema de Processamento de Alarmes foi desenvolvido utilizando **Java**, **Spring Boot**, **RabbitMQ instalado localmente** e **PostgreSQL**, aplicando boas práticas de engenharia de software para criar uma solução **assíncrona, escalável e resiliente**.

O projeto demonstra:

- Arquitetura desacoplada
- Processamento assíncrono via mensageria
- Persistência robusta
- Alta disponibilidade
- Tolerância a falhas

---

# 🎯 Objetivo

Criar um sistema capaz de:

- Receber alarmes via API REST
- Enfileirar mensagens no RabbitMQ
- Processar alarmes de forma assíncrona
- Persistir dados no PostgreSQL
- Garantir que nenhum alarme seja perdido

---

# 📌 Requisitos do Sistema

## ✅ Requisitos Funcionais

- Receber alarmes via endpoint REST (`POST /alarmes`)
- Publicar alarmes na fila `alarmes.fila`
- Processar mensagens via Consumer
- Persistir alarmes no banco de dados
- Listar alarmes cadastrados
- Buscar alarmes por severidade
- Buscar alarmes por origem

## 🔒 Requisitos Não Funcionais

- **Escalabilidade:** múltiplos consumers podem ser adicionados
- **Resiliência:** mensagens não são perdidas mesmo com falhas
- **Performance:** API responde em < 100ms
- **Disponibilidade:** sistema deve operar 24/7
- **Manutenibilidade:** arquitetura modular (Controller → Service → Repository)
- **Portabilidade:** executável em qualquer ambiente
- **Testabilidade:** cobertura de testes unitários e integração

---

# 🛠 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring AMQP
- RabbitMQ (instalado localmente)
- PostgreSQL
- JSON

---

# 🏗 Arquitetura do Sistema

Fluxo principal:

    Cliente → API REST → RabbitMQ → Consumer → PostgreSQL


### Diagrama Simplificado (ASCII)

                +-------------------+
                |      Cliente      |
                | (Front-end/App)   |
                +---------+---------+
                |
                v
                +-------------------+
                |    Controller     |
                | Recebe alarmes    |
                +---------+---------+
                |
                v
                +-------------------+
                |     Producer      |
                | Envia p/ RabbitMQ |
                +---------+---------+
                |
                v
                +-------------------+
                |     RabbitMQ      |
                |  Fila de alarmes  |
                +---------+---------+
                |
                v
                +-------------------+
                |     Consumer      |
                | Processa alarmes  |
                +---------+---------+
                |
                v
                +-------------------+
                |    PostgreSQL     |
                | Banco de dados    |
                +-------------------+

---

# 🧠 Modelo de Domínio

### Entidade: **Alarme**

| Campo         | Tipo             | Descrição |
|---------------|------------------|-----------|
| id            | Long             | Identificador único |
| origem        | String           | Equipamento que gerou o alarme |
| severidade    | String/Enum      | MINOR, MAJOR, CRITICAL |
| mensagem      | String           | Descrição do evento |
| timestamp     | LocalDateTime    | Momento do evento |
| correlacaoId  | String           | ID opcional para rastreamento |

---

# 🌐 Endpoints RESTful

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/alarmes` | Envia alarme para a fila |
| GET | `/alarmes` | Lista todos os alarmes |
| GET | `/alarmes/severidade/{severidade}` | Filtra por severidade |
| GET | `/alarmes/origem/{origem}` | Filtra por origem |

---

# 📈 Diagramas UML de Sequência

## 1. Enviar Alarme

    Cliente → Controller → Producer → RabbitMQ → Consumer → Service → Repository → PostgreSQL

    Cliente        Controller        Producer        RabbitMQ        Consumer        Service        Repository        PostgreSQL
    | POST /alarmes |               |               |                 |               |               |                 |
    |--------------->|               |               |                 |               |               |                 |
    |                | valida dados  |               |                 |               |               |                 |
    |                |-------------->| publica msg   |                 |               |               |                 |
    |                |               |-------------->| enfileira msg   |               |               |                 |
    |                |               |               |---------------> | recebe msg    |               |                 |
    |                |               |               |                 |-------------->| salvar alarme |                 |
    |                |               |               |                 |               |-------------->| INSERT alarme   |
    |                |               |               |                 |               |               |---------------> |
    |                |               |               |                 |               |               | <--- sucesso ---|
    | <--- 202 Accepted ------------------------------------------------|


---

## 2. Listar Alarmes

    Cliente → Controller → Service → Repository → PostgreSQL


---

# 👤 Diagrama UML de Caso de Uso

    +-------------------+
    |      Cliente      |
    | (Usuário Externo) |
    +---------+---------+
    |
    v
    +-------------------------------------------+
    |     Sistema de Processamento de Alarmes   |
    |-------------------------------------------|
    |  • Enviar Alarme                          |
    |  • Listar Alarmes                         |
    |  • Buscar por Severidade                  |
    |  • Buscar por Origem                      |
    +-------------------------------------------+


---

# ✅ Resultados Obtidos

- Pipeline assíncrono funcionando de ponta a ponta
- Zero perda de mensagens
- Persistência confiável no PostgreSQL
- Arquitetura modular e escalável
- Logs claros e rastreáveis
- Sistema pronto para produção

---

# 🚀 Próximos Passos

- Implementar Dead Letter Queue (DLQ)
- Retry automático com backoff
- Dashboard em tempo real
- Autenticação JWT
- Deploy em nuvem
- Monitoramento com Prometheus + Grafana

---

# 🏁 Conclusão

O Sistema de Processamento de Alarmes atingiu seu objetivo ao implementar uma arquitetura **assíncrona, resiliente e escalável**, utilizando **Java**, Spring Boot, RabbitMQ e PostgreSQL.

A solução é adequada para ambientes corporativos que exigem **alta disponibilidade**, **tolerância a falhas** e **processamento contínuo de eventos**.
