import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Microcontrolador } from '../models/microcontrolador';
import { PagedResponse } from '../models/responses/paged-response';
import { RestResponse } from '../models/responses/rest-response';
import { Acao } from '../models/acao';

@Injectable({
  providedIn: 'root'
})
export class MicrocontroladorService {

  /**
   * Construtor da classe.
   * 
   * @param http 
   */
  constructor(private http: HttpClient) { }

  public listar(): Observable<PagedResponse<Microcontrolador>> {
    return this.http.get<PagedResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores');
  }

  public listarPorId(id: string): Observable<RestResponse<Microcontrolador>> {
    return this.http.get<RestResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + id);
  }

  public listarSalas(idSala: number): Observable<RestResponse<Array<Microcontrolador>>> {
    return this.http.get<RestResponse<Array<Microcontrolador>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/sala/' + idSala);
  }

  public listarSalasPaginadas(idSala: number): Observable<RestResponse<Array<Microcontrolador>>> {
    return this.http.get<RestResponse<Array<Microcontrolador>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/sala/' + idSala);
  }

  public salvar(microcontrolador: Microcontrolador): Observable<RestResponse<Microcontrolador>> {
    if (microcontrolador.id) {
      return this.http.put<RestResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + microcontrolador.id, microcontrolador);
    }

    return this.http.post<RestResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores', microcontrolador);
  }

  public deletar(id: string): Observable<RestResponse<Microcontrolador>> {
    return this.http.delete<RestResponse<Microcontrolador>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + id);
  }

  public onOffAirConditioner(acao: Acao): Observable<void> {
    return this.http.post<void>(
      environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + acao.microID + '/on-off-air-cond',
      acao
    );
  }

  public changeTempAirConditioner(acao: Acao): Observable<void> {
    return this.http.post<void>(
      environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + acao.microID + '/change-temp-air-cond',
      acao
    );
  }

  public changeModeAirConditioner(acao: Acao): Observable<void> {
    return this.http.post<void>(
      environment.URL_API_MANAGEMENT_DASHBOARD + 'microcontroladores/' + acao.microID + '/change-mode-air-cond',
      acao
    );
  }

}
