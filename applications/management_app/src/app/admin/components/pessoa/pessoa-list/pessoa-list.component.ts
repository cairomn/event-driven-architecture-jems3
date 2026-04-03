import { Component, OnInit } from '@angular/core';
import { PagedResponse } from "../../../models/responses/paged-response";
import { Usuario } from "../../../models/usuario";
import { UsuarioService } from "../../../services/usuario.service";
import { Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-pessoa-list',
  templateUrl: './pessoa-list.component.html',
  styleUrls: ['./pessoa-list.component.scss']
})
export class PessoaListComponent implements OnInit {

  public usuarios: Array<Usuario> = [];
  public paged: PagedResponse<any>;

  constructor(
    private _router: Router,
    private _toastr: ToastrService,
    private _modalService: NgbModal,
    private _usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
    this._carregarUsuarios();
  }

  public paginar(numPag: number): void {
    this._carregarUsuarios(numPag);
  }

  public navegarParaCadastro(): void {
    this._router.navigateByUrl('/usuario/adicionar');
  }

  public navegarParaEdicao(id: number): void {
    this._router.navigateByUrl(`/usuario/${id}/editar`);
  }

  private _carregarUsuarios(numPag?: number) {
    this._usuarioService.paginar().subscribe((usuarios) => {
      this.paged = usuarios;
      this.usuarios = usuarios.content;
    }, err => {
      this._toastr.error(err.message)
    });
  }

}
