
# TODO-CLI

Gerenciador de tarefas via terminal desenvolvido em Java. A aplicação é leve e permite gerir as suas tarefas (criar, editar, listar e remover) diretamente da linha de comando. Utiliza SQLite como banco de dados local e JDBC para persistência, dispensando instalação de servidores ou configurações complexas.


## Aprendizado

Este projeto aplica conceitos fundamentais de desenvolvimento de software, incluindo: programação orientada a objetos, organização em camadas, separação de responsabilidades, modelagem de banco de dados relacional, scripts SQL e implementação completa de operações CRUD.


## Tecnologias utilizadas
* **Java**
* **JDBC SQLite**
* **SQLite**
## Instalação
1. Tenha instalado Java 21 ou superior

2. Clone este repositório
```bash
git clone https://github.com/LinsVitor/to-do-cli.git
```

3. Altere o terminal para pasta do projeto
```bash
cd to-do-cli
```

4. Compile o projeto
```bash
./compile.bat
```


## Usando
Após a instalação no terminal do diretório use o seguinte comando:
```bash
./to-do create "Linguagem a aprender" "aprender C"
## Saída: Task created successfully (ID: 1)
```
Utilize o comando **help** para ver a lista de comandos:
```bash
./to-do help
## Saída: Tabela de comandos com exemplos de uso
```
Por padrão o arquivo to-do.bat funciona apenas no terminal na pasta do diretório, mas caso queira usar em qualquer terminal basta adicionar a pasta do projeto na variável Path do sistema, caso queira fazer segue um [tutorial](https://www.wikihow.com/Change-the-PATH-Environment-Variable-on-Windows).
## Funcionalidades

- Criar uma tarefa.
```
./to-do create "Linguagem a aprender" "aprender C"
## Saída: Task created successfully (ID: 1)
```
- Buscar por uma tarefa.
```
./to-do find 1
## Saída:
----------------------------------------
 ID: 1
 Title: Linguagem a aprender
 Description: aprender C
 Status: TODO
 Created at: 01/01/2026 00:00
 Updated at: N/A
 ----------------------------------------
```
- Buscar todas tarefas.
```
./to-do list
## Saída:
┌──────┬────────────────────────────────┬──────────────┐
│ ID   │ Title                          │ Status       │
├──────┼────────────────────────────────┼──────────────┤
│ 1    │ Linguagem a aprender           │ TODO         │
└──────┴────────────────────────────────┴──────────────┘
```
- Remover uma tarefa.
```
./to-do delete 1
```
- Atualizar uma tarefa.
```
./to-do update 1 "Linguagem a aprender" "aprender assembly"
```
- Marca o estado atual de uma tarefa.
```
./to-do mark 1 "done"
```
- Buscar tarefas que contém qualquer palavra no título.
```
./to-do search "aprender"
## Saída:
┌──────┬────────────────────────────────┬──────────────┐
│ ID   │ Title                          │ Status       │
├──────┼────────────────────────────────┼──────────────┤
│ 1    │ Linguagem a aprender           │ TODO         │
└──────┴────────────────────────────────┴──────────────┘
```
- Comando de ajuda.

```
./to-do help
## Saída:
+----------+----------------------------------+--------------------------------------------+
| Command  | Description                      | Example                                    |
+----------+----------------------------------+--------------------------------------------+
| create   | Create a new task                | to-do create "Market List" "Buy groceries" |
| update   | Update an existing task by ID    | to-do update 1 "To learn" "Learn C"        |
| delete   | Remove a task                    | to-do delete 1                             |
| search   | Search a task by keyword         | to-do search "learn"                       |
| find     | Find a task by ID                | to-do find 1                               |
| list     | List all tasks                   | to-do list                                 |
| list     | List tasks by status             | to-do list "to do"                         |
| mark     | Mark the status of a task        | to-do mark 1 "done"                        |
+----------+----------------------------------+--------------------------------------------+
```
## Estrutura do Projeto
```
todocli/
├── cli/
│   ├── commands/
│   │   ├── CreateCommand.java
│   │   ├── DeleteCommand.java
│   │   ├── FindCommand.java
│   │   ├── HelpCommand.java
│   │   ├── ListCommand.java
│   │   ├── MarkCommand.java
│   │   ├── SearchCommand.java
│   │   └── UpdateCommand.java
│   ├── CliConsole.java
│   ├── Command.java
│   ├── CommandExecutor.java
│   └── CommandParser.java
├── db/
│   └── ConnectionFactory.java
├── exception/
│   ├── DbException.java
│   ├── InvalidArgumentException.java
│   └── ServiceException.java
├── model/
│   ├── Status.java
│   └── Task.java
├── repository/
│   ├── SQLiteRepository.java
│   └── TaskRepository.java
├── service/
│   └── TaskService.java
├── util/
│   ├── Color.java
│   └── ErrorMessage.java
└── Main.java
```