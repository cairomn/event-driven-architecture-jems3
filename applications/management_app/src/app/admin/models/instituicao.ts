import { Bloco } from "./bloco";
import { Piso } from "./piso";
import { Sala } from "./sala";

export interface Instituicao {
  id: string;
  nome: string;
  cnpj: string;
  blocos: Array<Bloco>;
  pisos: Array<Piso>;
  salas: Array<Sala>;
  instituicoesFilhas: Array<Instituicao>;
}