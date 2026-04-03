import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Medicao } from '../models/medicao';
import { TipoSensor } from '../models/tipo-sensor';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  constructor(private http: HttpClient) { }

  public getTipos(): Observable<RestResponse<Array<TipoSensor>>> {
    return this.http.get<RestResponse<Array<TipoSensor>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'sensores/tipos');
  }

  public getMedicoes(idSensor: string): Observable<RestResponse<Array<Medicao>>> {
    return this.http.get<RestResponse<Array<Medicao>>>(environment.URL_API_LAB_STREAM_CONTROL + 'sensores/' + idSensor + '/medicoes');
  }
}
