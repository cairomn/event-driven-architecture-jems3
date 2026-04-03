import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoAtuador } from '../models/tipo-atuador';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class TipoAtuadorService {

  constructor(private http: HttpClient) { }

  public getTiposAtuador(): Observable<RestResponse<Array<TipoAtuador>>> {
    return this.http.get<RestResponse<Array<TipoAtuador>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'tipos-atuadores');
  }
}
