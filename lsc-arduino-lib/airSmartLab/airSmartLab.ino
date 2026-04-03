#include "LSC.h"
#include "ACS712.h"
#include "IRControl.hpp"

#define IR_LED 33            // pino em que o led infra vermelho para controlar o arcondicionado estara ligado
#define PIN_SENS_CORR 34   // pino em que realiza a medição do sensor de corrente
#define INTERVALO_COMANDO 10 // intervalo entre comandos em minutos
#define PUB_STATUS_PERIOD 15000 // minuto: 60000

#define WIFI_USER ""
#define WIFI_PASS ""
#define UUID_DEVICE ""

int pinActuator = 12;
IRControl controleFujitsu(FUJITSU_CON, IR_LED);
IRControl controleSamsung(SAMSUNG_CON, IR_LED);
IRControl controleCoolix(COOLIX_CON, IR_LED);
//ACS712 ACS(PIN_SENS_CORR, 3.3, 4095, 64);

int ZERO_SENSOR=0;

class LSCAirCond : public LSC
{
public:
  LSCAirCond(char *_ssid, char *_password, char *_microID) : LSC(_ssid, _password, _microID) {
    
  }
  

  /**
   * @brief Metodo com a logica principal para ligar e desligar o ar condicionado.
   *
   * @param action
   */
  void airConditionerOnOffConsumer(ACTION action)
  {
    Serial.println("[AIR-CON] ON-OFF Action ");
    (action.valor == 1) ? comandoLigarAr(action) : comandoDeligarAr();
  }
  
  /**
   * @brief Metodo com a logica principal para ligar e desligar o ar condicionado.
   *
   * @param action
   */
  void airConditionerChangeTempConsumer(ACTION action)
  {
    if (action.valor > 16 && action.valor <= 32)
    {
      comandoMudarTemperatura(action.valor);
    }
  }

  void airConditionerChangeModeConsumer(ACTION action)
  {
    if (action.valor >= AUTO && action.valor <= HEAT) {
        comandoMudarModo(action.valor);
    }
  }

  void comandoLigarAr(ACTION action)
  {
    controleFujitsu.setPower(true);
    controleSamsung.setPower(true);
    controleCoolix.setPower(true);
    Serial.println("[AIR-CON] Ligar ");
    enviarComando(action);
  }

  void comandoDeligarAr()
  {
    controleFujitsu.setPower(false);
    controleSamsung.setPower(false);
    controleCoolix.setPower(false);
    Serial.println("[AIR-CON] Desligar ");
  }
  
  void comandoMudarTemperatura(int temperatura)
  {
    controleFujitsu.setTemp(temperatura);
    controleFujitsu.setMode(AUTO);
    controleSamsung.setTemp(temperatura);
    controleSamsung.setMode(AUTO);
    controleCoolix.setTemp(temperatura);
    controleCoolix.setMode(AUTO);
    Serial.println("[AIR-CON] Mudar Temp ");
  }

  void comandoMudarModo(int mode)
  {
    controleFujitsu.setMode(mode);
    controleSamsung.setMode(mode);
    controleCoolix.setMode(mode);
    Serial.println("[AIR-CON] Mudar Modo ");
  }

  void enviarComando(ACTION action)
  {
    controleFujitsu.send();
    Serial.println("Enviando comando Fujitsu:");
    Serial.println(controleFujitsu.toString());
    delay(150);

    controleSamsung.send();
    Serial.println("Enviando comando Samsung:");
    Serial.println(controleSamsung.toString());
    delay(150);

    controleCoolix.send();
    Serial.println("Enviando comando Coolix:");
    Serial.println(controleCoolix.toString());

    publishStatusCheckK6(getStatusOnOffAr(), "8be14deb-a2ea-4b66-b22e-f57a0dcd9c3a", action.k6Topic);
  }

  float readCurrentPin(){

    float corrente_pico;
    int i,valor_lido,menor_valor=4095;
  
    for(i = 0;i<1000;i++){
      valor_lido = analogRead(PIN_SENS_CORR); 
      
      if(valor_lido<menor_valor)
        menor_valor=valor_lido;
  
      delayMicroseconds(10);
    }
   
    corrente_pico=(ZERO_SENSOR-menor_valor)*0.805/66;

    /*
    Serial.print("Corrente de pico: ");
    Serial.print(corrente_pico);
    Serial.println("A");

    Serial.print("Corrente Eficas: ");
    Serial.print(corrente_pico/1.4);
    Serial.println("A");*/
    return corrente_pico/1.4;  
  }

  

  float readCurrentPin(){

    float corrente_pico;
    int i,valor_lido,menor_valor=4095;
  
    for(i = 0;i<1000;i++){
      valor_lido = analogRead(PIN_SENS_CORR); 
      
      if(valor_lido<menor_valor)
        menor_valor=valor_lido;
  
      delayMicroseconds(10);
    }
   
    corrente_pico=(ZERO_SENSOR-menor_valor)*0.805/66;

    /*
    Serial.print("Corrente de pico: ");
    Serial.print(corrente_pico);
    Serial.println("A");

    Serial.print("Corrente Eficas: ");
    Serial.print(corrente_pico/1.4);
    Serial.println("A");*/
    return corrente_pico/1.4;  
}

  bool getStatusOnOffAr() {
    int contador=0;
    float valor_lido;
    
    Serial.print("Leituras Sensor: {");
    
    for (int i = 0; i<5;i++){
      
      valor_lido=readCurrentPin();
      Serial.print(valor_lido);
      Serial.print('\t');

      if(valor_lido>0.1){
        contador+=1;
      }

      delay(100);
    }
    Serial.println('}');
    
    return contador>3;
  }
};

LSCAirCond lsc(WIFI_USER, WIFI_PASS, UUID_DEVICE);

void autoZero(){

    int menor_valor=4095,valor_lido,total=0;
    long previousMicros=0,intervalMicros=10;

   for(int i = 0;i<10000;i++){
      valor_lido = analogRead(PIN_SENS_CORR); 
      
      if(valor_lido<menor_valor)
        menor_valor=valor_lido;
  
      delayMicroseconds(1);
    }
    
    ZERO_SENSOR=menor_valor;
    Serial.print("Zero sensor:");
    Serial.println(ZERO_SENSOR);
}

void setup()
{
  Serial.begin(9600);
  
  //ACS.autoMidPoint();
  pinMode(PIN_SENS_CORR, INPUT);
  autoZero();
  Serial.print("ZERO CHAMADO");
  delay(100);
}

void loop()
{
  static int last = 0;
  
  if (!lsc.run())
  {
    delay(2000);
  } else {
    // Publica o STATUS de verificação do ar condicionado.
    if ((millis() - last) > PUB_STATUS_PERIOD)
    {
      last = millis();
      lsc.publishStatusCheck(lsc.getStatusOnOffAr(), NULL, NULL, "79de6497-fd09-4c11-b670-61a1aae6f6a5", NULL);
    }
  }
  // else {
    // Publica o STATUS de verificação do ar condicionado.
    // if ((millis() - last) > PUB_STATUS_PERIOD)
    // {
    //   last = millis();
    //   lsc.publishStatusCheck(lsc.getStatusOnOffAr(), NULL, NULL, "79de6497-fd09-4c11-b670-61a1aae6f6a5", NULL);
    // }
  // }
}