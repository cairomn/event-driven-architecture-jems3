import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bloco } from '../models/bloco';
import { Piso } from '../models/piso';
import { PagedResponse } from '../models/responses/paged-response';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class BlocoService {

  /**
   * Construtor da classe.
   * 
   * @param http 
   */
  constructor(private http: HttpClient) { }

  public listar(idInstituicao: string): Observable<RestResponse<Array<Bloco>>> {
    return this.http.get<RestResponse<Array<Bloco>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + idInstituicao + '/blocos');
  }

  public paginar(idInstituicao: string, pagNum: number = 0, pagSize: number = 10): Observable<PagedResponse<Bloco>> {
    return this.http.get<PagedResponse<Bloco>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + idInstituicao + '/blocos/paginados?pagNum=' + pagNum + '&pagSize=' + pagSize);
  }

  public listarPisos(idBloco: string): Observable<RestResponse<Array<Piso>>> {
    return this.http.get<RestResponse<Array<Piso>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + idBloco + '/pisos');
  }

  public listarPisosPaginados(idBloco: string, pagNum: number = 0, pagSize: number = 10): Observable<PagedResponse<Piso>> {
    return this.http.get<PagedResponse<Piso>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + idBloco + '/pisos/paginados?pagNum=' + pagNum + '&pagSize=' + pagSize);
  }

  public listarPorId(id: string): Observable<RestResponse<Bloco>> {
    return this.http.get<RestResponse<Bloco>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + id);
  }

  public salvar(bloco: Bloco): Observable<RestResponse<Bloco>> {
    if (bloco.id) {
      return this.http.put<RestResponse<Bloco>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + bloco.id, bloco);
    }

    return this.http.post<RestResponse<Bloco>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos', bloco);
  }

  public deletar(id: string): Observable<RestResponse<Bloco>> {
    return this.http.delete<RestResponse<Bloco>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'blocos/' + id);
  }
}
