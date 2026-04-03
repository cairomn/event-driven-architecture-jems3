import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';

@Component({
  selector: 'app-bloco-list',
  templateUrl: './bloco-list.component.html',
  styleUrls: ['./bloco-list.component.scss']
})
export class BlocoListComponent implements OnInit {

  @Input() isRota: boolean = true;

  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];
  public idInstituicao: string = '';
  public paged: PagedResponse<any>;

  /**
   * Construtor da classe.
   * 
   * @param _router 
   * @param _route 
   * @param _blocoService 
   */
  constructor(
    private _toastr: ToastrService,
    private _modalService: NgbModal,
    private _blocoService: BlocoService,
    private _instituicaoService: InstituicaoService
  ) { }

  ngOnInit(): void {
    this._carregarInstituicao();
  }

  public getBlocos(idInstituicao: string) {
    this.idInstituicao = idInstituicao;
    if (idInstituicao != '') {
      this._carregarBlocos(idInstituicao);
    }
  }

  public getUrlRotaCadastro() {
    return ['/admin/instituicao/bloco/inserir'];
  }

  public abrirModal(bloco: Bloco): void {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.id = bloco.id;
    ref.componentInstance.title = 'Remover Bloco';
    ref.componentInstance.conteudo = 'Deseja realmente remover esse bloco?';

    ref.result.then((result) => {
      this._deletarBloco(result);
    });
  }

  public paginar(numPage?: number, pagSize?: number) {
    this._carregarBlocos(this.idInstituicao, numPage);
  }

  private _carregarInstituicao(): void {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBlocos(idInstituicao: string, numPag?: number): void {
    this._blocoService.paginar(idInstituicao, numPag).subscribe((blocos) => {
      this.paged = blocos;
      this.blocos = blocos.content;
    });
  }

  private _deletarBloco(idBloco: string): void {
    this._blocoService.listarPisos(idBloco).subscribe((pisos) => {
      if (pisos.data.length > 0) {
        this._toastr.error('Existem pisos vinculados ao bloco.');
        return;
      }

      this._blocoService.deletar(idBloco).subscribe(() => {
        this._toastr.error('Bloco removido.');
        this.idInstituicao = '';
      });
    });
  }
}
