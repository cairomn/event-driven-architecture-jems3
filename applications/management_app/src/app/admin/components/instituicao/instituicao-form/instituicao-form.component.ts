import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { Instituicao } from 'src/app/admin/models/instituicao';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';

@Component({
  selector: 'app-instituicao-form',
  templateUrl: './instituicao-form.component.html',
  styleUrls: ['./instituicao-form.component.scss']
})
export class InstituicaoFormComponent implements OnInit, OnDestroy {

  @Input() isRota: boolean = true;
  @Input() idInstituicao: string | null = null;

  public instituicao: Instituicao = {} as Instituicao;
  public errosForm: FormValidation = new FormValidation;

  /**
   * Construtor da classe.
   * 
   * @param _router 
   * @param _route 
   * @param _instituicaoService 
   */
  constructor(
    private _router: Router,
    public toster: ToastrService,
    private _route: ActivatedRoute,
    private _instituicaoService: InstituicaoService,
  ) { }

  ngOnInit(): void {
    let id: string | null = this._route.snapshot.paramMap.get('id') ? this._route.snapshot.paramMap.get('id') : null;

    if (!this.isRota) id = this.idInstituicao;

    if (id !== null) {
      this._carregarInstituicao(id);
    } else {
      this.instituicao.nome = '';
      this.instituicao.cnpj = '';
    }
  }
  
  ngOnDestroy(): void {
    this.isRota = true;
    this.idInstituicao = null;
  }

  public salvar() {
    let isInstituicaoValida = this._isInstituicaoValida();
    this.errosForm = isInstituicaoValida;

    if (!isInstituicaoValida.isValid) {
      this.toster.error("Falha na validação dos dados.");
      return;
    }

    this._instituicaoService.salvar(this.instituicao).subscribe((instituicao) => {
      this.toster.success("Cadastrado com sucesso.");
      this._router.navigateByUrl('/admin/instituicao');
    }, err => {
      this.toster.error(err.error.message);
    });
  }

  public cancelar() {
    this._router.navigateByUrl('/admin/instituicao');
  }

  private _isInstituicaoValida(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.instituicao.nome == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('nome', 'Campo obrigatório.'));
    }

    if (this.instituicao.cnpj == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('cnpj', 'Campo obrigatório.'));
    }

    return formValidation;
  }

  private _carregarInstituicao(id: string) {
    this._instituicaoService.listarPorId(id).subscribe((instituicao) => {
      this.instituicao = instituicao.data;
    });
  }
}
