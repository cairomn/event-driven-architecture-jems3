import { Microcontrolador } from "./microcontrolador";
import { StatusCheck } from "./status-check";

export interface Atuador {
  id: string;
  apelido: string;
  microcontrolador: Microcontrolador;
  status: number;
  tipoAtuador: number;
  cordenadaX?: number;
  cordenadaY?: number;
  statusCheck: StatusCheck;
};