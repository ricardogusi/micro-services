# Microsserviços de Email e Usuário

Sistema de microsserviços desenvolvido com Spring Boot para demonstrar uma arquitetura moderna e escalável de gestão de usuários com envio de emails transacionais.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5.5**
* **Spring Data JPA**
* **RabbitMQ** - Mensageria para comunicação assíncrona
* **SQL Server** - Banco de dados relacional
* **JUnit 5 & Mockito** - Testes unitários
* **Jakarta Mail** - Envio de emails
* **Gradle** - Gerenciamento de dependências

## 🏗️ Arquitetura

O projeto é composto por dois microsserviços independentes:

### 1. User Service
* Responsável pelo cadastro e gestão de usuários
* Implementa padrão Publisher para notificações assíncronas
* Utiliza transações para garantir consistência dos dados

### 2. Email Service
* Serviço de envio de emails transacionais
* Consumer das mensagens do RabbitMQ
* Implementa retry pattern para garantir entrega
* Rastreamento de status de envio

## 🔍 Principais Features

* Cadastro de usuários com validações
* Envio assíncrono de emails
* Comunicação entre serviços via mensageria
* Tratamento de falhas e retentativas
* Monitoramento de status de envio de emails
* Cobertura de testes unitários

## 🚀 Como Executar

1. Clone o repositório
2. Configure as variáveis de ambiente (exemplos em `.env.example`)
3. Certifique-se que o RabbitMQ está em execução
4. Execute cada microsserviço: