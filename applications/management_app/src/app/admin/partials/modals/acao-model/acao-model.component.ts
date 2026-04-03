import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Microcontrolador } from 'src/app/admin/models/microcontrolador';

@Component({
  selector: 'app-acao-model',
  templateUrl: './acao-model.component.html',
  styleUrls: ['./acao-model.component.scss']
})
export class AcaoModelComponent implements OnInit {

  @Input() microcontrolador: Microcontrolador = {} as Microcontrolador;
  private modeSelectedValue: number = 0;
  private tempSelectedValue: number = 21;

  constructor(
    public activeModal: NgbActiveModal
  ) { }

  ngOnInit(): void {
    this.modeSelectedValue = 0;
    this.tempSelectedValue = 21;
  }

  public getNomeTipoAtuador(tipoAtuador) {
    let nomeTipo = '';
    if (tipoAtuador == 1) nomeTipo = 'Ar Condicionado';
    return nomeTipo;
  }

  public changeTemp(temp: number) {
    this.tempSelectedValue = temp;
  }

  public changeMode(mode: number) {
    this.modeSelectedValue = mode;
  }

}
