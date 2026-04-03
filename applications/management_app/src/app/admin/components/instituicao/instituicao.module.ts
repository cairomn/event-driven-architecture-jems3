import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InstituicaoRoutingModule } from './instituicao-routing.module';
import { InstituicaoListComponent } from './instituicao-list/instituicao-list.component';
import { InstituicaoFormComponent } from './instituicao-form/instituicao-form.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ToastrModule } from 'ngx-toastr';
import { DirectivesModule } from 'src/app/directives/directives.module';
import { PipesModule } from 'src/app/pipes/pipes.module';
import { MaskService, NgxMaskModule } from 'ngx-mask';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { PartialsAdminModule } from '../../partials/partials-admin.module';

@NgModule({
  declarations: [
    InstituicaoListComponent,
    InstituicaoFormComponent,
  ],
  imports: [
    CommonModule,
    InstituicaoRoutingModule,
    SharedModule,
    ToastrModule,
    DirectivesModule,
    PipesModule,
    NgxMaskModule.forRoot(),
    NgxDatatableModule,
    PartialsAdminModule
  ],
  exports: [
    InstituicaoListComponent,
    InstituicaoFormComponent
  ],
  providers: [
    MaskService
  ]
})
export class InstituicaoModule { }
