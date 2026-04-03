import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {UsuarioService} from "../../../services/usuario.service";
import {Usuario} from "../../../models/usuario";
import {FormValidation} from "../../../models/validations/form-validation";
import {ErrorMessages} from "../../../models/validations/error-messages";
import {Role} from "../../../models/role";
import {RoleService} from "../../../services/role.service";

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent implements OnInit {

  @Input() isRota: boolean = true;

  public isAlteracaoLoad: boolean = false;
  public usuario: Usuario = { roles: [] } as Usuario;
  public errosForm: FormValidation = new FormValidation();
  public roles: Array<Role> = [];

  /**
   * Construtor da classe.
   *
   * @param _router
   * @param _route
   * @param _toastr
   * @param _roleService
   * @param _usuarioService
   */
  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _toastr: ToastrService,
    private _roleService: RoleService,
    private _usuarioService: UsuarioService
  ) {
  }

  ngOnInit(): void {
    this._roleService.getRoles().subscribe(roles => {
      this.roles = roles;

      const id: string = this._route.snapshot.paramMap.get('id');
      if (id) {
        this.isAlteracaoLoad = true;
        this._carregarUsuario(parseInt(id));
      }
    }, error => {
      this._toastr.error("Falha ao recuperar regras de usuários: " + error.message);
    });
  }

  public salvar(): void {
    this.errosForm = this._isUsuarioValido();

    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this._usuarioService.salvar(this.usuario).subscribe((resp) => {
      if (this.isRota) {
        this._toastr.success("Cadastrado com sucesso.");
        this.cancelar();
      }
    }, err => {
      this._toastr.error(err.error.message);
    });
  }

  public cancelar(): void {
    this._router.navigateByUrl('/admin/usuario');
  }

  public toggleRole(role: string): void {
    const roleInUser = this.usuario.roles.filter(roleUser => {
      return roleUser == role;
    });

    if (roleInUser.length > 0) {
      this.usuario.roles = this.usuario.roles.filter(roleUser => {
        return roleUser != role;
      });
    } else {
      this.usuario.roles.push(role);
    }
  }

  public isToggleUsuario(role: string): boolean {
    let countRoles: number = this.usuario.roles.filter(roleUser => {
      return roleUser === role;
    }).length;

    return countRoles > 0;
  }

  private _carregarUsuario(id: number): void {
    this._usuarioService.listarPorId(id).subscribe(resp => {
      this.usuario = resp;
    }, error => {
      this._toastr.error("Falha ao recuperar usuário: " + error.message);
    });
  }

  private _isUsuarioValido(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.usuario.nome == '' || this.usuario.nome == null) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('nome', 'Campo obrigatório.'));
    }

    if (this.usuario.email == '' || this.usuario.email == null) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('email', 'Campo obrigatório.'));
    }

    if (this.usuario.roles.length < 1) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('roles', 'Campo obrigatório.'));
    }

    return formValidation;
  }

}
