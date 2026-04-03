import { Component, OnInit } from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {Usuario} from "../../../models/usuario";
import {PagedResponse} from "../../../models/responses/paged-response";
import {UsuarioService} from "../../../services/usuario.service";

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent implements OnInit {

  public usuarios: Array<Usuario> = [];
  public paged: PagedResponse<any>;

  constructor(
    private _router: Router,
    private _toastr: ToastrService,
    private _usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
    this._carregarUsuarios();
  }

  public paginar(numPag: number) {
    this._carregarUsuarios(numPag);
  }

  private _carregarUsuarios(numPage?: number) {
    this._usuarioService.paginar().subscribe((usuarios: PagedResponse<Usuario>) => {
      this.usuarios = usuarios.content;
    });
  }
}
