import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { BlocoListComponent } from './bloco-list/bloco-list.component';
import { BlocoRoutingModule } from './bloco-routing.module';
import { BlocoFormComponent } from './bloco-form/bloco-form.component';
import { ToastrModule } from 'ngx-toastr';
import { PartialsAdminModule } from '../../partials/partials-admin.module';

@NgModule({
  declarations: [
    BlocoListComponent,
    BlocoFormComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    BlocoRoutingModule,
    ToastrModule,
    PartialsAdminModule
  ],
  exports: [
    BlocoListComponent,
    BlocoFormComponent
  ]
})
export class BlocoModule { }
