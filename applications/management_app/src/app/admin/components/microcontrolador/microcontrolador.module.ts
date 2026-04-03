import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MicrocontroladorRoutingModule } from './microcontrolador-routing.module';
import { MicrocontroladorListComponent } from './microcontrolador-list/microcontrolador-list.component';
import { MicrocontroladorFormComponent } from './microcontrolador-form/microcontrolador-form.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MicrocontroladorAtuadorFormComponent } from './microcontrolador-form/microcontrolador-atuador-form/microcontrolador-atuador-form.component';
import { MicrocontroladorSensorFormComponent } from './microcontrolador-form/microcontrolador-sensor-form/microcontrolador-sensor-form.component';
import { MaskService, NgxMaskModule } from 'ngx-mask';
import { PartialsAdminModule } from '../../partials/partials-admin.module';

@NgModule({
  declarations: [
    MicrocontroladorListComponent,
    MicrocontroladorFormComponent,
    MicrocontroladorAtuadorFormComponent,
    MicrocontroladorSensorFormComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    MicrocontroladorRoutingModule,
    NgxMaskModule.forRoot(),
    PartialsAdminModule
  ],
  providers: [
    MaskService
  ],
  exports: [
    MicrocontroladorListComponent,
    MicrocontroladorFormComponent
  ]
})
export class MicrocontroladorModule { }
