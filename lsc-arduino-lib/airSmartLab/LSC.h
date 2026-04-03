#ifndef LSC_H
#define LSC_H

/**
 * LIBRARIES
 */
#include <vector>
#include "SPIFFS.h"
#include <ArduinoJson.h>
#include <PubSubClient.h>
#include <Wire.h>
#include <WiFi.h>
#include <NTPClient.h>
#include <WiFiUdp.h>

typedef enum TP_DEVICE
{
  TP_SENSOR = 1,
  TP_ACTUATOR
} TP_DEVICES;

/**
 * Type definitions for control the structure os Lab-Stream-Control.
 */
typedef enum TP_SENSOR
{
  TP_SENSOR_TEMPERATURE = 1,
  TP_SENSOR_HUMIDITY,
  TP_SENSOR_CO2,
  TP_SENSOR_MOVEMENT,
  TP_SENSOR_LUMINOSITY
} TP_SENSORS;

typedef enum TP_ACTUATOR
{
  TP_ACTUATOR_AIR_CONDITIONER = 1,
  TP_ACTUATOR_LAMP,
  TP_ACTUATOR_RELE
} TP_ACTUATORS;

typedef enum TP_UNIT_MEASURE {
  TP_UNIT_MEASURE_CELSIUS = 1,
  TP_UNIT_MEASURE_PERCENTAGE,
  TP_UNIT_MEASURE_KOHM,
  TP_UNIT_MEASURE_LUX,
  TP_UNIT_MEASURE_PPM,
  TP_UNIT_MEASURE_HPA,
  TP_UNIT_MEASURE_LINEAR_ACELERATION
} TP_UNIT_MEASURES;

typedef struct measure
{
  double valor;
  double timestamp;
  char sensorID[32];
  TP_UNIT_MEASURE unitMeasure;
  char microcontroladorID[32];
} MEASURE;

typedef struct action {
  double valor;
  char actID[34];
  char k6Topic[34];
} ACTION;

/**
 * Incluir Wifi cliente, mqtt client e biblioteca para trabalhar com arquivos.
 * Criar as funções de envio de mensagem para o servidor.
 */
class LSC
{

public:
  // CONSTRUCTORS
  LSC(char *ssid, char *password, char *microID);

  // CONSUMERS TOPICS
  const char *MQTT_TCONS_LAMP_ON_OFF = "lasdpc/lamp/on-off/";
  const char *MQTT_TCONS_AIR_ON_OFF = "lasdpc/air-conditioner/on-off/";
  const char *MQTT_TCONS_AIR_CHANGE_TEMP = "lasdpc/air-cond/change-temp/";
  const char *MQTT_TCONS_AIR_CHANGE_MODE = "lasdpc/air-cond/change-mode/";

  // DEFAULT FUNCTIONS
  bool run();
  
  // PUBLISHERS
  bool publishMeasureTemperature(TP_UNIT_MEASURES, char sensorID[32], double);
  bool publishMeasureHumidity(TP_UNIT_MEASURES, char sensorID[32], double);
  bool publishMeasureCO2(TP_UNIT_MEASURES, char sensorID[32], double);
  bool publishMeasureMovement(TP_UNIT_MEASURES, char sensorID[32], double);
  bool publishMeasureLuminosity(TP_UNIT_MEASURES, char sensorID[32], double);
  bool publishStatusCheck(bool on, int temp, int mode, char actuatorID[32], char sensorID[32]);
  bool publishStatusCheckK6(bool on, char actuatorID[32], char *topic);

  // CONSUMERS
  virtual void lampOnOffConsumer(ACTION);
  virtual void airConditionerOnOffConsumer(ACTION);
  virtual void airConditionerChangeTempConsumer(ACTION);
  virtual void airConditionerChangeModeConsumer(ACTION);

private:
  // VARIABLES
  char *_uuidSala;
  char *_ssidNetwork;
  char *_passwordNetwork;
  char *_uuidMicrocontroller;

  char *_clientName;
  char *_clientSubLampOnOff;
  char *_clientSubAirOnOff;
  char *_clientSubAirChangeTemp;
  char *_clientSubAirChangeMode;

  // CONSTANTS
  const char *_MQTT_BROKER_ADDRESS = "";
  //const char *_MQTT_BROKER_ADDRESS = "broker.hivemq.com";
  // const char *_MQTT_BROKER_ADDRESS = "192.168.1.5";
  const unsigned int _MQTT_BROKER_PORT = 1884;

  // CONSTANTS: PUBLISHS TOPICS
  const char *_MQTT_TPUB_TEMPERATURE = "lasdpc/temperature/measure";
  const char *_MQTT_TPUB_HUMIDITY = "lasdpc/humidity/measure";
  const char *_MQTT_TPUB_CO2 = "lasdpc/co2/measure";
  const char *_MQTT_TPUB_MOVEMENT = "lasdpc/movement/measure";
  const char *_MQTT_TPUB_LUMINOSITY = "lasdpc/luminosity/measure";
  const char *_MQTT_TPUB_STATUS_CHECK = "lasdpc/status/check";

  // WIFI
  bool _connectWifi();
  bool _reconnectWifi();
  bool _isWiFiConnected();

  // MQTT
  bool _mqttConnect();
  void _mqttReconnect();

  // CALLBACK FUNCTIONS
  void _callback(const char *, byte *, unsigned int);

  // MISC FUNCTIONS
  DynamicJsonDocument _getDocumentJsonMeasure(TP_SENSORS, TP_UNIT_MEASURES, char[32], double);
  DynamicJsonDocument _getDocumentJsonStatusCheck(bool, int, int, char[32], char[32]);
  bool _isMicrocontrollerRegistered();
};
#endif
