import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Atuador } from 'src/app/admin/models/atuador';
import { Bloco } from 'src/app/admin/models/bloco';
import { Instituicao } from 'src/app/admin/models/instituicao';
import { Microcontrolador } from 'src/app/admin/models/microcontrolador';
import { Piso } from 'src/app/admin/models/piso';
import { Sala } from 'src/app/admin/models/sala';
import { Sensor } from 'src/app/admin/models/sensor';
import { TipoSensor } from 'src/app/admin/models/tipo-sensor';
import { BlocoService } from 'src/app/admin/services/bloco.service';
import { InstituicaoService } from 'src/app/admin/services/instituicao.service';
import { MicrocontroladorService } from 'src/app/admin/services/microcontrolador.service';
import { PisoService } from 'src/app/admin/services/piso.service';
import { TipoAtuadorService } from 'src/app/admin/services/tipo-atuador.service';
import { TipoSensorService } from 'src/app/admin/services/tipo-sensor.service';
import { NameGeneratorService } from 'src/app/admin/services/name-generator.service';
import { MicrocontroladorAtuadorFormComponent } from './microcontrolador-atuador-form/microcontrolador-atuador-form.component';
import { MicrocontroladorSensorFormComponent } from './microcontrolador-sensor-form/microcontrolador-sensor-form.component';
import { FormValidation } from 'src/app/admin/models/validations/form-validation';
import { ErrorMessages } from 'src/app/admin/models/validations/error-messages';
import { ToastrService } from 'ngx-toastr';
import { ModalDeletarComponent } from 'src/app/admin/partials/modals/modal-deletar/modal-deletar.component';

@Component({
  selector: 'app-microcontrolador-form',
  templateUrl: './microcontrolador-form.component.html',
  styleUrls: ['./microcontrolador-form.component.scss']
})
export class MicrocontroladorFormComponent implements OnInit {

  @Input() isRota: boolean = true;
  @Input() microcontrolador: Microcontrolador = {} as Microcontrolador;

  public tiposSensores: Array<TipoSensor> = [];
  public tiposAtuadores: Array<TipoSensor> = [];
  public listaDeAtuadores: Array<Atuador> = [];
  public listaDeSensores: Array<Sensor> = [];
  public instituicoes: Array<Instituicao> = [];
  public blocos: Array<Bloco> = [];
  public pisos: Array<Piso> = [];
  public salas: Array<Sala> = [];

  public idInstituicao: string = '';
  public idBloco: string = '';
  public idPiso: string = '';
  public idSala: string = '';
  public errosForm: FormValidation = new FormValidation;

  /**
   * Construtor da classe.
   * 
   * @param _router 
   * @param _toastr 
   * @param _modalService 
   * @param _instituicaoService 
   * @param _blocoService 
   * @param _pisoService 
   * @param _nameGeneratorService 
   * @param _tipoSensorService 
   * @param _tipoAtuadorService 
   * @param _microcontroladorService 
   */
  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _toastr: ToastrService,
    private _modalService: NgbModal,
    private _instituicaoService: InstituicaoService,
    private _blocoService: BlocoService,
    private _pisoService: PisoService,
    private _nameGeneratorService: NameGeneratorService,
    private _tipoSensorService: TipoSensorService,
    private _tipoAtuadorService: TipoAtuadorService,
    private _microcontroladorService: MicrocontroladorService,
  ) { }

  ngOnInit(): void {
    this.microcontrolador.apelido = this._nameGeneratorService.getRandomName();
    this._carregarInstituicoes();
    this._carregarTiposSensores();
    this._carregarTiposAtuadores();

    this.microcontrolador.sala = {} as Sala;
    this.microcontrolador.sala.id = '';

    this.microcontrolador.sala.piso = {} as Piso;
    this.microcontrolador.sala.piso.id = '';

    this.microcontrolador.sala.piso.bloco = {} as Bloco;
    this.microcontrolador.sala.piso.bloco.id = '';

    this.microcontrolador.sala.piso.bloco.instituicao = {} as Instituicao;
    this.microcontrolador.sala.piso.bloco.instituicao.id = '';

    this.microcontrolador.macAddress = '';
    this.microcontrolador.sensores = [];
    this.microcontrolador.atuadores = [];

    this._route.queryParams.subscribe(params => {
      if (params['instituicao']) {
        this.idInstituicao = params['instituicao'];
        this._carregarBlocosPorInstituicao(params['instituicao']);
      }

      if (params['bloco']) {
        this.idBloco = params['bloco'];
        this._carregarPisosPorBloco(params['bloco']);
      }

      if (params['piso']) {
        this.idPiso = params['piso'];
        this._carregarSalasPorPiso(params['piso']);
      }

      if (params['sala']) {
        this.idSala = params['sala'];
      }
    });

    this._route.params.subscribe(params => {
      if (params['id']) {
        this._carregarMicrocontrolador(params['id']);
      }
    });
  }

  public salvar() {
    this.errosForm = this._isMicrocontroladorValido();

    if (!this.errosForm.isValid) {
      this._toastr.error("Falha na validação dos dados.");
      return;
    }

    this._microcontroladorService.salvar(this.microcontrolador).subscribe((resp) => {
      this._toastr.success("Cadastrado com sucesso.")
      this.cancelar();
    }, (error) => {
      this._toastr.error(error.messages);
    });
  }

  public cancelar() {
    this._router.navigateByUrl(`/admin/microcontrolador`);
  }

  public getBlocos(idInstituicao: string): void {
    if (idInstituicao === '') {
      this.blocos = [];
      this.pisos = [];
      return;
    }

    this._carregarBlocosPorInstituicao(idInstituicao);
  }

  public getPisos(idBloco: string): void {
    if (idBloco === '') {
      this.pisos = [];
      return;
    }

    this._carregarPisosPorBloco(idBloco);
  }

  public getSalas(idPiso: string) {
    this._carregarSalasPorPiso(idPiso);
  }

  public abrirModalRemocaoAtuador(atuador: Atuador): void {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);
    
    ref.componentInstance.id = atuador.apelido;
    ref.componentInstance.title = 'Remover Atuador';
    ref.componentInstance.conteudo = 'Deseja realmente remover esse atuador?';

    ref.result.then((result) => {
      this.microcontrolador.atuadores = this.microcontrolador.atuadores.filter(item => item.apelido !== result);
    });
  }

  public abrirModalRemocaoSensor(sensor: Sensor): void {
    let ref: NgbModalRef = this._modalService.open(ModalDeletarComponent);

    ref.componentInstance.title = 'Remover Sensor';
    ref.componentInstance.conteudo = 'Deseja realmente remover esse sensor?';
    ref.componentInstance.id = sensor.apelido;

    ref.result.then(result => {
      this.microcontrolador.sensores = this.microcontrolador.sensores.filter(item => item.apelido !== result);
    });
  }

  public abrirModalAddAtuador(atuador?: Atuador): void {
    let ref: NgbModalRef = this._modalService.open(MicrocontroladorAtuadorFormComponent);

    ref.componentInstance.atuador = atuador ?? {} as Atuador;
    ref.componentInstance.tiposAtuadores = this.tiposAtuadores;

    ref.result.then((result) => {
      this.microcontrolador.atuadores.push(result);
    });
  }

  public abrirModalAddSensor(sensor?: Sensor): void {
    let ref: NgbModalRef = this._modalService.open(MicrocontroladorSensorFormComponent);

    ref.componentInstance.sensor = sensor ?? {} as Sensor;
    ref.componentInstance.tiposSensores = this.tiposSensores;

    ref.result.then((result) => {
      this.microcontrolador.sensores.push(result);
    });
  }

  public getDescricaoTipoSensor(idSensor: number): string {
    let tpSensor = this.tiposSensores.filter((tipoSensor) => {
      return tipoSensor.id == idSensor;
    });

    return tpSensor[0].descricao ?? '-';
  }

  public getDescricaoTipoAtuador(idAtuador: number): string {
    let tpAtuador = this.tiposAtuadores.filter((tipoAtuador) => {
      return tipoAtuador.id == idAtuador;
    });

    return tpAtuador[0].descricao ?? '-';
  }

  private _carregarInstituicoes(): void {
    this._instituicaoService.paginar().subscribe((instituicoes) => {
      this.instituicoes = instituicoes.content;
    });
  }

  private _carregarBlocosPorInstituicao(idInstituicao: string): void {
    this._instituicaoService.listarBlocos(idInstituicao).subscribe((blocos) => {
      this.blocos = blocos.data;
    });
  }

  private _carregarPisosPorBloco(idBloco: string): void {
    this._blocoService.listarPisos(idBloco).subscribe((pisos) => {
      this.pisos = pisos.data;
    });
  }

  private _carregarSalasPorPiso(idPiso: string): void {
    this._pisoService.listarSalas(idPiso).subscribe((salas) => {
      this.salas = salas.data;
    });
  }

  private _isMicrocontroladorValido(): FormValidation {
    let formValidation: FormValidation = new FormValidation();

    if (this.microcontrolador.apelido == '') {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('apelido', 'Campo obrigatório.'));
    }

    if (this.microcontrolador.macAddress == '' || this.microcontrolador.macAddress == null) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('macAddress', 'Campo obrigatório.'));
    }

    if (!this.microcontrolador.sala || this.microcontrolador.sala.id == '' || this.microcontrolador.sala.id == null) {
      formValidation.isValid = false;
      formValidation.messages.push(new ErrorMessages('sala', 'Campo obrigatório.'));
    }

    if ((this.microcontrolador.sensores.length + this.microcontrolador.atuadores.length) < 1) {
      formValidation.isValid = false;
      this._toastr.error("Insira ao menos um atuador ou sensor.");
    }

    return formValidation;
  }

  private _carregarTiposSensores(): void {
    this._tipoSensorService.getTiposSensores().subscribe((tiposSensores) => {
      this.tiposSensores = tiposSensores.data;
    });
  }

  private _carregarTiposAtuadores(): void {
    this._tipoAtuadorService.getTiposAtuador().subscribe((tiposAtuadores) => {
      this.tiposAtuadores = tiposAtuadores.data;
    });
  }

  private _carregarMicrocontrolador(id: string): void {
    this._microcontroladorService.listarPorId(id).subscribe((microcontrolador) => {
      microcontrolador.data.sala = microcontrolador.data.sala ?? { id: this.idSala } as Sala;
      microcontrolador.data.sala.piso = microcontrolador.data.sala.piso ?? { id: this.idPiso } as Piso;
      microcontrolador.data.sala.piso.bloco = microcontrolador.data.sala.piso.bloco ?? { id: this.idBloco } as Bloco;
      microcontrolador.data.sala.piso.bloco.instituicao = microcontrolador.data.sala.piso.bloco.instituicao ?? { id: this.idInstituicao } as Instituicao;
      this.microcontrolador = microcontrolador.data;
    });
  }
}
