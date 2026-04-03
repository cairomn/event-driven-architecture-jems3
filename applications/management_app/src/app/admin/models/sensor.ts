import { Microcontrolador } from "./microcontrolador";
import { TipoSensor } from "./tipo-sensor";

export interface Sensor {
  id: string;
  apelido: string;
  microcontrolador: Microcontrolador;
  status: number;
  tipoSensor: number;
  cordenadaX?: number;
  cordenadaY?: number;
};