import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Piso } from 'src/app/admin/models/piso';
import { PisoService } from 'src/app/admin/services/piso.service';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';
import { ToastrService } from 'ngx-toastr';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';

@Component({
  selector: 'app-piso-list',
  templateUrl: './piso-list.component.html',
  styleUrls: ['./piso-list.component.scss']
})
export class PisoListComponent implements OnInit {

  @Input() isRota: boolean = true;

  public instituicoes: Array<Instituicao> = [];
  public blocos: Array<Bloco> = [];
  public pisos: Array<Piso> = [];
  public idInstituicao: string = '';
  public idBloco: string = '';
  public paged: PagedResponse<any>;

  /**
   * Construtor da classe.
   * 
   * @param _router 
   * @param _route 
   * @param _pisoService 
   */
  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _toastr: ToastrService,
    private _modalService: NgbModal,
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

  public abrirModal(piso: Piso): void {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.id = piso.id;
    ref.componentInstance.title = 'Remover Piso';
    ref.componentInstance.conteudo = 'Deseja realmente remover esse piso?';

    ref.result.then((result) => {
      this._deletarPiso(result);
    });
  }

  public paginar(numPag?: number) {
    this._carregarPisosPorBloco(this.idBloco, numPag)
  }

  private _carregarInstituicoes() {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBlocosPorInstituicao(idInstituicao: string) {
    this._blocoService.listar(idInstituicao).subscribe((blocos) => {
      this.blocos = blocos.data;
    });
  }

  private _carregarPisosPorBloco(idBloco: string, numPag?: number) {
    this._blocoService.listarPisosPaginados(idBloco, numPag).subscribe((pisos) => {
      this.paged = pisos;
      this.pisos = pisos.content;
    });
  }

  private _deletarPiso(idPiso: string): void {
    this._pisoService.listarSalas(idPiso).subscribe((salas) => {
      if (salas.data.length > 0) {
        this._toastr.error('Existem salas vinculados ao piso.');
        return;
      }

      this._pisoService.deletar(idPiso).subscribe(() => {
        this._toastr.error('Piso removido.');
        this.idBloco = '';
        this.idInstituicao = '';
      });
    })
  }
}
