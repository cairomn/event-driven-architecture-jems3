import { Sensor } from "./sensor";
import { Atuador } from "./atuador";
import { Sala } from "./sala";

export interface Microcontrolador {
  id: string;
  apelido: string;
  macAddress: string;
  sala: Sala;
  sensores: Array<Sensor>;
  atuadores: Array<Atuador>;
};