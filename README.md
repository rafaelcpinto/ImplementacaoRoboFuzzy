# Implementação de Robô Fuzzy

## Objetivo

Este projeto simula um robô que procura uma porta em uma sala 1.000 x 1.000. O robô enxerga somente uma vizinhança local, usa lógica fuzzy para calcular o tamanho dos deslocamentos e desvia de barreiras buscando aberturas lateralmente.

## Representação do ambiente

A sala é representada por uma matriz de inteiros:

| Valor | Significado |
| --- | --- |
| `0` | Espaço livre |
| `1` | Obstáculo |
| `2` | Porta |

O robô começa em `(0, 0)`. A porta é criada em uma posição `X` aleatória da última linha (`Y = 999`). Os limites externos da matriz são apresentados ao sensor como obstáculos virtuais.

O ambiente gera 60 barreiras horizontais. Cada barreira:

- fica em uma faixa de linhas diferente;
- possui linha aleatória dentro da sua faixa;
- possui uma abertura horizontal aleatória;
- deixa 51 células livres, largura compatível com o alcance máximo do sensor.

## Arquitetura

As responsabilidades são separadas da seguinte forma:

- `Main`: cria os componentes, coordena cada passo e repassa os resultados.
- `Ambiente`: conhece a sala, a posição do robô e a porta; fornece a vizinhança local e aplica deslocamentos.
- `Sensor`: recebe somente a matriz da vizinhança; não conhece o ambiente nem a posição global do robô.
- `ControleFuzzy`: transforma uma distância normalizada em intensidade de movimento.
- `Movimento`: escolhe a direção, aplica a saída fuzzy ao deslocamento e memoriza a direção de busca lateral.
- `Interface`: imprime porta, barreiras encontradas, passos e resultado final.

Fluxo de um passo:

```text
Ambiente fornece a matriz local
          |
          v
Sensor produz LeituraSensor
          |
          v
Movimento usa ControleFuzzy
          |
          v
Movimento produz um Deslocamento
          |
          v
Ambiente aplica o deslocamento
          |
          v
Interface exibe o resultado do passo
```

## Sensor

O ambiente fornece uma vizinhança com alcance 50 ao redor do robô. O sensor mede quantas células livres existem nas quatro direções:

- direita;
- esquerda;
- abaixo;
- acima.

A porta (`2`) é tratada como caminho livre. Somente obstáculos (`1`) interrompem uma medição.

Quando existe uma barreira imediatamente abaixo, o sensor também varre a linha seguinte dentro da vizinhança e informa a distância das aberturas visíveis à esquerda e à direita. Ele continua sem conhecer a posição global ou a matriz completa da sala.

## Controle fuzzy

As distâncias são limitadas ao alcance do sensor e normalizadas para o intervalo `[0, 1]`:

```text
entrada = min(distanciaLivre / 50, 1)
```

### Fuzzyficação

`ControleFuzzy` calcula diretamente os graus de pertinência:

- `entrada1`: função triangular de ombro entre `0` e `0,50`, com pertinência máxima em `0`;
- `entrada2`: função triangular entre `0,25` e `0,75`;
- `entrada3`: função triangular de ombro entre `0,50` e `1`, com pertinência máxima em `1`.

### Inferência

As regras ficam no próprio `ControleFuzzy` e produzem quatro graus genéricos: `saida1`, `saida2`, `saida3` e `saida4`. As transições entre conjuntos adjacentes usam `min` e `max`.

### Centro de gravidade

Cada função de saída calcula seu centroide. `ControleFuzzy` calcula a velocidade final pela média ponderada dos quatro graus:

| Conjunto | Centro |
| --- | --- |
| Saída 1 | `0,0861` |
| Saída 2 | `0,2833` |
| Saída 3 | `0,5800` |
| Saída 4 | `0,8630` |

A saída fuzzy é multiplicada pelo alcance do sensor para definir o deslocamento em qualquer direção. O resultado é arredondado, tem o mínimo de uma célula quando há caminho livre e nunca ultrapassa a quantidade de células livres medida pelo sensor.

## Regras de movimento

1. Se existe caminho abaixo, o robô desce.
2. Se existe obstáculo imediatamente abaixo, o robô procura aberturas visíveis nos dois lados.
3. Se existe uma abertura visível, escolhe a mais próxima.
4. Em caso de empate ou quando nenhuma abertura está visível, usa a direção de busca armazenada.
5. Ao encontrar obstáculo no sentido lateral atual, o robô inverte a busca.
6. Se abaixo e os dois lados estão bloqueados, o robô tenta subir.
7. A direção de busca permanece armazenada mesmo enquanto o robô está descendo.

Quando um deslocamento longo atravessa a porta, o ambiente interrompe o movimento exatamente na posição dela. O console mostra o deslocamento realmente realizado, não apenas o valor inicialmente solicitado.

O laço permite até cinco vezes o tamanho padrão da sala, atualmente 5.000 passos.

## Saída no console

Exemplo:

```text
TRAJETO DO ROBO
Inicio: (0, 0)
Porta: (31, 49)
BARREIRA ENCONTRADA NO PASSO 5
  Posicao do robo: (0, 17)
  Sensor: abaixo=0, direita=10, esquerda=0, acima=10
  Aberturas visiveis: esquerda=nao encontrada, direita=9
  Direcao escolhida para buscar abertura: DIREITA
Passo 5 | deslocamento=(8, 0) | posicao=(8, 17) | busca=DIREITA | fuzzy=0.16
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
|   |   `-- Sala.java
|   |-- app
|   |   `-- Main.java
|   |-- controle
|   |   |-- Deslocamento.java
|   |   `-- Movimento.java
|   |-- fuzzy
|   |   |-- ControleFuzzy.java
|   |   `-- functions
|   |       |-- FuncaoPertinencia.java
|   |       |-- FuncaoTrapezoidal.java
|   |       `-- FuncaoTriangular.java
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

- JDK 21 ou superior
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
- varredura de aberturas visíveis nas barreiras;
- escolha e inversão da direção de movimento;
- funções de pertinência;
- validação, inferência e defuzzyficação do controlador fuzzy;
- cálculo dos centroides das funções de pertinência;
- detecção de chegada e passagem pela porta.
