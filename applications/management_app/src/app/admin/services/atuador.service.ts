import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Medicao } from '../models/medicao';
import { TipoAtuador } from '../models/tipo-atuador';
import { RestResponse } from '../models/responses/rest-response';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtuadorService {

  constructor(private http: HttpClient) { }

  public getTipos(): Observable<RestResponse<Array<TipoAtuador>>> {
    return this.http.get<RestResponse<Array<TipoAtuador>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'atuadores/tipos');
  }

  public getMedicoes(idAtuador: string): Observable<RestResponse<Array<Medicao>>> {
    return this.http.get<RestResponse<Array<Medicao>>>(environment.URL_API_LAB_STREAM_CONTROL + 'atuadores/' + idAtuador + '/medicoes');
  }

  public getStatuses(ids: Array<string>): Observable<Array<any>> {
    return this.http.post<Array<any>>(environment.URL_API_LAB_STREAM_CONTROL + 'atuadores/statuses', ids);
  }
}
