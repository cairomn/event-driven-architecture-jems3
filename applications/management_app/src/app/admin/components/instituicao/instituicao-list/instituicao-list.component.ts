import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';

@Component({
  selector: 'app-instituicao-list',
  templateUrl: './instituicao-list.component.html',
  styleUrls: ['./instituicao-list.component.scss']
})
export class InstituicaoListComponent implements OnInit {

  @Input() isRota: boolean = true;

  public instituicoes: Array<Instituicao> = [];
  public paged: PagedResponse<any>;

  constructor(
    private _router: Router,
    private _toastr: ToastrService,
    private _modalService: NgbModal,
    private _instituicaoService: InstituicaoService,
  ) { }

  ngOnInit(): void {
    this._carregarInstituicao();
  }

  public abrirModal(instituicao: Instituicao): void {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.id = instituicao.id;
    ref.componentInstance.title = 'Remover Instituição';
    ref.componentInstance.conteudo = 'Deseja realmente remover essa instituição?';

    ref.result.then((result) => {
      this._deletarInstituicao(result);
    });
  }

  public paginar(numPag: number): void {
    this._carregarInstituicao(numPag);
  }

  public navegarParaCadastro(): void {
    this._router.navigateByUrl('/instituicao/adicionar');
  }

  public navegarParaEdicao(id: number): void {
    this._router.navigateByUrl(`/instituicao/${id}/editar`);
  }

  private _carregarInstituicao(numPag?: number) {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.paged = instituicoes;
      this.instituicoes = instituicoes.content;
    }, err => {
      this._toastr.error(err.message)
    });
  }

  private _deletarInstituicao(id: string): void {
    this._instituicaoService.listarBlocos(id).subscribe((blocos) => {
      if (blocos.data.length > 0) {
        this._toastr.error('Existem blocos vinculados a instituição.')
        return;
      }

      this._instituicaoService.deletar(id).subscribe(() => {
        this._toastr.error('Instituição removida.');
        this._carregarInstituicao();
      });
    });
  }
}
