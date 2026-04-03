import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Instituicao } from '../models/instituicao';
import { Observable } from 'rxjs';
import { Bloco } from '../models/bloco';
import { PagedResponse } from '../models/responses/paged-response';
import { RestResponse } from '../models/responses/rest-response';


@Injectable({
  providedIn: 'root'
})
export class InstituicaoService {

  /**
   * Construtor da classe.
   * 
   * @param http 
   */
  constructor(private http: HttpClient) { }

  public paginar(numPag: number = 0, pagSize: number = 10): Observable<PagedResponse<Instituicao>> {
    return this.http.get<PagedResponse<Instituicao>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes?numPag=' + numPag + "&pagSize=" + pagSize);
  }

  public listarBlocos(id: string): Observable<RestResponse<Array<Bloco>>> {
    return this.http.get<RestResponse<Array<Bloco>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + id + '/blocos');
  }

  public listarPorId(id: string): Observable<RestResponse<Instituicao>> {
    return this.http.get<RestResponse<Instituicao>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + id);
  }

  public salvar(instituicao: Instituicao): Observable<RestResponse<Instituicao>> {
    if (instituicao.id) {
      return this.http.put<RestResponse<Instituicao>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + instituicao.id, instituicao);
    }

    return this.http.post<RestResponse<Instituicao>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes', instituicao);
  }

  public deletar(id: string): Observable<RestResponse<Instituicao>> {
    return this.http.delete<RestResponse<Instituicao>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'instituicoes/' + id);
  }
}
