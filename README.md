# INF012 Internet Banking Backend

Projeto backend de Internet Banking desenvolvido com arquitetura de microsserviços utilizando Netflix Eureka. Desenvolvido para a disciplina de Programação Web no Instituto Federal da Bahia (IFBA).

## Visão Geral da Arquitetura

Este projeto implementa um sistema simples de internet banking usando arquitetura de microsserviços Spring Boot com os seguintes componentes:

- **API Gateway**: Ponto de entrada e roteamento de requisições
- **Eureka Server**: Descoberta e registro de serviços
- **API Principal do Banking**: Operações bancárias e autenticação principais
- **Serviço de Email**: Serviço de notificação por email

## Microsserviços

### 🌐 [Internet Banking API Gateway](./internet-banking-api-gateway/README.md)
- **Porta**: 8080
- **Propósito**: Ponto de entrada único, roteamento e balanceamento de carga
- **Rotas**: `/api/**` → API Principal, `/mail/**` → Serviço de Email

### 🔍 [Internet Banking Eureka Server](./internet-banking-eureka-server/README.md)
- **Porta**: 8761
- **Propósito**: Descoberta de serviços e monitoramento de saúde (health check)
- **Dashboard**: <http://localhost:8761>

### 🏦 [Internet Banking Main API](./internet-banking-main-api/README.md)
- **Porta**: 8081
- **Propósito**: Operações bancárias principais, gerenciamento de usuários, autenticação
- **Funcionalidades**: Autenticação JWT, operações de conta, histórico de transações
- **Documentação da API**: <http://localhost:8081/swagger-ui.html>

### 📧 [Internet Banking Mail Service](./internet-banking-mail-service/README.md)
- **Porta**: 8082
- **Propósito**: Notificações por email para operações bancárias
- **Funcionalidades**: Envio de email SMTP, notificações de transações

## Início Rápido

### Pré-requisitos

- Java 21+
- Maven 3.6+
- PostgreSQL 13+
- Docker & Docker Compose (opcional)

### Configuração de Desenvolvimento

1. **Clone o repositório**:
```bash
git clone https://github.com/Ednaxx/INF012-internet-banking-backend.git
cd INF012-internet-banking-backend
```

2. **Configure o banco de dados PostgreSQL com docker compose**:
```bash
docker compose up -d
```

3. **Configure o serviço de email** (necessário para notificações):
```bash
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-de-app
```

4. **Inicie os serviços em ordem**:

```bash
# 1. Inicie o Eureka Server
cd internet-banking-eureka-server
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev &

# 2. Inicie o Serviço de Email
cd ../internet-banking-mail-service
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev &

# 3. Inicie a API Principal
cd ../internet-banking-main-api
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev &

# 4. Inicie o API Gateway
cd ../internet-banking-api-gateway
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev &
```

## Funcionalidades

- ✅ Registro e autenticação de usuários (JWT)
- ✅ Consulta de saldo da conta
- ✅ Depósitos e saques
- ✅ Pagamentos entre contas
- ✅ Histórico de transações
- ✅ Notificações por email
- ✅ Descoberta de serviços e balanceamento de carga
- ✅ Documentação da API (Swagger/OpenAPI)
- ✅ Monitoramento de saúde

## Tecnologias

- **Spring Boot 3.5.3** - Framework de aplicação
- **Spring Cloud 2025.0.0** - Ferramentas de microsserviços
- **Spring Security** - Autenticação e autorização
- **JWT** - Autenticação baseada em token
- **Netflix Eureka** - Descoberta de serviços
- **Spring Cloud Gateway** - Gateway de API
- **PostgreSQL** - Banco de dados
- **Spring Data JPA** - Persistência de dados
- **Spring Mail** - Envio de email
- **OpenFeign** - Comunicação entre serviços
- **Swagger/OpenAPI** - Documentação da API
- **Maven** - Ferramenta de build

## Licença

GNU General Public License v3.0
