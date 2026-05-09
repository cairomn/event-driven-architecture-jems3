# SmartLab Project

Objetivo dos artefatos: criar um ambiente que gerencie uma SmartLab desde o código do ESP32 até o código para a transferencia de mensagens entre o protocolo MQTT e o sistema de mensageria Kafka e por fim apresentar uma interface para interação com o usuário.

Título do Projeto: On the Scale Transition of Event-Driven IoT Architectures: An Experimental Evaluation

Resumo do Projeto: Event-driven Internet of Things (IoT) architectures are widely adopted due to their flexibility and decoupling properties, yet their behavior during scale transitions remains insufficiently understood. In particular, how performance and reliability evolve when systems move from moderate to high workload regimes is often underexplored. This paper presents an experimental evaluation of an event-driven IoT architecture focusing on the transition from medium- to large-scale operation. The proposed architecture combines MQTT and Apache Kafka to decouple data ingestion from processing and is evaluated using a reproducible testbed with synthetic workloads under constant and exponential load patterns. Experimental results show that HTTP latency remains stable across all scenarios, while message acceptance rates reveal clear throughput saturation points under extreme load. These findings demonstrate that scale transition manifests primarily through reliability degradation rather than latency increase, highlighting the importance of multi-dimensional evaluation when assessing scalability in IoT systems.

## Estrutura do readme.md

Esse README.md está organizado da seguinte forma:

- Estrutura do projeto: apresenta a estrutura de pastas desse projeto, detalhando o que está em cada uma das pastas.
- Selos Considerados: detalha os selos considerados para o SBRC'26.
- Dependências: lista as dependências do projeto.
- Preocupações com segurança:
- Instalação: detalha como a aplicação deve ser instalada e qual o passo a passo para executar corretamente.
- Teste mínimo: detalha um teste mínimo para verificar se a aplicação está funcionando normalmente.
- Licença: Detalha qual a licença de distribuição da aplicação.

## Estrutura do projeto

```[text]
/
├── applications/                   # Diretório o código da aplicação front-end e back-end
    ├── kafka-mqtt-bridge/          # Diretório com o código da bridge entre o Kafka e o MQTT
    ├── management_api/             # Diretório com o código da API de backend que gerencia os dispositivos
    ├── management_app/             # Diretório com o código da tela que gerencia os dispositivos
    ├── mqtt-kafka-bridge/          # Diretório com o código da bridge entre o MQTT e o Kafka
    ├── stream-control-service/     # Diretório com o código de gerenciamento das Filas do Kafka
├── infrastructure/                 # Diretório com configurações da infraestrutura
    ├── databases/                  # Configuração do Postgres e MongoDB
    ├── messengers/                 # Configuração do Kafka e MQTT
├── k6_scripts/                     # Arquivos k6 de teste
├── lsc-arduino-lib/                # Código para configurar o ESP32 e conectar ao MQTT
├── LICENSE                         # Licença MIT
├── README.md                       # Documentação principal
├── smartlab.run.sh                 # Arquivo bash para subir os dockers da aplicação
└── .gitignore                      # Arquivos a ignorar no Git
```

O projeto é dividido em uma hierarquia de pastas conforme descrito abaixo:

- Applications: Aplicação front-end e back-end para controlar o cadastro e a circulação de dados no kafka.
- Infrastructure: dados para configurar a infraestrutura (banco de dados e sistema de mensageria)
- k6_scripts: Arquivos para os testes que utilizaram a biblioteca k6
- lcs-arduino-lib: Biblioteca para o ESP32 responsável por enviar os dados dos sensores para o MQTT.

Os arquivos individuais servem para a configuração e execução da aplicação.

## Selos Considerados

Os selos considerados são: Disponíveis (SeloD) e Funcionais (SeloF).

## Dependências

A máquina precisará das seguintes aplicações para fazer o uso completo da aplicação:

- O Docker instalado na máquina para rodar as aplicações front e back-end.
- Uma IDE para edição de código Typescrypt e Java.
- ESP32 com algum sensor ou atuador (infravermelho capaz de enviar sinal) *(opcional)*
- O Arduino IDE instalado no computador com a extensão para compilar código para o ESP32 *(opcional)*

## Preocupações com segurança

A aplicação executa apenas as instâncias da aplicação. Se for executada no docker (recomendado para analise) a aplicação não apresentará nenhum problema de segurança para os revisores.
Caso ocorra um erro na aplicação basta reiniciar o Docker.

## Instalação

Requisitos mínimos para instalação:

- Instância do Docker em execução
- Executor de comandos batch (.sh)

Com o Docker em execução rode esse comando:

```[Bash]
sh smartlab.run.sh      # Windows
./smartlab.run.sh       # Linux
```

Após a execução do comando a aplicação realizará o download das tecnologias necessárias para a aplicação executar. E criará as instâncias do projeto no docker conforme imagem abaixo:

![Docker Running](./readme_images/docker_running.png)

As aplicações estão rodando nas seguintes portas:

- Zookeper: Interna -> 2181 / Externa -> 22181
- Kafka: Interna -> 9092 / Externa -> 9092
- Mosquitto: Interna -> 1883 / Externa -> 1883
- PostgreSQL: Interna -> 5432 / Externa -> 5433
- MongoDB: Interna -> 27017 / Externa -> 27017
- Management-API: Interna -> 8080 / Externa -> 8010
- Management-APP: Interna -> 80 / Externa -> 4200
- Service-Stream-Control: Interna -> 8080 / Externa -> 8011
- MQTT-Kafka-Bridge: Interna -> 8080 / Externa -> 8012
- Kafka-MQTT-Bridge: Interna -> 8080 / Externa -> 8013

### Conexão com o PostgreSQL

Para conectar ao PostgreSQL, pode ser utilizada uma ferramenta de Visualização de Banco de Dados como o DBeaver.

Informe as configurações abaixo:

```[text]
Host: localhost
Porta: 5433
Banco de Dados: SMARTLAB_DB
Usuário: SMARTLAB_USER
Senha: SMARTLAB_123456
```

Um exemplo de conexão utilizando o DBeaver é apresentado abaixo:

![Conexão com PostgreSQL](./readme_images/postgres_connection.png)

### Conexão com o MongoDB

Para conectar ao MongoDB, pode ser utilizada uma ferramenta para acompanhar as listagens de publish/subscribe. Como o MongoDB Compass.

```[text]
Host: localhost
Porta: 27017
Usuário: smartlabRoot
Senha: smartlabRoot2022

URL de Conexão: mongodb://smartlabRoot:smartlabRoot2022@127.0.0.1:27017/smartlab
```

### Conexão com o MQTT/Mosquitto

Para conectar ao MQTT, pode ser utilizada uma ferramenta para acompanhar as listagens de publish/subscribe. Como o MQTTBox.

As configurações para conexão são exibidas abaixo:

```[text]
Protocolo: mqtt/tcp
Host: 127.0.0.1:1883
```

![MQTT](./readme_images/mqtt_connection.png)

### Conexão com o FrontEnd

Para acessar o front-end da aplicação você precisar acessar o link <http://localhost:4200/>. Esse link exibirá uma página semelhante a essa:

![Tela de Login](./readme_images/tela_login.png)

Para acessar o sistema, será necessário usar um dos usuários a seguir:

```[text]
Usuário de Visualização
--
email: viewer@admin.com
senha: 123456
--

Usuário Padrão
--
email: user@admin.com
senha: 123456
--

Usuário Administrador
--
email: admin@admin.com
senha: 123456
--
```

Ao inserir o usuário selecionado você será redirecionado para a página do sistema:

![Página Sistema](./readme_images/pagina_sistema.png)

### ESP32

Para instalar o código da aplicação em um ESP32, é necessário ter o ESP32 e o respectivo sensor necessário para a leitura dos dados. Com ele em mãos abra o Arduino IDE e abre o código da pasta "lcs-arduino-lib".

Define os valores das constantes WIFI_USER, WIFI_PASS e UUID_DEVICE (código gerado para o dispositivo na aplicação) no arquivo airSmartLab.ino.

Define os valores das constantes _MQTT_BROKER_ADDRESS e _MQTT_BROKER_PORT no arquivo LSC.h, para os valores nos quais o broker MQTT está executando.

Após isso, basta carregar o código no ESP32 e ver se ele está publicando no tópico MQTT.

## Teste mínimo

Com a aplicação rodando no Docker o próximo passo é testar a Bridge, Banco de Dados, Listagem de Medições de dados está funcionando. Para isso, vamos publicar no MQTT e ver se a publicação reflete na aplicação.

Para isso, utilizarei o MQTTBox para simular a publicação de um microcontrolador, o corpo da requisição deve conter os seguintes parâmetros:

```[text]
tim: timestamp representando a hora da publicação do evento. <https://www.epochconverter.com/>
val: valor da leitura do sensor
uni: número que representa a unidade de medida. 1 - CELSIUS / 2 - Porcentagem / 3 - KOHM / 4 - LUX / 5 - PPM / 6 - HPA / 7 - Aceleração Linear
sen: número que representa o tipo de sensor. 1 - Temperatura / 2 - Umidade / 3 - CO2 / 4 - Movimento / 5 - Luminosidade
sid: UUID do sensor. Para o teste utilizar o sensor de temperatura já cadastrado na aplicação: '23c8a36d-b48d-4885-8ede-b1fc1562d8b9'
mid: UUID do microcontrolador. Para o teste utilizar o microcontrolador já cadastrado na aplicação: '3319a8bc-836c-4301-8384-e6932b267d84'
```

O corpo da requisição deve ser um JSON, ele deve ter o seguinte formato:

```[json]
{
    "tim": 1778368977,
    "val": 14.5,
    "uni": 1,
    "sen": 1,
    "sid": "23c8a36d-b48d-4885-8ede-b1fc1562d8b9",
    "mid": "3319a8bc-836c-4301-8384-e6932b267d84"
}
```

Além disso, temos os seguintes tópicos disponíveis para a leitura de variáveis dos sensores e publicação no MQTT:

- lasdpc/temperature/measure
- lasdpc/humidity/measure
- lasdpc/co2/measure
- lasdpc/movement/measure
- lasdpc/luminosity/measure
- lasdpc/status/check

Por fim, a publicação no MQTTBox será igual a essa:

![Publicação MQTTBox](./readme_images/publicacao_mqttbox.png)

O resultado da publicação poderá ser visualizado na URL: <http://localhost:4200/admin/microcontrolador/medicoes> ao selecionar os filtros da tela e clicar no botão **Medições** da listagem. Conforme imagens abaixo:

![Lista de Medições](./readme_images/lista_medicoes.png)

![Medições](./readme_images/medicao.png)

**Obs.:** As vezes o botão de medição não abre de primeira no primeiro acesso a página, basta clicar nele novamente.

## LICENSE

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](https://github.com/cairomn/event-driven-architecture-jems3/blob/main/LICENSE) para mais detalhes.
