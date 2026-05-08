# SmartLab Project

Objetivo dos artefatos: criar um ambiente que gerencie uma SmartLab desde o código do ESP32 até o código para a transferencia de mensagens entre o protocolo MQTT e o sistema de mensageria Kafka e por fim apresentar uma interface para interação com o usuário.

Título do Projeto: On the Scale Transition of Event-Driven IoT Architectures: An Experimental Evaluation

Resumo do Projeto: Event-driven Internet of Things (IoT) architectures are widely adopted due to their flexibility and decoupling properties, yet their behavior during scale transitions remains insufficiently understood. In particular, how performance and reliability evolve when systems move from moderate to high workload regimes is often underexplored. This paper presents an experimental evaluation of an event-driven IoT architecture focusing on the transition from medium- to large-scale operation. The proposed architecture combines MQTT and Apache Kafka to decouple data ingestion from processing and is evaluated using a reproducible testbed with synthetic workloads under constant and exponential load patterns. Experimental results show that HTTP latency remains stable across all scenarios, while message acceptance rates reveal clear throughput saturation points under extreme load. These findings demonstrate that scale transition manifests primarily through reliability degradation rather than latency increase, highlighting the importance of multi-dimensional evaluation when assessing scalability in IoT systems.

# Estrutura do readme.md

O projeto é dividido em uma hierarquia de pastas conforme descrito abaixo:

- Applications: Aplicação front-end e back-end para controlar o cadastro e a circulação de dados no kafka.
- Infrastructure: dados para configurar a infraestrutura (banco de dados e sistema de mensageria)
- k6_scripts: Arquivos para os testes que utilizaram a biblioteca k6
- lcs-arduino-lib: Biblioteca para o ESP32 responsável por enviar os dados dos sensores para o MQTT.

Os arquivos individuais servem para a configuração e execução da aplicação.

# Selos Considerados

Os selos considerados são: Disponíveis (SeloD) e Funcionais (SeloF).

# Informações básicas

Esta seção deve apresentar informações básicas de todos os componentes necessários para a execução e replicação dos experimentos.
Descrevendo todo o ambiente de execução, com requisitos de hardware e software.

Para a execução do front-end (./applications/management_app) é necessário o seguinte componente instalado:
- Node.js 16.14 (https://nodejs.org/en/)

Para a execução do back-end (./applications/management_api, ./applications/kafka_mqtt_bridge, ./applications/mqtt_kafka_bridge) são necessários os seguintes componentes instalados:

- JAVA 11
- Maven 4.0.0
- PostgreSQL 12

---

**Caso você queira executar a aplicação com o uso de Docker, será necessário instalar qualquer versão do Docker**

---

# Dependências

A máquina precisará das seguintes aplicações para fazer o uso completo da aplicação:

- ESP32 com algum sensor ou atuador (infravermelho capaz de enviar sinal)
- O Arduino IDE instalado no computador com a extensão para compilar código para o ESP32.
- O Docker instalado na máquina para rodar as aplicações front e back-end.
- Uma IDE para edição de código Typescrypt e Java.

# Preocupações com segurança

A aplicação executa apenas as instâncias da aplicação. Se for executada no docker (recomendado para analise) a aplicação não apresentará nenhum problema de segurança para os revisores.
Caso ocorra um erro na aplicação basta reiniciar o Docker.

# Instalação

## Aplicação

Para executar a aplicação no ambiente de testes é necessário ter o Docker instalar e o docker-compose funcionando. Com esses dois programas instalados, execute o script: "smartlab.run.sh", esse script vai carregar a aplicação em containers do Docker.

As aplicacoes serão executadas nas seguintes portas:
- Management Dashboard (API): 8010
- Management Dashboard (APP): 4200
- MQTT-Kafka Bridge: 8012
- Stream Control Service (SCS): 8011

## ESP32

Para instalar o código da aplicação em um ESP32, é necessário ter o ESP32 e o respectivo sensor necessário para a leitura dos dados. Com ele em mãos abra o Arduino IDE e abre o código da pasta "lcs-arduino-lib".

Define os valores das constantes WIFI_USER, WIFI_PASS e UUID_DEVICE (código gerado para o dispositivo na aplicação) no arquivo airSmartLab.ino.

Define os valores das constantes _MQTT_BROKER_ADDRESS e _MQTT_BROKER_PORT no arquivo LSC.h, para os valores nos quais o broker MQTT está executando.

Após isso, basta carregar o código no ESP32 e ver se ele está publicando no tópico MQTT.

# Teste mínimo

## Aplicação

Executar o docker e verificar que todas as instâncias estão em execução. Caso estejam, acessar a porta 4200 para visualizar a interface da aplicação.

## ESP32

Após configurar o ESP32, verificar pelo DEBUG do arduino IDE se os dados estão sendo lidos e publicados nos tópicos do MQTT.

# LICENSE

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](https://github.com/cairomn/event-driven-architecture-jems3/LICENCE) para mais detalhes.
