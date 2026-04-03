#include "LSC.h"

/**
 * @brief VARIABLE DECLARATIONS
 */
WiFiClient wifiClientLSC;
PubSubClient clientLSC(wifiClientLSC);

// Define NTP Client to get time
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP);

StaticJsonDocument<128> doc;

/**
 * @brief Construct a new LSC::LSC object
 *
 * @param ssid
 * @param password
 * @param salaID
 */
LSC::LSC(char *ssid, char *password, char *microID)
{
  _ssidNetwork = (char *)malloc(sizeof(char) * strlen(ssid) + 1);
  _passwordNetwork = (char *)malloc(sizeof(char) * strlen(password) + 1);
  _uuidMicrocontroller = (char *)malloc(sizeof(char) * strlen(microID) + 1);

  strcpy(_ssidNetwork, ssid);
  strcpy(_passwordNetwork, password);
  strcpy(_uuidMicrocontroller, microID);

  _clientName = (char *)malloc(sizeof(char) * strlen("LUKITAS") + sizeof(char) * strlen(_uuidMicrocontroller) + 1);
  strcpy(_clientName, "LUKITAS");
  strcat(_clientName, _uuidMicrocontroller);

  _clientSubLampOnOff = (char *)malloc(sizeof(char) * strlen(MQTT_TCONS_LAMP_ON_OFF) + sizeof(char) * strlen(_uuidMicrocontroller) + 1);
  strcpy(_clientSubLampOnOff, MQTT_TCONS_LAMP_ON_OFF);
  strcat(_clientSubLampOnOff, _uuidMicrocontroller);

  _clientSubAirOnOff = (char *)malloc(sizeof(char) * strlen(MQTT_TCONS_AIR_ON_OFF) + sizeof(char) * strlen(_uuidMicrocontroller) + 1);
  strcpy(_clientSubAirOnOff, MQTT_TCONS_AIR_ON_OFF);
  strcat(_clientSubAirOnOff, _uuidMicrocontroller);

  _clientSubAirChangeTemp = (char *)malloc(sizeof(char) * strlen(MQTT_TCONS_AIR_CHANGE_TEMP) + sizeof(char) * strlen(_uuidMicrocontroller) + 1);
  strcpy(_clientSubAirChangeTemp, MQTT_TCONS_AIR_CHANGE_TEMP);
  strcat(_clientSubAirChangeTemp, _uuidMicrocontroller);

  _clientSubAirChangeMode = (char *)malloc(sizeof(char) * strlen(MQTT_TCONS_AIR_CHANGE_MODE) + sizeof(char) * strlen(_uuidMicrocontroller) + 1);
  strcpy(_clientSubAirChangeMode, MQTT_TCONS_AIR_CHANGE_MODE);
  strcat(_clientSubAirChangeMode, _uuidMicrocontroller);
}

bool LSC::run()
{
  bool isRunning = true;
  if (!_isWiFiConnected())
  {
    Serial.print(".");
    isRunning = _reconnectWifi();
  }

  if (_isWiFiConnected()) {
    isRunning = _mqttConnect();
    timeClient.begin();
  }
    
  return isRunning;
}

/**
  @brief Publish the measure in MQTT route temperature.

  @param double measure
  @return true
  @return false
*/
bool LSC::publishMeasureTemperature(TP_UNIT_MEASURES unitMeasure, char sensorID[32], double measure)
{
  if (!_isMicrocontrollerRegistered())
    return false;
  char buffer[256];
  DynamicJsonDocument doc =
      _getDocumentJsonMeasure(TP_SENSOR_TEMPERATURE, unitMeasure, sensorID, measure);
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_TEMPERATURE, buffer) == true;
}

/**
  @brief Publish the measure in MQTT route humidity.

  @param double measure
  @return true
  @return false
*/
bool LSC::publishMeasureHumidity(TP_UNIT_MEASURES unitMeasure, char sensorID[32], double measure)
{
  if (!_isMicrocontrollerRegistered())
    return false;
  char buffer[256];
  DynamicJsonDocument doc =
      _getDocumentJsonMeasure(TP_SENSOR_HUMIDITY, unitMeasure, sensorID, measure);
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_HUMIDITY, buffer) == true;
}

/**
  @brief Publish the measure in MQTT route humidity.

  @param double measure
  @return true
  @return false
*/
bool LSC::publishMeasureCO2(TP_UNIT_MEASURES unitMeasure, char sensorID[32], double measure)
{
  if (!_isMicrocontrollerRegistered())
    return false;
  char buffer[256];
  DynamicJsonDocument doc = _getDocumentJsonMeasure(TP_SENSOR_CO2, unitMeasure, sensorID, measure);
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_CO2, buffer) == true;
}

/**
  @brief Publish the measure in MQTT route moviment.

  @param double measure
  @return true
  @return false
*/
bool LSC::publishMeasureMovement(TP_UNIT_MEASURES unitMeasure, char sensorID[32], double measure)
{
  if (!_isMicrocontrollerRegistered())
    return false;
  char buffer[256];
  DynamicJsonDocument doc =
      _getDocumentJsonMeasure(TP_SENSOR_MOVEMENT, unitMeasure, sensorID, measure);
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_MOVEMENT, buffer) == true;
}

/**
  @brief Publish the measure in MQTT route luminosity.

  @param double measure
  @return true
  @return false
*/
bool LSC::publishMeasureLuminosity(TP_UNIT_MEASURES unitMeasure, char sensorID[32], double measure)
{
  DynamicJsonDocument doc =
      _getDocumentJsonMeasure(TP_SENSOR_LUMINOSITY, unitMeasure, sensorID, measure);
  char buffer[256];
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_LUMINOSITY, buffer) == true;
}

/**
 * @brief Publish the status check in MQTT.
 *
 * @param bool on
 * @param int temp
 * @param int mode
 * @param char actuatorID
 * @param char sensorID
 * @return true
 * @return false
 */
bool LSC::publishStatusCheck(bool on, int temp, int mode, char actuatorID[32], char sensorID[32])
{
  DynamicJsonDocument doc = _getDocumentJsonStatusCheck(on, temp, mode, actuatorID, sensorID);
  char buffer[256];
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(_MQTT_TPUB_STATUS_CHECK, buffer) == true;
}

bool LSC::publishStatusCheckK6(bool on, char actuatorID[32], char topic[32])
{
  DynamicJsonDocument doc = _getDocumentJsonStatusCheck(on, NULL, NULL, actuatorID, NULL);
  char buffer[256];
  size_t n = serializeJson(doc, buffer);
  return clientLSC.publish(topic, buffer) == true;
}

/**
 * @brief Verify if microcontroller was registered on server.
 *
 * @return true
 * @return false
 */
bool LSC::_isMicrocontrollerRegistered() { return _uuidMicrocontroller != NULL && _uuidMicrocontroller != ""; }

/**
 * @brief Connect to wi-fi connection.
 *
 * @return true
 * @return false
 */
bool LSC::_connectWifi()
{
  WiFi.begin(_ssidNetwork, _passwordNetwork);
  return _isWiFiConnected();
}

/**
 * @brief Re-connect to wifi with the ssid and password presented in
 * constructor.
 *
 * @return true
 * @return false
 */
bool LSC::_reconnectWifi()
{
  if (!_isWiFiConnected())
  {
    delay(2000);
    Serial.print(".");
    return _connectWifi();
  }

  return _isWiFiConnected();
}

/**
 * @brief Verify wifi is connected.
 *
 * @return true
 * @return false
 */
bool LSC::_isWiFiConnected() { return WiFi.status() == WL_CONNECTED; }

/**
 * @brief Verify MQTT Broker is connected.
 *
 * @return true
 * @return false
 */
bool LSC::_mqttConnect()
{
  if (!_isWiFiConnected())
    return false;

  clientLSC.setServer(_MQTT_BROKER_ADDRESS, _MQTT_BROKER_PORT);
  if (!clientLSC.connected())
    _mqttReconnect();

  clientLSC.loop();
  return true;
}

/**
 * @brief Try to recconect to mqtt, this method block until connect to mqtt.
 *
 */
void LSC::_mqttReconnect()
{
  while (!clientLSC.connected())
  { 
    Serial.print("Attempting MQTT connection...");
    
    // Attempt to connect
    if (clientLSC.connect(_clientName))
    {
      Serial.println("connected");
      clientLSC.subscribe(_clientSubLampOnOff);
      clientLSC.subscribe(_clientSubAirOnOff);
      clientLSC.subscribe(_clientSubAirChangeTemp);
      clientLSC.subscribe(_clientSubAirChangeMode);
      clientLSC.setCallback(std::bind(&LSC::_callback, this, std::placeholders::_1, std::placeholders::_2, std::placeholders::_3));

      Serial.println(clientLSC.connected());
      delay(250);
    }
    else
    {
      Serial.print("failed, rc=");
      Serial.print(clientLSC.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void LSC::lampOnOffConsumer(ACTION action) {}

void LSC::airConditionerOnOffConsumer(ACTION action) {}

void LSC::airConditionerChangeTempConsumer(ACTION action) {}

void LSC::airConditionerChangeModeConsumer(ACTION action) {}

void LSC::_callback(const char *topic, byte *message, unsigned int length)
{
  deserializeJson(doc, (const byte*)message, length);

  // Pegar mensagem dos sensores e converter no objeto ACTION.
  ACTION act;
  act.valor = doc["val"];
  strcpy(act.actID, doc["actID"]);
  strcpy(act.k6Topic, doc["k6Topic"]);
  
  if (String(topic) == _clientSubLampOnOff) lampOnOffConsumer(act);
  if (String(topic) == _clientSubAirOnOff) airConditionerOnOffConsumer(act);
  if (String(topic) == _clientSubAirChangeTemp) airConditionerChangeTempConsumer(act);
  if (String(topic) == _clientSubAirChangeMode) airConditionerChangeModeConsumer(act);
}

DynamicJsonDocument LSC::_getDocumentJsonMeasure(
    TP_SENSORS typeSensor,
    TP_UNIT_MEASURES unitMeasures,
    char sensorID[32],
    double measure)
{
  timeClient.update();
  DynamicJsonDocument doc(1024);
  doc["tim"] = timeClient.getEpochTime();
  doc["val"] = measure;
  doc["uni"] = unitMeasures;
  doc["sen"] = typeSensor;
  doc["sid"] = sensorID;
  doc["mid"] = _uuidMicrocontroller;
  return doc;
}

DynamicJsonDocument LSC::_getDocumentJsonStatusCheck(
  bool on,
  int temp,
  int mode,
  char actuatorID[32],
  char sensorID[32])
{
  timeClient.update();
  DynamicJsonDocument doc(1024);
  doc["tim"] = timeClient.getEpochTime();
  doc["on"] = on;
  doc["tem"] = temp;
  doc["mod"] = mode;
  doc["sid"] = sensorID;
  doc["aid"] = actuatorID;
  return doc;
}
