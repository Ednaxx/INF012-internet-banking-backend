# Internet Banking API Gateway

Serviço Spring Cloud Gateway que fornece um ponto de entrada único para o ecossistema de microsserviços do Internet Banking e gerencia o roteamento.

## Funcionalidades

- **Roteamento de Serviços**: Roteamento inteligente para microsserviços backend
- **Monitoramento de Saúde**: Verificações de saúde do gateway e endpoints de monitoramento
- **Reescrita de Caminhos**: Transformação de caminhos de URL para endpoints de API limpos

## Rotas de Serviços

- `/api/**` → `internet-banking-api` (API Principal do Banking)
- `/mail/**` → `internet-banking-mail-service` (Serviço de Email)
- `/gateway/**` → Informações de saúde e rotas do gateway

## Variáveis de Ambiente

### Perfil de Produção

- `GATEWAY_PORT` - Porta do servidor gateway (padrão: 8080)
- `EUREKA_SERVER_ADDRESS` - Host do servidor Eureka
- `EUREKA_SERVER_PORT` - Porta do servidor Eureka

### Perfil de Desenvolvimento (`dev`)

Usa valores codificados para desenvolvimento local:

- Porta do gateway: `8080`
- Eureka: `localhost:8761`

## Configuração de Desenvolvimento Local

### Pré-requisitos

- Java 21+
- Maven 3.6+
- Eureka Server em execução
- Serviços backend (API Principal, Serviço de Email) em execução

### Executando o Serviço

1. **Usando Maven (Perfil de Desenvolvimento)**:

```bash
cd internet-banking-api-gateway
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Endpoints do Gateway

- **Informações do Gateway**: <http://localhost:8080/gateway/routes>
- **Verificação de Saúde**: <http://localhost:8080/actuator/health>
