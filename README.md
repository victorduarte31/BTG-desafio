# Desafio BTG

### Desenho de solução:
![](arquitetura.png)

### Banco de Dados:
![](banco.png)


### Tecnologias utilizadas:

 - JAVA 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
 - DOCKER (https://www.docker.com/products/docker-desktop/)
 - MONGODB (https://www.mongodb.com/)
 - RABBITMQ (https://www.rabbitmq.com/)



Atendendo os requisitos acima basta rodar o comando abaixo para subir as imagens no docker

### Habilitando plugin Shovel

- rabbitmq-plugins enable rabbitmq_shovel, **Executar dentro do terminal do rabbit**

#### Subindo os containers
 - docker-compose up -d

#### Parar container
 - docker-compose stop

#### Estrutura de mensagem aceita na requisição

```json
{
  "codigoPedido":1001,
  "codigoCliente":1,
  "itens":[
    {
      "produto":"lápis",
      "quantidade":100,
      "preco":1.10
    },
    {
      "produto":"caderno",
      "quantidade":10,
      "preco":1.00
    }
  ]
}

```

### Swagger

 - http://localhost:8080/swagger-ui/#/



### Futuras melhorias

- Realizar a quebra da aplicação em outros microservices ou criar libs para isolar as diferentes camadas permitindo assim uma melhor manuntenção
- Realização de testes de stress
- Utilização de BDD/TDD