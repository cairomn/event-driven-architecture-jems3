#include "IRControl.hpp"
#include <ir_Fujitsu.h>
#include <ir_Samsung.h>
#include <ir_Coolix.h>
#include <ir_Daikin.h>

IRControl::IRControl(int brand, int pin) {
  switch(brand){
    case FUJITSU_CON:
      this->_controle = (void*) ( new IRFujitsuAC(pin, fujitsu_ac_remote_model_t::ARRAH2E));
      ((IRFujitsuAC*) this->_controle)->begin();
      break;
    case SAMSUNG_CON:
      this->_controle = (void*) ( new IRSamsungAc(pin));
      ((IRSamsungAc*) this->_controle)->begin();
      break;
    case COOLIX_CON:
      this->_controle = (void*) ( new IRCoolixAC(pin));
      ((IRCoolixAC*) this->_controle)->begin();
      break;
  }
  this->_brand = brand;
}

void IRControl::send() {
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->send();
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->send();
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->send();
      break;
  }
}

void IRControl::setTemp(uint8_t desired){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setTemp(desired);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setTemp(desired);
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->setTemp(desired);
      break;
  }
}

void IRControl::setMode(uint8_t mode){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setMode(mode);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setMode(mode);
      break;
    case COOLIX_CON:
      switch(mode){
        case COOL:
          mode = kCoolixCool;
          break;
        case DRY:
          mode = kCoolixDry;
          break;
        case AUTO:
          mode  = kCoolixAuto;
          break;
        case FAN:
          mode = kCoolixFan;
          break;
        case HEAT:
          mode = kCoolixHeat;
          break;
      }
      ((IRCoolixAC*) this->_controle)->setMode(mode);
      break;
  }
}

void IRControl::setPower(bool on){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setPower(on);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setPower(on);
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->setPower(on);
      break;
  }
}

void IRControl::setSwing(uint8_t mode){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setSwing(mode);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setSwing(mode > 0);
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->setSwing();
      break;
  }
}

void IRControl::setFan(uint8_t speed){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setFanSpeed(speed);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setFan(speed);
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->setFan(speed);
      break;
  }
}

void IRControl::setFilter(bool on){
  if(this->_brand == FUJITSU_CON){
    ((IRFujitsuAC*) this->_controle)->setFilter(on);
  }
}

void IRControl::setClean(bool on){
  switch(this->_brand){
    case FUJITSU_CON:
      ((IRFujitsuAC*) this->_controle)->setClean(on);
      break;
    case SAMSUNG_CON:
      ((IRSamsungAc*) this->_controle)->setClean(on);
      break;
    case COOLIX_CON:
      ((IRCoolixAC*) this->_controle)->setClean();
      break;
  }
}

bool IRControl::getPower(){
  switch(this->_brand){
    case FUJITSU_CON:
      return !((IRFujitsuAC*) this->_controle)->getPower();
      break;
    case SAMSUNG_CON:
      return !((IRSamsungAc*) this->_controle)->getPower();
      break;
    case COOLIX_CON:
      return !((IRCoolixAC*) this->_controle)->getPower();
      break;
  }
}

uint8_t IRControl::getTemp() {
  switch(this->_brand){
    case FUJITSU_CON:
      return !((IRFujitsuAC*) this->_controle)->getTemp();
      break;
    case SAMSUNG_CON:
      return !((IRSamsungAc*) this->_controle)->getTemp();
      break;
    case COOLIX_CON:
      return !((IRCoolixAC*) this->_controle)->getTemp();
      break;
  }
}

uint8_t IRControl::getMode() {
  switch(this->_brand){
    case FUJITSU_CON:
      return !((IRFujitsuAC*) this->_controle)->getMode();
      break;
    case SAMSUNG_CON:
      return !((IRSamsungAc*) this->_controle)->getMode();
      break;
    case COOLIX_CON:
      return !((IRCoolixAC*) this->_controle)->getMode();
      break;
  }
}

String IRControl::toString() {
  switch(this->_brand){
    case FUJITSU_CON:
      return ((IRFujitsuAC*) this->_controle)->toString();
      break;
    case SAMSUNG_CON:
      return ((IRSamsungAc*) this->_controle)->toString();
      break;
    case COOLIX_CON:
      return ((IRCoolixAC*) this->_controle)->toString();
      break;
  }
}
