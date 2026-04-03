import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Acao } from 'src/app/admin/models/acao';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Microcontrolador } from 'src/app/admin/models/microcontrolador';
import { Piso } from 'src/app/admin/models/piso';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';
import { Sala } from 'src/app/admin/models/sala';
import { AcaoModelComponent } from 'src/app/admin/partials/modals/acao-model/acao-model.component';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';
import { AtuadorService } from 'src/app/admin/services/atuador.service';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { MicrocontroladorService } from 'src/app/admin/services/microcontrolador.service';
import { PisoService } from 'src/app/admin/services/piso.service';
import { SalaService } from 'src/app/admin/services/sala.service';

@Component({
  selector: 'app-microcontrolador-list',
  templateUrl: './microcontrolador-list.component.html',
  styleUrls: ['./microcontrolador-list.component.scss']
})
export class MicrocontroladorListComponent implements OnInit {

  @Input() isRota: boolean = true;

  public salas: Array<Sala> = [];
  public pisos: Array<Piso> = [];
  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];
  public microcontroladores: Array<Microcontrolador> = [];
  public paged: PagedResponse<any>;

  public idInstituicao: string = '';
  public idBloco: string = '';
  public idPiso: string = '';
  public idSala: string = '';

  /**
   * Construtor da classe.
   *
   * @param _router
   * @param _instituicaoService
   * @param _blocoService
   * @param _pisoService
   * @param _microcontroladorService
   */
  constructor(
    private _router: Router,
    private _instituicaoService: InstituicaoService,
    private _blocoService: BlocoService,
    private _pisoService: PisoService,
    private _salaService: SalaService,
    private _microcontroladorService: MicrocontroladorService,
    private _atuadoresService: AtuadorService,
    private _toastr: ToastrService,
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

  public paginar(numPag: number) {
    this._carregarMicrocontroladoresPorSala(this.idSala, numPag);
  }

  public abrirModal(microcontrolador: Microcontrolador) {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.id = microcontrolador.id;
    ref.componentInstance.title = 'Remover Microcontrolador';
    ref.componentInstance.conteudo = 'Deseja realmente remover esse microcontrolador?';

    ref.result.then((result) => {
      this._deletarMicrocontrolador(result);
    });
  }

  public abrirModalAcoes(microcontrolador: Microcontrolador) {
    let atuadorIDs = [];

    microcontrolador.atuadores.forEach(atuador => {
      atuadorIDs.push(atuador.id);
    });

    this._atuadoresService.getStatuses(atuadorIDs).subscribe(resp => {
      resp.forEach((status, index) => {
        if (status) {
          microcontrolador.atuadores[index].statusCheck = {
            id: status.id,
            on: status.on,
            temp: status.temp,
            mode: status.mode
          };
        }
      });

      let ref: NgbModalRef = this._modalService.open(AcaoModelComponent, { size: 'lg' });
      ref.componentInstance.microcontrolador = microcontrolador;

      ref.result.then((result) => {
        let acao = {} as Acao;
        acao.actuatorID = result.actID;
        acao.valor = result.valor;
        acao.microID = result.microID;

        if (result.operation == 1) {
          this._acaoLigaDesligaAr(acao);
        }

        if (result.operation == 2) {
          this._acaoChangeTemp(acao);
        }

        if (result.operation == 3) {
          this._acaoChangeMode(acao);
        }
      });
    });
  }

  public _acaoLigaDesligaAr(acao: Acao) {
    this._microcontroladorService.onOffAirConditioner(acao).subscribe(() => {
      this._toastr.success("Status alterado.");
    }, err => {
      this._toastr.error(err.error.message);
    });
  }

  public _acaoChangeTemp(acao: Acao) {
    this._microcontroladorService.changeTempAirConditioner(acao).subscribe(() => {
      this._toastr.success("Status alterado.");
    }, err => {
      this._toastr.error(err.error.message);
    });
  }

  public _acaoChangeMode(acao: Acao) {
    this._microcontroladorService.changeModeAirConditioner(acao).subscribe(() => {
      this._toastr.success("Status alterado.");
    }, err => {
      this._toastr.error(err.error.message);
    });
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

  private _carregarMicrocontroladoresPorSala(idSala: string, numPag: number = 0) {
    this._salaService.listarMicrocontroladoresPaginados(idSala, numPag).subscribe((microcontroladores) => {
      this.paged = microcontroladores;
      this.microcontroladores = microcontroladores.content;
    });
  }

  private _deletarMicrocontrolador(idMicrocontrolador: string) {
    this._microcontroladorService.deletar(idMicrocontrolador).subscribe(() => {
      this._toastr.error('Microcontrolador removido.');
      this._carregarMicrocontroladoresPorSala(this.idSala);
    });
  }
}
