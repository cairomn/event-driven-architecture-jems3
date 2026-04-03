import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalaListComponent } from './sala-list/sala-list.component';
import { SalaRoutingModule } from './sala-routing.module';
import { SalaFormComponent } from './sala-form/sala-form.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PartialsAdminModule } from '../../partials/partials-admin.module';

@NgModule({
  declarations: [
    SalaListComponent,
    SalaFormComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    SalaRoutingModule,
    PartialsAdminModule
  ],
  exports: [
    SalaListComponent,
    SalaFormComponent
  ]
})
export class SalaModule { }
