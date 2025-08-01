# Internet Banking Eureka Server

Servidor de descoberta de serviços Netflix Eureka que permite aos microsserviços se registrarem e descobrirem outros serviços no ecossistema do Internet Banking.

## Funcionalidades

- **Descoberta de Serviços**: Registro central para todos os microsserviços
- **Monitoramento de Saúde**: Rastreia a saúde e disponibilidade dos serviços
- **Balanceamento de Carga**: Habilita balanceamento de carga do lado do cliente
- **Gerenciamento de Serviços**: Registro e cancelamento automático de serviços
- **Dashboard Web**: Interface web integrada para monitoramento de serviços

## Variáveis de Ambiente

### Perfil de Produção

- `EUREKA_SERVER_PORT` - Porta do servidor Eureka

### Perfil de Desenvolvimento (`dev`)

Usa valores codificados para desenvolvimento local:

- Porta do servidor: `8761`

## Configuração de Desenvolvimento Local

### Pré-requisitos

- Java 21+
- Maven 3.6+

### Executando o Serviço

1. **Usando Maven (Perfil de Desenvolvimento)**:

```bash
cd internet-banking-eureka-server
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Dashboard do Eureka

Acesse o dashboard web do Eureka para monitorar os serviços registrados:

- **Desenvolvimento**: <http://localhost:8761>
- **Produção**: <http://localhost:{EUREKA_SERVER_PORT}>
