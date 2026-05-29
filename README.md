# 🐾 PetAgende - Sistema de Gestão para Petshops

**Trabalho da II Unidade - Disciplina: Engenharia de Requisitos** **Projeto:** Desenvolvimento de um sistema standalone web para gestão de agendamentos e controle operacional de Petshops, aplicando elicitação de requisitos e metodologias ágeis.

---

## 🎯 Escopo e Requisitos Entregues

O sistema foi modelado para isolar as responsabilidades entre dois atores principais: **Atendente** (gestão de agenda e clientes) e **Operador Técnico** (execução de serviços). 

As seguintes funcionalidades foram implementadas com total fidelidade à documentação de requisitos:

### Requisitos Funcionais de Base (UC01 e UC03)
- **Gestão de Cadastros:** Formulário restrito para cadastro de Tutores, Pets e anotações médicas (alergias).
- **Consulta de Agenda:** Painel exclusivo para os profissionais técnicos visualizarem a carga de trabalho do dia.

### ⭐ Requisito Funcional Primário (UC02 - Prevenção de Conflitos)
- **Regra de Negócio:** O sistema impede ativamente o agendamento de dois animais para o mesmo profissional técnico no exato mesmo horário.
- **Implementação:** Validação arquitetada na camada `Service` (Spring Boot), interceptando e bloqueando a transação antes da persistência no banco, retornando alerta visual na interface (Erro 400).

### ⭐ Requisito Funcional Secundário (UC04 - Notificação)
- **Regra de Negócio:** Ao concluir um serviço, o tutor deve ser notificado.
- **Implementação:** Botão de conclusão na interface do técnico que realiza uma requisição `PATCH`, atualiza o status via JPA e simula o disparo de integração via rotina no Back-end.

---

## 🛠️ Stack Tecnológica

O projeto foi construído seguindo o padrão MVC e as melhores práticas de Engenharia de Software:

* **Back-end:** Java 21 + Spring Boot (Web, Data JPA).
* **Banco de Dados:** PostgreSQL (Relacional) persistindo dados das entidades `Pet`, `OperadorTecnico` e `Agendamento`.
* **Segurança:** Spring Security (In-Memory Authentication) com controle de perfis (`ROLE_ATENDENTE` e `ROLE_TECNICO`) e proteção de rotas.
* **Front-end:** HTML5, JavaScript (Fetch API para consumo REST) e TailwindCSS para responsividade.

---

## 🚀 Como Executar o Projeto

1. Clone este repositório em seu terminal:
   ```bash
   git clone [https://github.com/Agab01/petagende.git](https://github.com/Agab01/petagende.git)

2. Configure o banco de dados PostgreSQL:

    Crie um banco local chamado petagende_db.

    Verifique e atualize as credenciais (username e password) no arquivo src/main/resources/application.properties.

    Carga Inicial: Execute o script abaixo na ferramenta de query do banco para cadastrar os técnicos iniciais:


        INSERT INTO tb_tecnico (nome, especialidade) VALUES ('João Tosador', 'Banho e Tosa');
        INSERT INTO tb_tecnico (nome, especialidade) VALUES ('Maria Veterinária', 'Clínica Geral');

3. Execute a classe principal PetagendeApplication.java na sua IDE (Eclipse/IntelliJ).

4. Acesse o sistema no navegador através da URL: http://localhost:8080/login.html

    Acesso Atendente: admin / 1234

    Acesso Técnico: tecnico / 1234

Autor
Arthur Gabriel Palmeira Teixeira