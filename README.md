## Programa de validação e tradução de expressões matemáticas

Este programa foi desenvolvido para a disciplina de Compiladores e tem como objetivo validar e traduzir expressões matemáticas nas sintaxes Infixa, Posfixa e Prefixa.

### Como executar

Para executar o programa, é necessário ter o Maven instalado. Em seguida, basta abrir um terminal na pasta do projeto e executar o seguinte comando:

```shell
mvn package
```

Isso irá gerar um arquivo `jar` na pasta `target`. Para executá-lo, basta rodar o seguinte comando:

```shell
java -jar target/nome_do_arquivo.jar
```

### Interface de usuário

O programa utiliza uma interface gráfica desenvolvida com a biblioteca Swing. A classe responsável por essa interface é `views.Form`. A interface consiste em três elementos principais:

- Seleção das sintaxes partida e destino
- Campo de entrada de texto para a expressão matemática
- Botão para validar a expressão e obter a tradução

### Funcionamento
O programa possui uma classe principal Main, que recebe como entrada uma expressão matemática em qualquer uma das notações suportadas. A partir disso, são realizadas as seguintes operações:

1. Validação da expressão: a expressão é verificada para garantir que possui uma sintaxe válida de acordo com a notação escolhida. Caso haja algum erro de sintaxe, o programa informa o erro.
2. Tradução da expressão: a expressão é traduzida para outra notação especificada. Ou seja, se a entrada for uma expressão em notação prefixa, a saída será a mesma expressão em notação posfixa ou infixa.
3. Exibição das expressões: as expressões resultantes da tradução são exibidas na tela.

### Bibliotecas utilizadas

Além da biblioteca padrão do Java, o programa utiliza a biblioteca Swing para construir a interface gráfica e a biblioteca JUnit para executar os testes automatizados.