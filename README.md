# Implementação de Robô Fuzzy

## Objetivo

Este projeto simula um robô que procura uma porta em uma sala 50 x 50. O robô enxerga somente uma vizinhança local, usa lógica fuzzy para calcular o tamanho dos deslocamentos e desvia de barreiras buscando aberturas lateralmente.

## Representação do ambiente

A sala é representada por uma matriz de inteiros:

| Valor | Significado |
| --- | --- |
| `0` | Espaço livre |
| `1` | Obstáculo |
| `2` | Porta |

O robô começa em `(0, 0)`. A porta é criada em uma posição `X` aleatória da última linha (`Y = 49`). Os limites externos da matriz são apresentados ao sensor como obstáculos virtuais.

O ambiente gera cinco barreiras horizontais. Cada barreira:

- fica em uma faixa de linhas diferente;
- possui linha aleatória dentro da sua faixa;
- possui uma abertura horizontal aleatória;
- deixa 11 células livres, largura compatível com o alcance máximo do sensor.

## Arquitetura

As responsabilidades são separadas da seguinte forma:

- `Main`: cria os componentes, coordena cada passo e repassa os resultados.
- `Ambiente`: conhece a sala, a posição do robô e a porta; fornece a vizinhança local e aplica deslocamentos.
- `Sensor`: recebe somente uma `Vizinhanca`; não conhece o ambiente nem a posição global do robô.
- `ControleFuzzy`: transforma uma distância normalizada em intensidade de movimento.
- `Movimento`: escolhe o eixo e o sentido do movimento; memoriza somente a última direção de busca lateral.
- `Interface`: imprime porta, barreiras encontradas, passos e resultado final.

Fluxo de um passo:

```text
Ambiente fornece Vizinhanca
          |
          v
Sensor produz LeituraSensor
          |
          v
Main calcula deslocamentos com ControleFuzzy
          |
          v
Movimento escolhe um Deslocamento
          |
          v
Ambiente aplica o deslocamento
          |
          v
Interface exibe o resultado do passo
```

## Sensor

O ambiente fornece uma vizinhança com alcance 10 ao redor do robô. O sensor mede quantas células livres existem nas quatro direções:

- direita;
- esquerda;
- abaixo;
- acima.

A porta (`2`) é tratada como caminho livre. Somente obstáculos (`1`) interrompem uma medição.

## Controle fuzzy

As distâncias são limitadas ao alcance do sensor e normalizadas para o intervalo `[0, 1]`:

```text
entrada = min(distanciaLivre / 10, 1)
```

### Fuzzyficação

`Fuzzyficacao` calcula os graus de pertinência:

- `perto`: função decrescente entre `0` e `0,50`;
- `medio`: função triangular entre `0,25` e `0,75`;
- `longe`: função crescente entre `0,50` e `1`.

### Inferência

`InferenciaFuzzy` valida os graus e atualmente transfere os graus de entrada para as saídas baixa, média e alta. A classe está preparada para receber uma base de regras mais elaborada futuramente.

### Centro de gravidade

`CalculaCentroDeGravidade` usa uma média ponderada com os centros:

| Conjunto | Centro |
| --- | --- |
| Perto | `0,1667` |
| Médio | `0,5000` |
| Longe | `0,8333` |

A intensidade resultante é multiplicada pela distância livre. O deslocamento final é arredondado, tem no mínimo uma célula quando existe caminho e nunca ultrapassa a distância medida pelo sensor.

## Regras de movimento

1. Se existe caminho abaixo, o robô desce.
2. Se existe obstáculo imediatamente abaixo, o robô procura uma abertura lateralmente.
3. A busca começa para a direita.
4. Ao encontrar obstáculo no sentido lateral atual, o robô inverte a busca.
5. Se abaixo e os dois lados estão bloqueados, o robô tenta subir.
6. A direção de busca permanece armazenada mesmo enquanto o robô está descendo.

Quando um deslocamento longo atravessa a porta, o ambiente interrompe o movimento exatamente na posição dela. O console mostra o deslocamento realmente realizado, não apenas o valor inicialmente solicitado.

O laço atual permite até 200 passos (`2 * MAXIMO_DE_PASSOS`). Depois do passo 100, o `Main` contém uma inversão experimental dos valores usados para cima e para baixo. Essa lógica não constitui ainda um modo completo de saída de becos sem saída.

## Saída no console

Exemplo:

```text
TRAJETO DO ROBO
Inicio: (0, 0)
Porta: (31, 49)
BARREIRA ENCONTRADA NO PASSO 5
  Posicao do robo: (0, 17)
  Sensor: abaixo=0, direita=10, esquerda=0, acima=10
  Direcao escolhida para buscar abertura: DIREITA
Passo 5 | deslocamento=(8, 0) | posicao=(8, 17) | busca=DIREITA
...
O ROBO CHEGOU NA PORTA.
posicaoFinalX: 31
posicaoFinalY: 49
```

Como a porta, as linhas das barreiras e suas aberturas são aleatórias, o trajeto muda a cada execução.

## Estrutura do projeto

```text
src
|-- main/java/implementacaorobofuzzy
|   |-- ambiente
|   |   |-- Ambiente.java
|   |   |-- ConsultaVizinhanca.java
|   |   |-- Posicao.java
|   |   |-- Sala.java
|   |   `-- Vizinhanca.java
|   |-- app
|   |   `-- Main.java
|   |-- controle
|   |   |-- Deslocamento.java
|   |   |-- DeslocamentosPossiveis.java
|   |   |-- Executa.java
|   |   `-- Movimento.java
|   |-- fuzzy
|   |   |-- CalculaCentroDeGravidade.java
|   |   |-- ControleFuzzy.java
|   |   |-- Defuzzyficacao.java
|   |   |-- Fuzzyficacao.java
|   |   `-- InferenciaFuzzy.java
|   |-- sensor
|   |   |-- LeituraSensor.java
|   |   `-- Sensor.java
|   `-- ui
|       `-- Interface.java
`-- test/java/implementacaorobofuzzy
    |-- ambiente
    |-- controle
    |-- fuzzy
    `-- sensor
```

## Requisitos

- JDK 8 ou superior
- Maven

## Execução

Executar diretamente pelo Maven:

```bash
mvn clean compile exec:java
```

Executar os testes:

```bash
mvn test
```

Gerar e executar o JAR:

```bash
mvn clean package
java -jar target/implementacao-robo-fuzzy-1.0.0.jar
```

## Testes

A suíte cobre:

- criação da porta e das barreiras;
- consulta da vizinhança nos limites da sala;
- leitura do sensor;
- escolha e inversão da direção de movimento;
- funções de pertinência;
- inferência fuzzy;
- cálculo do centro de gravidade;
- detecção de chegada e passagem pela porta.
