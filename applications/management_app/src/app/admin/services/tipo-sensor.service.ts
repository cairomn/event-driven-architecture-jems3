import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoSensor } from '../models/tipo-sensor';
import { RestResponse } from '../models/responses/rest-response';

@Injectable({
  providedIn: 'root'
})
export class TipoSensorService {

  constructor(private http: HttpClient) { }

  public getTiposSensores(): Observable<RestResponse<Array<TipoSensor>>> {
    return this.http.get<RestResponse<Array<TipoSensor>>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'tipos-sensores');
  }
}
