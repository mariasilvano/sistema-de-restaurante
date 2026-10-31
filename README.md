# Sistema de Restaurante

Projeto 1 do bootcamp #ElasTech — sistema de console em Java para um restaurante: cardápio, pedidos, personalização dos lanches e fechamento do dia.

**Vídeo de demonstração:** [COLE AQUI O LINK]

## O que foi feito

### Nível 1 — O restaurante abre
- Cadastro de pedidos com número gerado automaticamente pelo `GerenciadorPedidos`.
- Listagem dos pedidos do dia e fechamento com o total vendido.
- Menu no `MenuConsole`; o `Main` só controla o fluxo.

### Nível 2 — O cardápio ganha vida
- Interface `ItemCardapio`, implementada por `Lanche` e pelos adicionais.
- `LancheFactory` cria uma instância nova de lanche a cada chamada.
- Adicionais com **Decorator**: `ComCheddar` (+ R$ 4,00) e `ComBaconExtra` (+ R$ 6,00).
- Testes com JUnit 5.

### Nível 3 — O restaurante fica profissional
- Retirada de ingredientes (queijo, alface, tomate, cebola) sem alterar o preço.
- Modificação de pedidos: adicionar item, remover item por número e cancelar o pedido.
- Fechamento mostra as combinações diferentes usando `HashSet<String>`.
- Entrada inválida (letras, opções inexistentes, nome vazio) não derruba o sistema.

### Bônus
- Gorjeta registrada a cada pedido, com relatório no fechamento.
- Lanches base diferentes no fechamento (`getNomeBase()` delegado pelo Decorator).
- `Caixa` (Singleton) e `Transacao` (com `equals`/`hashCode`) no pacote `financeiro`; opção "Pagar pedido" no menu.
- Três testes extras: descrições repetidas, fechamento e cancelamento.

## Organização dos pacotes

| Pacote | Responsabilidade |
|---|---|
| `app` | `Main`, `MenuConsole` (interação com o usuário) e `RestauranteFacade` (coordena as operações) |
| `cardapio` | `Lanche`, `ItemCardapio`, `LancheFactory` e os adicionais (Decorator) |
| `pedido` | `Pedido` (uma comanda) e `GerenciadorPedidos` (todos os pedidos) |
| `financeiro` | `Caixa` e `Transacao` |

O menu conversa com a pessoa, a fachada coordena e as classes de negócio guardam os dados.

## Padrões de projeto
- **Factory** (`LancheFactory`): o restante do sistema não sabe como um lanche é construído.
- **Decorator** (`ItemCardapioDecorator`, `ComCheddar`, `ComBaconExtra`): adiciona preço e descrição sem alterar `Lanche`.
- **Facade** (`RestauranteFacade`): o `Main` fala só com a fachada.
- **Singleton** (`Caixa`): o restaurante tem um único caixa.

## Como executar
Requisitos: Java 27 e Maven.

```bash
mvn test
```

Para rodar o sistema, execute a classe `org.example.app.Main` pela IDE.

## Ordem dos adicionais
O menu guarda só quais adicionais foram escolhidos, e a `RestauranteFacade` sempre os aplica na mesma ordem (cheddar, depois bacon extra). Por isso a descrição é sempre `X-Bacon + cheddar + bacon extra`, e o `HashSet` não conta combinações repetidas por causa da ordem.

A limitação continua nas classes: se os decorators forem montados manualmente em outra ordem, as descrições diferem e o `HashSet` conta duas combinações. Uma solução seria ordenar os adicionais antes de montar a descrição, ou compará-los como um conjunto.

## Outra limitação
Se um pedido já pago for alterado, a transação no caixa mantém o valor antigo.

## Autora
Maria Gabriela Silvano
