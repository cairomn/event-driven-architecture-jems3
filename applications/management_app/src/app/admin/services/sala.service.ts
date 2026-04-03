import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Microcontrolador } from '../models/microcontrolador';
import { Sala } from '../models/sala';
import { PagedResponse } from '../models/responses/paged-response';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  /**
   * Construtor da classe.
   * 
   * @param http 
   */
  constructor(private http: HttpClient) { }

  public get(id: string): Observable<RestResponse<Sala>> {
    return this.http.get<RestResponse<Sala>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas/' + id);
  }

  public salvar(sala: Sala): Observable<RestResponse<Sala>> {
    if (sala.id) {
      return this.http.put<RestResponse<Sala>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas/' + sala.id, sala);
    }

    return this.http.post<RestResponse<Sala>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas', sala);
  }

  public deletar(id: string): Observable<RestResponse<Sala>> {
    return this.http.delete<RestResponse<Sala>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas/' + id);
  }

  public listarPorPiso(idPiso: string): Observable<RestResponse<Array<Sala>>> {
    return this.http.get<RestResponse<Array<Sala>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + idPiso + '/salas');
  }

  public listarMicrocontroladores(id: string): Observable<RestResponse<Array<Microcontrolador>>> {
    return this.http.get<RestResponse<Array<Microcontrolador>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas/' + id + '/microcontroladores');
  }

  public listarMicrocontroladoresPaginados(id: string, numPag: number): Observable<PagedResponse<Microcontrolador>> {
    return this.http.get<PagedResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'salas/' + id + '/microcontroladores/paginados?pagNum=' + numPag);
  }
}
