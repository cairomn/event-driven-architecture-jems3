export interface Usuario {
  id: number;
  nome: string;
  email: string;
  password?: number;
  roles: string[];
}
