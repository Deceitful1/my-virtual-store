# 🛒 My Virtual Store

Uma API de e-commerce desenvolvida com **Java + Spring Boot**, com foco em simular o funcionamento de uma loja virtual completa, incluindo gerenciamento de produtos, usuários e regras de negócio.

🔗 Repositório:  
https://github.com/Deceitful1/my-virtual-store

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 📌 Sobre o projeto

O **My Virtual Store** é uma aplicação backend que representa o núcleo de uma loja virtual, utilizando arquitetura em camadas bem definida.

O projeto foi desenvolvido com o objetivo de praticar:

- Estruturação de APIs REST
- Boas práticas com Spring Boot
- Separação de responsabilidades (Controller, Service, Repository)
- Segurança e tratamento de exceções
- Persistência de dados com migrações

---

## 🛠️ Tecnologias utilizadas

- ☕ Java  
- 🌱 Spring Boot  
- 🔐 Spring Security  
- 🗄️ JPA / Hibernate  
- 🧪 JUnit (testes)  
- 🛢️ Banco de dados (H2 / MySQL — ajustar se necessário)  
- 📦 Maven  

---

## 📂 Estrutura do projeto

```bash
src/
 ├── main/
 │   ├── java/com/gablins/
 │   │   ├── configuration/     # Configurações gerais da aplicação
 │   │   ├── exceptions/        # Tratamento de erros
 │   │   ├── security/          # Configuração de segurança (auth, filtros, etc)
 │   │   ├── virtual_store/
 │   │   │   ├── controllers/   # Camada de entrada (REST)
 │   │   │   ├── entities/      # Entidades do banco de dados
 │   │   │   ├── repositories/  # Acesso aos dados (JPA)
 │   │   │   └── services/      # Regras de negócio
 │   │   └── VO/                # Value Objects / DTOs
 │   └── resources/
 │       ├── db/migration/      # Scripts de migração (Flyway ou similar)
 │       ├── static/            # Arquivos estáticos
 │       └── templates/         # Templates (caso use)
 │
 └── test/
     └── java/com/gablins/virtual_store/repositories  # Testes
