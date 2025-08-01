# Internet Banking Mail Service

Microsserviço dedicado para gerenciar notificações por email no sistema Internet Banking. Fornece capacidades de envio de email SMTP para notificações de transações, mensagens de boas-vindas e outras comunicações bancárias.

## Funcionalidades

- **Envio de Email**: Entrega de email SMTP usando Spring Mail
- **API REST**: Endpoint REST simples para envio de emails
- **Validação**: Validação de entrada para requisições de email
- **Tratamento de Erros**: Tratamento robusto de erros e logging
- **Monitoramento de Saúde**: Endpoint de verificação de saúde para monitoramento do serviço

## Variáveis de Ambiente

### Perfil de Produção

- `MAIL_SERVER_PORT` - Porta do servidor do serviço de email
- `MAIL_HOST` - Host do servidor SMTP (padrão: smtp.gmail.com)
- `MAIL_PORT` - Porta do servidor SMTP (padrão: 587)
- `MAIL_USERNAME` - Nome de usuário para autenticação SMTP
- `MAIL_PASSWORD` - Senha para autenticação SMTP
- `EUREKA_SERVER_ADDRESS` - Host do servidor Eureka
- `EUREKA_SERVER_PORT` - Porta do servidor Eureka

### Perfil de Desenvolvimento (`dev`)

Usa principalmente valores codificados para desenvolvimento local:

- Porta do servidor: `8082`
- Host SMTP: `smtp.gmail.com`
- Porta SMTP: `587`
- Eureka: `localhost:8761`
- **Obrigatório**: Variáveis de ambiente `MAIL_USERNAME` e `MAIL_PASSWORD`

## Configuração de Desenvolvimento Local

### Pré-requisitos

- Java 21+
- Maven 3.6+
- Eureka Server em execução
- Credenciais SMTP válidas (Gmail recomendado para desenvolvimento)

### Configuração SMTP

Para SMTP do Gmail (Desenvolvimento):

1. Habilite a autenticação de 2 fatores na sua conta Gmail
2. Gere uma Senha de App para a aplicação
3. Configure as variáveis de ambiente:

```bash
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-de-app
```

### Executando o Serviço

1. **Usando Maven (Perfil de Desenvolvimento)**:

```bash
cd internet-banking-mail-service
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-de-app
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Endpoints da API

- **Enviar Email**: `POST /send`
- **Verificação de Saúde**: `GET /health`

### Uso da API

Envie um email via API REST:

```bash
curl -X POST http://localhost:8082/send \
  -H "Content-Type: application/json" \
  -d '{
    "to": "destinatario@exemplo.com",
    "subject": "Email de Teste",
    "body": "Este é um email de teste do Internet Banking"
  }'
```

### Regras de Validação

- `to`: Deve ser um endereço de email válido (obrigatório)
- `subject`: Não deve estar em branco (obrigatório)
- `body`: Não deve estar em branco (obrigatório)
