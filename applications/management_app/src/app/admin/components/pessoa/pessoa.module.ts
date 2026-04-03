import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';
import { PessoaFormComponent } from './pessoa-form/pessoa-form.component';
import {PartialsAdminModule} from "../../partials/partials-admin.module";
import {PipesModule} from "../../../pipes/pipes.module";
import {RouterModule} from "@angular/router";
import {SharedModule} from "../../../shared/shared.module";
import {NgxMaskModule} from "ngx-mask";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {InstituicaoRoutingModule} from "../instituicao/instituicao-routing.module";
import {ToastrModule} from "ngx-toastr";
import {DirectivesModule} from "../../../directives/directives.module";

@NgModule({
  declarations: [
    PessoaListComponent,
    PessoaFormComponent
  ],
  imports: [
    CommonModule,
    PartialsAdminModule,
    PipesModule,
    RouterModule,
    SharedModule,
    NgxMaskModule.forRoot(),
    NgxDatatableModule,
    DirectivesModule,
    ToastrModule
  ],
  exports: [
    PessoaListComponent,
    PessoaFormComponent
  ]
})
export class PessoaModule { }

