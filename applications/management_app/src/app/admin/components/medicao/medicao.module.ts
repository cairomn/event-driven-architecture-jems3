import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MedicaoListComponent } from './medicao-list/medicao-list.component';
import { MedicaoRoutingModule } from './medicao-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { NgxMaskModule } from 'ngx-mask';
import { MedicaoValoresListComponent } from './medicao-valores-list/medicao-valores-list.component';

@NgModule({
  declarations: [
    MedicaoListComponent,
    MedicaoValoresListComponent
  ],
  imports: [
    CommonModule,
    MedicaoRoutingModule,
    SharedModule,
    NgxMaskModule.forRoot(),
  ]
})
export class MedicaoModule { }
