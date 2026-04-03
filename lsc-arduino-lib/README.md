# Controle de Fluxo de Laboratório (Português)

Esta aplicação tem como objetivo construir a ponte entre o microcontrolador ESP32 e o MQTT.

A seguir apresentamos os principais objetivos desta biblioteca:
- Construir uma ponte entre microcontroladores e publicação/assinatura do MQTT.
- Apresentar um único ponto para publicar no servidor do Laboratory Stream Control.
- Conecte os dados publicados no Kafka no outro lado do MQTT.
- Defina uma estrutura para configurar o método de publicação e assinatura.
- Bibliotecas necessárias para a aplicação:
  - ArduinoJson (Objeto de publicacao)
  - PubSubClient (Publicação/Subscrição)
  - Wire
  - SPIFFS
  - NTPClient (Recupera timestamp)

## Configurando o ESP8266 no Arduino IDE

Tutorial retirado do link: https://www.filipeflop.com/blog/programar-nodemcu-com-ide-arduino

- Abra o Arduino IDE.
- No menu da IDE acesse *Arquivo* -> *Preferências*
- 

# Laboratory Stream Control (English)

This application that's the aim of construct the bridge between the ESP32 microcontroller and the MQTT.

Follow we presente the main objectives of this library:
- Construct a bridge between microcontrollers and MQTT pusblish/subscribe.
- Present a single point to publish in the server of Laboratory Stream Control.
- Connect the data published to Kafka on the other side of MQTT.
- Set a structure to configure the method of publisher and subscribe.
