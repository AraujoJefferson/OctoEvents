# OctoEvent
O sistema é responsável por guardar todos os eventos relacionados as publicações de questões no repositório do GitHub,
se utilizando de WebHooks para receber tais eventos e expor para consumo externo, via API.

- [Pré-requisitos](#pré-requisitos)
- [Instruções](#instruções)
- [Integração](#integração)
- [Endpoints](#endpoints)


## Pré-requisitos
- Java 1.8.0_162
- Maven 3.3.9
- Intellij IDEA
- ngrok 2.2.8

## Instruções

Todos os comandos deverão ser executado via terminal.
Fazer o checkout do projeto via GitHub e na raiz do projeto executar o comando abaixo:

``` mvn clean install ```

Com o build realizado com sucesso, a aplicação pode ser executada através da linha de comando abaixo via terminal:

```java -jar target/OctoEvents-1.0-SNAPSHOT.jar ```

A partir de agora você poderá acessar os serviços pelos [endpoints](#endpoints).

## Integração

Para integração entre o Webhooks e a API é necessário configurar no repositório GitHub a URL da API, para obter a URL 
segue as instruções abaixo:

Após executar a aplicação de acordo com o passo acima, abrir um novo terminal e chamar o comando:

```sudo ngrok http 8080 ```

No terminal irá disponibilizar a URL a ser definida no repositório em Settings > Webhooks > add webhook:
- Em "Payload URL" colar a URL;
- Em "Content type" configurar "application/json".

A partir de agora a API captura todas as publicações no repositório.

## Endpoints
Endpoints necessário para consumir os serviços da aplicação:
- Criar Issues
	> **POST** http://localhost:8080/

    Segue abaixo, exemplo com os campos para realizar a chamada **JSON** via **POST**:

	```
    {
       "action":"opened",
       "issue":{
          "number":1347,
          "created_at":"2019-01-25T23:38:29Z"
       }
    }

    ```
- Filtrar Issues
	> **GET** http://localhost:8080/issues/***{issue}***/events
