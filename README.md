<h1 align="center">
  📦 Inventário API
</h1>

<p align="center">
  <strong>Back-end para gerenciamento de categorias e itens de inventário</strong>
  <br>
  Desenvolvido com Spring Boot e Java 21+, seguindo boas práticas REST e validações.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21%2B-blue?logo=java" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2-green?logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Maven-3.9-red?logo=apache-maven" alt="Maven">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

---

## ✨ Funcionalidades

- **Categorias** – Criação, listagem, busca, atualização e remoção de categorias.
- **Itens** – Criação, listagem, busca, atualização e remoção de itens, com vínculo obrigatório a uma categoria.
- **Filtro por categoria** – Listar itens de uma categoria específica.
- **Validações** – Campos obrigatórios, categoria existente, erro ao vincular item a categoria inexistente.
- **Tratamento de erros** – Retorno de códigos HTTP adequados (200, 201, 204, 400, 404).

---

## 🧱 Modelo de Dados

Um item pertence a apenas uma categoria (obrigatório)
