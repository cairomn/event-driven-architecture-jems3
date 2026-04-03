import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Piso } from 'src/app/admin/models/piso';
import { RestResponse } from 'src/app/admin/models/responses/rest-response';
import { Sala } from 'src/app/admin/models/sala';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { PisoService } from 'src/app/admin/services/piso.service';
import { SalaService } from 'src/app/admin/services/sala.service';

@Component({
  selector: 'app-sala-form',
  templateUrl: './sala-form.component.html',
  styleUrls: ['./sala-form.component.scss']
})
export class SalaFormComponent implements OnInit {

  @Input() isRota: boolean = true;

  public isAlteracaoLoad: boolean = false;
  public instituicao: Instituicao = {'id': ''} as Instituicao;
  public bloco: Bloco = {'id': ''} as Bloco;
  public piso: Piso = {'id': ''} as Piso;
  public sala: Sala = {'id': ''} as Sala;
  public pisos: Array<Piso> = [];
  public blocos: Array<Bloco> = [];
  public instituicoes: Array<Instituicao> = [];

  public idInstituicao: string = '';
  public idBloco: string = '';
  public idAndar: string = '';

  public errosForm: FormValidation = new FormValidation();

  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _toastr: ToastrService,
    private _salaService: SalaService,
    private _pisoService: PisoService,
    private _blocoService: BlocoService,
    private _instituicaoService: InstituicaoService
  ) { }

  ngOnInit(): void {
    this._carregarInstituicoes();
    const id = this._route.snapshot.paramMap.get('id');

    this._route.params.subscribe(params => {
      if (params['id']) {
        this.isAlteracaoLoad = true;
        this._carregarSala(id);
      }
    });
  }

  public salvar(): void {
    this.errosForm = this._isSalaValida();
    
    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this.sala.piso = this.piso;
    this._salaService.salvar(this.sala).subscribe((sala) => {
      if (this.isRota) {
        this._toastr.success("Cadastrado com sucesso.");
        this.cancelar();
      }
    }, err => {
      this._toastr.error(err.error.message);
    });
  }

  public cancelar() {
    this._router.navigateByUrl('/admin/instituicao/sala');
  }

  public getBlocos(idInstituicao: string) {
    this.piso.id = '';
    this.bloco.id = '';
    this._carregarBlocos(idInstituicao);
  }

  public getPisos(idBloco: string) {
    this._carregarPisos(idBloco);
  }

  private _isSalaValida(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.sala.nome == '' || this.sala.nome == null) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('nome', 'Campo obrigatório.'));
    }

    if (this.piso.id == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('piso', 'Campo obrigatório.'));
    }

    return formValidation;
  }

  private _carregarInstituicoes() {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    }, (err: RestResponse<Instituicao>) => {
      this._toastr.error(err.message);
    });
  }
  
  private _carregarBlocos(id: string) {
    this._instituicaoService.listarBlocos(id).subscribe((blocos) => {
      this.blocos = blocos.data;
    }, (err: RestResponse<Bloco>) => {
      this._toastr.error(err.message);
    });
  }

  private _carregarPisos(id: string) {
    this._blocoService.listarPisos(id).subscribe((pisos) => {
      this.pisos = pisos.data;
    }, (err: RestResponse<Piso>) => {
      this._toastr.error(err.message);
    });
  }

  private _carregarSala(id: string) {
    this._salaService.get(id).subscribe((sala) => {
      this.sala = sala.data;

      if (this.isAlteracaoLoad) {
        this._pisoService.get(this.sala.piso.id).subscribe(piso => {
          this.piso.id = piso.data.id;
          this._carregarPisos(piso.data.bloco.id);

          this._blocoService.listarPorId(piso.data.bloco.id).subscribe(bloco => {
            this.bloco.id = bloco.data.id;
            this.instituicao.id = bloco.data.instituicao.id;
            this._carregarBlocos(bloco.data.instituicao.id);
            this.isAlteracaoLoad = false;
          });
        });
      }
    }, (err: RestResponse<Sala>) => {
      this._toastr.error(err.message);
    });
  }
}
