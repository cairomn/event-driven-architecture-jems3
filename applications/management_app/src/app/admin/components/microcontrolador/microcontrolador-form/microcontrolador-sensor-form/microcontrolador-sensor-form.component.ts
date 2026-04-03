import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Sensor } from 'src/app/admin/models/sensor';
import { TipoSensor } from 'src/app/admin/models/tipo-sensor';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { NameGeneratorService } from 'src/app/admin/services/name-generator.service';

@Component({
  selector: 'app-microcontrolador-sensor-form',
  templateUrl: './microcontrolador-sensor-form.component.html',
  styleUrls: ['./microcontrolador-sensor-form.component.scss']
})
export class MicrocontroladorSensorFormComponent implements OnInit {

  @Input() sensor: Sensor = {} as Sensor;
  @Input() tiposSensores: Array<TipoSensor> = [];

  public errosForm: FormValidation = new FormValidation();

  constructor(
    private _toastr: ToastrService,
    public activeModal: NgbActiveModal,
    private _nameGeneratorService: NameGeneratorService
  ) { }

  ngOnInit(): void {
    this.sensor.tipoSensor = -1;

    if (this.sensor.apelido == undefined || this.sensor.apelido == null || this.sensor.apelido == "") {
      this.sensor.apelido = this._nameGeneratorService.getRandomName();
    }
  }

  public addSensor(): void {
    this.errosForm = this._isSensorValido(this.sensor);

    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this.activeModal.close(this.sensor);
  }

  private _isSensorValido(sensor: Sensor): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (sensor.apelido == "") {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('apelido', 'Campo obrigatório.'));
    }

    if (sensor.tipoSensor == -1) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('tipoSensor', 'Campo obrigatório.'));
    }

    return formValidation;
  }
}
