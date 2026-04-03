import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PisoRoutingModule } from './piso-routing.module';
import { PisoListComponent } from './piso-list/piso-list.component';
import { PisoFormComponent } from './piso-form/piso-form.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PartialsAdminModule } from '../../partials/partials-admin.module';

@NgModule({
  declarations: [
    PisoListComponent,
    PisoFormComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PisoRoutingModule,
    PartialsAdminModule
  ],
  exports: [
    PisoListComponent,
    PisoFormComponent
  ]
})
export class PisoModule { }
