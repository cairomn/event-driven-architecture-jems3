import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Piso } from '../models/piso';
import { Sala } from '../models/sala';
import { PagedResponse } from '../models/responses/paged-response';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class PisoService {

  /**
   * Construtor da classe.
   * 
   * @param http 
   */
  constructor(private http: HttpClient) { }

  public listarPorBloco(idBloco: string): Observable<RestResponse<Array<Piso>>> {
    return this.http.get<RestResponse<Array<Piso>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos');
  }

  public listarPorBlocoPaginados(idBloco: string): Observable<PagedResponse<Piso>> {
    return this.http.get<PagedResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + idBloco + '/pisos/paginados');
  }

  public listarSalas(idPiso: string): Observable<RestResponse<Array<Sala>>> {
    return this.http.get<RestResponse<Array<Sala>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + idPiso + '/salas');
  }

  public listarSalasPaginadas(idPiso: string, pagNum: number = 0, pagSize: number = 10): Observable<PagedResponse<Sala>> {
    return this.http.get<PagedResponse<Sala>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + idPiso + '/salas/paginadas?pagNum=' + pagNum + '&pagSize=' + pagSize);
  }

  public get(id: string): Observable<RestResponse<Piso>> {
    return this.http.get<RestResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + id);
  }

  public salvar(andar: Piso): Observable<RestResponse<Piso>> {
    if (andar.id) {
      return this.http.put<RestResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + andar.id, andar);
    }

    return this.http.post<RestResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/', andar);
  }

  public deletar(id: string): Observable<RestResponse<Piso>> {
    return this.http.delete<RestResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'pisos/' + id);
  }
}
