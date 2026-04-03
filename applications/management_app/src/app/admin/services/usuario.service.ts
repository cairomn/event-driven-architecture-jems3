import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PagedResponse} from "../models/responses/paged-response";
import {environment} from "../../../environments/environment";
import {RestResponse} from "../models/responses/rest-response";
import {Usuario} from "../models/usuario";
import {Sala} from "../models/sala";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private reqHeaders: HttpHeaders;

  /**
   * Construtor da classe.
   *
   * @param http
   */
  constructor(private http: HttpClient) { }

  public paginar(numPag: number = 0, pagSize: number = 10): Observable<PagedResponse<Usuario>> {
    return this.http.get<PagedResponse<Usuario>>(
      environment.URL_API_MANAGEMENT_DASHBOARD + 'usuarios?numPag=' + numPag + "&pagSize=" + pagSize, {
        headers: this.reqHeaders
      }
    );
  }

  public listarPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(environment.URL_API_MANAGEMENT_DASHBOARD + 'usuarios/' + id);
  }

  public salvar(usuario: Usuario): Observable<Usuario> {
    if (usuario.id) {
      return this.http.put<Usuario>(environment.URL_API_MANAGEMENT_DASHBOARD + 'usuarios/' + usuario.id, usuario);
    }

    return this.http.post<Usuario>(environment.URL_API_MANAGEMENT_DASHBOARD + 'usuarios', usuario);
  }

}
