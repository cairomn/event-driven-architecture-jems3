import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Piso } from 'src/app/admin/models/piso';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { PisoService } from 'src/app/admin/services/piso.service';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { RestResponse } from 'src/app/admin/models/responses/rest-response';


@Component({
  selector: 'app-piso-form',
  templateUrl: './piso-form.component.html',
  styleUrls: ['./piso-form.component.scss']
})
export class PisoFormComponent implements OnInit {

  @Input() isRota: boolean = true;

  public isAlteracaoLoad = false;
  public instituicao: Instituicao = {'id': ''} as Instituicao;
  public bloco: Bloco = {'id': ''} as Bloco;
  public piso: Piso = {'id': ''} as Piso;
  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];

  public errosForm: FormValidation = new FormValidation();

  constructor(
    private _router: Router,
    private _toastr: ToastrService,
    private _route: ActivatedRoute,
    private _pisoService: PisoService,
    private _blocoService: BlocoService,
    private _instituicaoService: InstituicaoService,
  ) { }

  ngOnInit(): void {
    this._carregarInstituicoes();

    this._route.params.subscribe(params => {
      if (params['id']) {
        this.isAlteracaoLoad = true;
        this._carregarPiso(params['id']);
      }
    });
  }

  public salvar() {
    let isAndarValido = this._isAndarValido();

    if (!isAndarValido) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this.piso.bloco = this.bloco;
    this._pisoService.salvar(this.piso).subscribe((piso) => {
      if (this.isRota) {
        this._toastr.success("Cadastrado com sucesso.");
        this.cancelar();
      }
    }, (err) => {
      this._toastr.error(err.error.message);
    });
  }

  public cancelar() {
    this._router.navigateByUrl('/admin/instituicao/piso');
  }

  public getBlocos(idInstituicao: string) {
    this._carregarBlocos(idInstituicao);
  }

  private _isAndarValido(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.piso.nome == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('nome', 'Campo obrigatório.'));
    }

    if (this.bloco.id == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('bloco', 'Campo obrigatório.'));
    }

    return formValidation;
  }

  private _carregarInstituicoes() {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBlocos(idInstituicao: string) {
    this._blocoService.listar(idInstituicao).subscribe((blocos) => {
      this.blocos = blocos.data;

      this.blocos.forEach((bloco) => {
        if (bloco.id == this.piso.bloco.id && this.isAlteracaoLoad) {
          this.piso.bloco.instituicao.id = bloco.instituicao.id;
          this.isAlteracaoLoad = false;
        }
      });

    }, (err: RestResponse<Bloco>) => {
      this._toastr.error(err.message);
    });
  }

  private _carregarPiso(id: string) {
    this._pisoService.get(id).subscribe((piso) => {
      this.piso = piso.data;
      
      if (piso.data.bloco.id && this.isAlteracaoLoad) {
        this._blocoService.listarPorId(piso.data.bloco.id).subscribe(bloco => {
          this.instituicao.id = bloco.data.instituicao.id;
          this.bloco.id = piso.data.bloco.id;
          this._carregarBlocos(bloco.data.instituicao.id);
        });
      }
    }, (err: RestResponse<Piso>) => {
      this._toastr.error(err.message);
    });
  }

}
