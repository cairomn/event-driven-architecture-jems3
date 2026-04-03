import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Microcontrolador } from 'src/app/admin/models/microcontrolador';
import { Piso } from 'src/app/admin/models/piso';
import { Sala } from 'src/app/admin/models/sala';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { PisoService } from 'src/app/admin/services/piso.service';
import { SalaService } from 'src/app/admin/services/sala.service';
import { SensorService } from 'src/app/admin/services/sensor.service';
import { AtuadorService } from 'src/app/admin/services/atuador.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MedicaoValoresListComponent } from '../medicao-valores-list/medicao-valores-list.component';

@Component({
  selector: 'app-medicao-list',
  templateUrl: './medicao-list.component.html',
  styleUrls: ['./medicao-list.component.scss']
})
export class MedicaoListComponent implements OnInit {

  public salas: Array<Sala> = [];
  public pisos: Array<Piso> = [];
  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];
  public microcontroladores: Array<Microcontrolador> = [];

  public idInstituicao: string = '';
  public idBloco: string = '';
  public idPiso: string = '';
  public idSala: string = '';

  constructor(
    private _router: Router,
    private _instituicaoService: InstituicaoService,
    private _blocoService: BlocoService,
    private _pisoService: PisoService,
    private _salaService: SalaService,
    private _sensorService: SensorService,
    private _atuadorService: AtuadorService,
    private _modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this._carregarInstituicoes();
  }

  public getBlocos(idInstituicao: string): void {
    if (idInstituicao === '') {
      this.blocos = [];
      this.pisos = [];
      return;
    }

    this._carregarBlocosPorInstituicao(idInstituicao);
  }

  public getPisos(idBloco: string): void {
    if (idBloco === '') {
      this.pisos = [];
      return;
    }

    this._carregarPisosPorBloco(idBloco);
  }

  public getSalas(idPiso: string) {
    this._carregarSalasPorPiso(idPiso);
  }

  public navegarParaCadastro(): void {
    this._router.navigateByUrl('/microcontrolador/adicionar');
  }

  public navegarParaEdicao(id: number) {
    this._router.navigate(['/microcontrolador', id, 'editar'], {
      queryParams: {
        instituicao: this.idInstituicao,
        bloco: this.idBloco,
        piso: this.idPiso,
        sala: this.idSala
      }
    });
  }

  public getMicrocontroladores(idSala: string): void {
    this._carregarMicrocontroladoresPorSala(idSala);
  }

  public abrirModalMedicao(list: any) {
    if (list.tipo == 'ATUADOR') {
      this._atuadorService.getMedicoes(list.id).subscribe((medicoes) => {
        let ref: NgbModalRef = this._modalService.open(MedicaoValoresListComponent, {
          size: 'lg',
        });

        ref.componentInstance.medicoes = medicoes;
      });
    }

    if (list.tipo == 'SENSOR') {
      this._sensorService.getMedicoes(list.id).subscribe((medicoes) => {
        let ref: NgbModalRef = this._modalService.open(MedicaoValoresListComponent, {
          size: 'lg',
        });

        ref.componentInstance.medicoes = medicoes;
      });
    }
  }

  public getAtuadoresSensores() {
    let list: Array<Object> = [];

    this.microcontroladores.forEach(microcontrolador => {
      microcontrolador.atuadores.forEach(atuador => {
        let nomeTipo = '';
        if (atuador.tipoAtuador == 1) nomeTipo = 'Ar Condicionado';

        list.push({
          microcontroladorID: microcontrolador.id,
          microcontroladorNome: microcontrolador.apelido,
          microcontroladorMacAddress: microcontrolador.macAddress,
          id: atuador.id,
          nome: atuador.apelido,
          tipo: 'ATUADOR',
          nomeTipo: nomeTipo,
          status: atuador.status,
        });
      });

      microcontrolador.sensores.forEach(sensor => {
        let nomeTipo = '';

        if (sensor.tipoSensor == 1) nomeTipo = 'Sensor de Temperatura';
        if (sensor.tipoSensor == 2) nomeTipo = 'Sensor de Umidade';
        if (sensor.tipoSensor == 3) nomeTipo = 'Sensor de CO2';
        if (sensor.tipoSensor == 4) nomeTipo = 'Sensor de Movimento';
        if (sensor.tipoSensor == 5) nomeTipo = 'Sensor de Iluminação';

        list.push({
          microcontroladorID: microcontrolador.id,
          microcontroladorNome: microcontrolador.apelido,
          microcontroladorMacAddress: microcontrolador.macAddress,
          id: sensor.id,
          nome: sensor.apelido,
          tipo: 'SENSOR',
          nomeTipo: nomeTipo,
          status: sensor.status,
        });
      });
    });

    return list;
  }

  private _carregarInstituicoes() {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBlocosPorInstituicao(idInstituicao: string) {
    this._instituicaoService.listarBlocos(idInstituicao).subscribe((blocos) => {
      this.blocos = blocos.data;
    });
  }

  private _carregarPisosPorBloco(idBloco: string) {
    this._blocoService.listarPisos(idBloco).subscribe((pisos) => {
      this.pisos = pisos.data;
    });
  }

  private _carregarSalasPorPiso(idPiso: string) {
    this._pisoService.listarSalas(idPiso).subscribe((salas) => {
      this.salas = salas.data;
    });
  }

  private _carregarMicrocontroladoresPorSala(idSala: string) {
    this._salaService.listarMicrocontroladores(idSala).subscribe((microcontroladores) => {
      this.microcontroladores = microcontroladores.data;
    });
  }

}
