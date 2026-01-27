
# TODO-CLI

Gerenciador de tarefas via terminal desenvolvido em Java. A aplicação é leve e permite gerenciar suas tarefas (criar, editar, listar e remover) diretamente da linha de comando. Utiliza SQLite como banco de dados local e JDBC para persistência, dispensando instalação de servidores ou configurações complexas.


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
git clone https://github.com/LinsVitor/todo-cli.git
```

3. Altere o terminal para pasta do projeto
```bash
cd todo-cli
```

4. Compile o projeto
```bash
./compile.bat
```


## Usando
Após a instalação no terminal do diretório use o seguinte comando:
```bash
./todo-cli create "Linguagem a aprender" "aprender C"
## Saída: Task created successfully (ID: 1)
```
Utilize o comando **help** para ver a lista de comandos:
```bash
./todo-cli help
## Saída: Tabela de comandos com exemplos de uso
```
Por padrão o arquivo todo-cli.bat funciona apenas no terminal na pasta do diretório, mas caso queira usar em qualquer terminal basta adicionar a pasta do projeto na variável Path do sistema, caso queira fazer segue um [tutorial](https://www.wikihow.com/Change-the-PATH-Environment-Variable-on-Windows).
## Funcionalidades

- Criar uma nova tarefa.
```
./todo-cli create "Linguagem a aprender" "aprender C"
## Saída: Task created successfully (ID: 1)
```
- Buscar por uma tarefa.
```
./todo-cli find 1
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
./todo-cli list
## Saída:
┌──────┬────────────────────────────────┬──────────────┐
│ ID   │ Title                          │ Status       │
├──────┼────────────────────────────────┼──────────────┤
│ 1    │ Linguagem a aprender           │ TODO         │
└──────┴────────────────────────────────┴──────────────┘
```
- Remover uma tarefa.
```
./todo-cli delete 1
```
- Atualizar uma tarefa.
```
./todo-cli update 1 "Linguagem a aprender" "aprender assembly"
```
- Marca o estado atual de uma tarefa.
```
./todo-cli mark 1 "done"
```
- Buscar tarefas que contém qualquer palavra no título.
```
./todo-cli search "aprender"
## Saída:
┌──────┬────────────────────────────────┬──────────────┐
│ ID   │ Title                          │ Status       │
├──────┼────────────────────────────────┼──────────────┤
│ 1    │ Linguagem a aprender           │ TODO         │
└──────┴────────────────────────────────┴──────────────┘
```
- Comando de ajuda.

```
./todo-cli help
## Saída:
+----------+----------------------------------+-----------------------------------------------+
| Command  | Description                      | Example                                       |
+----------+----------------------------------+-----------------------------------------------+
| create   | Create a new task                | todo-cli create "Market List" "Buy groceries" |
| update   | Update an existing task by ID    | todo-cli update 1 "To learn" "Learn C"        |
| delete   | Remove a task                    | todo-cli delete 1                             |
| search   | Search a task by keyword         | todo-cli find 1                               |
| find     | Find a task by ID                | todo-cli find 1                               |
| list     | List all tasks                   | todo-cli list                                 |
| list     | List tasks by status             | todo-cli list "todo"                          |
| mark     | Mark the status of a task        | todo-cli mark 1 "done"                        |
+----------+----------------------------------+-----------------------------------------------+
```
## Estrutura do Projeto
```
todocli/
├── cli/
│   ├── CliConsole.java
│   └── CommandParser.java
├── db/
│   ├── ConnectionFactory.java
│   └── DbException.java
├── model/
│   ├── Status.java
│   └── Task.java
├── repository/
│   ├── SQLiteRepository.java
│   └── TaskRepository.java
├── service/
│   ├── ServiceException.java
│   └── TaskService.java
├── util/
│   └── Color.java
└── Main.java
```