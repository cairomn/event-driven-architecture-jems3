import { Component, Input, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Piso } from 'src/app/admin/models/piso';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';
import { Sala } from 'src/app/admin/models/sala';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { PisoService } from 'src/app/admin/services/piso.service';
import { SalaService } from 'src/app/admin/services/sala.service';

@Component({
  selector: 'app-sala-list',
  templateUrl: './sala-list.component.html',
  styleUrls: ['./sala-list.component.scss']
})
export class SalaListComponent implements OnInit {

  @Input() isRota: boolean = true;

  public salas: Array<Sala> = [];
  public pisos: Array<Piso> = [];
  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];
  public paged: PagedResponse<any>;

  public idInstituicao: string = '';
  public idBloco: string = '';
  public idPiso: string = '';

  /**
   * Construtor da classe.
   * 
   * @param _router 
   * @param _route 
   * @param _toastr 
   * @param _modalService 
   * @param _salaService 
   * @param _pisoService 
   * @param _blocoService 
   * @param _instituicaoService 
   */
  constructor(
    private _toastr: ToastrService,
    private _modalService: NgbModal,
    private _salaService: SalaService,
    private _pisoService: PisoService,
    private _blocoService: BlocoService,
    private _instituicaoService: InstituicaoService
  ) { }

  ngOnInit(): void {
    this._carregarInstituicoes();
  }

  public getBlocos(idInstituicao: string) {
    if (idInstituicao === '') {
      this.blocos = [];
      this.pisos = [];
      return;
    } 
    
    this._carregarBlocosPorInstituicao(idInstituicao);
  }

  public getPisos(idBloco: string) {
    if (idBloco === '') {
      this.pisos = [];
      return;
    } 
    
    this._carregarPisosPorBloco(idBloco);
  }
  
  public getSalas(idPiso: string) {
    this._carregarSalasPorPiso(idPiso);
  }

  public abrirModal(piso: Piso) {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.id = piso.id;
    ref.componentInstance.title = 'Remover Sala';
    ref.componentInstance.conteudo = 'Deseja realmente remover essa sala?';

    ref.result.then((result) => {
      this._deletarSala(result);
    });
  }

  public paginar(numPag: number) {
    this._carregarSalasPorPiso(this.idPiso, numPag);
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

  private _carregarSalasPorPiso(idPiso: string, numPag?: number): void {
    this._pisoService.listarSalasPaginadas(idPiso, numPag).subscribe((salas) => {
      this.paged = salas;
      this.salas = salas.content;
    });
  }

  private _deletarSala(idSala: string): void {
    this._salaService.deletar(idSala).subscribe(() => {
      this._toastr.error('Sala removida.');
      this.idPiso = '';
      this.idBloco = '';
      this.idInstituicao = '';
    });
  }
}
