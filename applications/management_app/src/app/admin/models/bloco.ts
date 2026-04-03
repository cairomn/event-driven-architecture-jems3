import { Piso } from "./piso";
import { Instituicao } from "./instituicao";

export interface Bloco {
  id: string;
  nome: string;
  instituicao: Instituicao;
  andares: Array<Piso>;
}