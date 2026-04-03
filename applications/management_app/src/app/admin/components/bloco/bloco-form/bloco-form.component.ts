import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { RestResponse } from 'src/app/admin/models/responses/rest-response';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';

@Component({
  selector: 'app-bloco-form',
  templateUrl: './bloco-form.component.html',
  styleUrls: ['./bloco-form.component.scss']
})
export class BlocoFormComponent implements OnInit {

  @Input() isRota: boolean = true;
  @Input() idInstituicao: string | null = null;
  @Input() idBloco: string | null = null;

  @Output() aposSalvarEvento: EventEmitter<Bloco> = new EventEmitter<Bloco>();
  @Output() cancelarCadastroEvento: EventEmitter<null> = new EventEmitter<null>();

  public bloco: Bloco = {} as Bloco;
  public instituicoes: Array<Instituicao> = [];
  public errosForm: FormValidation = new FormValidation();

  constructor(
    private _router: Router,
    private _location: Location,
    private _route: ActivatedRoute,
    private _toastr: ToastrService,
    private _blocoService: BlocoService,
    private _instituicaoService: InstituicaoService
  ) { }

  ngOnInit(): void {
    this._carregarInstituicoes();

    this._route.params.subscribe(params => {
      if (params['id']) {
        this._carregarBloco(params['id']);
      } else {
        this.bloco.nome = '';
        this.bloco.instituicao = {} as Instituicao;
        this.bloco.instituicao.id = '';
      }
    });
  }

  public salvar() {
    this.errosForm = this._isBlocoValido();

    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this._blocoService.salvar(this.bloco).subscribe((bloco) => {
      if (this.isRota) {
        this._toastr.success("Cadastrado com sucesso.");
        this._router.navigateByUrl('/admin/instituicao/bloco');
      } else {
        this.aposSalvarEvento.emit(bloco.data);
      }
    }, (err: RestResponse<Bloco>) => {
      this._toastr.error(err.message);
    });
  }

  public cancelar() {
    if (this.isRota) {
      this._location.back();
    } else {
      this.cancelarCadastroEvento.emit();
    }
  }

  public atualizarInstituicaoBloco(idInstituicao: string) {
    this.bloco.instituicao.id = idInstituicao;
  }

  private _isBlocoValido(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.bloco.nome == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('nome', 'Campo obrigatório.'));
    }

    if (this.bloco.instituicao.id == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('instituicao', 'Campo obrigatório.'));
    }

    return formValidation;
  }

  private _carregarInstituicoes() {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBloco(id: string) {
    this._blocoService.listarPorId(id).subscribe((bloco) => {
      this.bloco = bloco.data;
    }, (err: RestResponse<Bloco>) => {
      this._toastr.error(err.message);
    });
  }
}
