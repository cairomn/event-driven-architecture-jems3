#ifndef IR_CONTROL_HPP
#define IR_CONTROL_HPP

#include <Arduino.h>

#define FUJITSU_CON 0
#define SAMSUNG_CON 1
#define COOLIX_CON  2
#define DAIKIN_CON  3

#define AUTO 0 // esquenta se estiver frio demais e esfria se estiver quente demais
#define COOL 1 // apenas esfria
#define DRY  2 // remove umidade do ar, projetado para ser usado em dias chuvosos
#define FAN  3 // funciona apenas como ventilador?
#define HEAT 4 // apenas esquenta

class IRControl{
  public:
    IRControl(int brand, int pin);
    virtual void send();
    virtual void setTemp(uint8_t desired); // configura a temperatura desejada
    virtual void setMode(uint8_t mode); // configura o ar-condicionado para um dos modos de COOL, HEAT, DRY, FAN ou AUTO
    virtual void setPower(bool on); // liga/desliga o ar-condicionado
    virtual void setSwing(uint8_t mode); // configura as pas de distribuicao de ar
    virtual void setFan(uint8_t speed); // configura a velocidade da ventuinha
    virtual void setFilter(bool on); // filtra o ar removendo particulas suspensas
    virtual void setClean(bool on); // aumenta a potência por alguns minutos para remover umidade de dentro do ar-condicionado
    virtual bool getPower(); // retorna se o ar condicionado esta desligado (true) ou ligado (false)
    virtual uint8_t getTemp(); // retorna a temperatura setada no ar condicionado.
    virtual uint8_t getMode(); // retorna o mode setado no ar condicionado.
    virtual String toString(); // gera uma string legivel por humanos do comando que sera enviado ao ar-condicionado

  private:
    void *_controle;
    int _brand;
};

#endif
