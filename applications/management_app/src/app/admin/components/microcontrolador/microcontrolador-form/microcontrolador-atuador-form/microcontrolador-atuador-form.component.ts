import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Atuador } from 'src/app/admin/models/atuador';
import { TipoAtuador } from 'src/app/admin/models/tipo-atuador';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { NameGeneratorService } from 'src/app/admin/services/name-generator.service';

@Component({
  selector: 'app-microcontrolador-atuador-form',
  templateUrl: './microcontrolador-atuador-form.component.html',
  styleUrls: ['./microcontrolador-atuador-form.component.scss']
})
export class MicrocontroladorAtuadorFormComponent implements OnInit {

  @Input() atuador: Atuador = {} as Atuador;
  @Input() tiposAtuadores: Array<TipoAtuador> = [];

  public errosForm: FormValidation = new FormValidation();

  constructor(
    private _toastr: ToastrService,
    public activeModal: NgbActiveModal,
    private _nameGeneratorService: NameGeneratorService
  ) {}

  ngOnInit(): void {
    this.atuador.tipoAtuador = -1;

    if (this.atuador.apelido == undefined || this.atuador.apelido == null) {
      this.atuador.apelido = this._nameGeneratorService.getRandomName();
    }
  }

  public addAtuador(): void {
    this.errosForm = this._isAtuadorValido(this.atuador);

    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this.activeModal.close(this.atuador);
  }

  private _isAtuadorValido(atuador: Atuador): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (atuador.apelido == "") {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('apelido', 'Campo obrigatório.'));
    }

    if (atuador.tipoAtuador == -1) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('tipoAtuador', 'Campo obrigatório.'));
    }

    return formValidation;
  }
}
