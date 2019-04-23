# Sincronização de Relógios com o algoritmo de Berkeley e Java RMI
Trabalho Prático da disciplina de Sistemas Distribuídos - FURB
### Especificação:
O algoritmo de Berkeley é o método que faz o relógio distribuir e sincronizar computadores. Ele faz uma consulta em cada computador e verifica os valores dos relógios. Efetua uma média dos dados coletados e informa a cada máquina para que se ajuste. Atrasando ou adiantando.

##### Passos do funcionamento de Berkeley:
  1) Servidor solicita a hora dos clientes;
  2) Cada cliente responde ao servidor informando qual é a diferença de tempo em relação a ele;
  3) O servidor efetua a média dos tempos (incluindo a leitura dele);
  4) O servidor encaminha o ajuste necessário a ser feito pelo cliente (média + inversão da diferença de tempo enviada no passo 2);
  5) Cliente realiza o ajuste.
  
##### A implementação:
  1. tem 1 cliente;
  2. têm no mínimo 2 servidores em máquinas distintas a do cliente;
  3. utiliza Java RMI para realizar a comunicação entre objetos;
  4. implementa o algoritmo de Berkeley propriamente dito.
***    
### Execução:
1) Verificar se a variável de ambiente CLASSPATH está estabelecida com o valor: 
```bash
.;%JAVA_HOME%\lib
```
2) Informar o ip dos dois servidores na classe MainClient, conforme exemplo:
```java
connections.add(new Connection("192.168.208.1", 4500));
connections.add(new Connection("192.175.209.1", 4500));
```
3) Iniciar o RMI Registry, através do prompt de comandos: 
```bash
start rmiregistry
```
4) Executar a classe MainServer, através do prompt de comandos, a partir do jar gerado (Server.jar):
```bash
cd <Diretório>\algoritmo-berkeley\src\br\furb\clock\server
java -jar Server.jar
```
5) A classe MainClient pode ser executada através de uma IDE, ou a partir do jar gerado (Client.jar), conforme exemplo anterior.

***    
### Exemplo de Saída:
Server:
```console
Server time: 2019-04-20T21:34
Server started
Server time: 2019-04-20T11:07
Server started
Updated time to: 2019-04-20T11:05:20
Updated time to: 2019-04-20T11:05:20
```
Client:
```console
Server time 192.168.208.1: 2019-04-20T21:34
Server time 192.175.209.1: 2019-04-20T11:07
Client time: 2019-04-20T08:29
Result Berkeley: 2019-04-20T11:05:20
Server time 192.168.208.1: 2019-04-20T11:05:20
Server time 192.175.209.1: 2019-04-20T11:05:20 
Client time: 2019-04-20T11:05:20
```
