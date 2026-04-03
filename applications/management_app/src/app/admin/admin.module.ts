import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { NgxMaskModule, IConfig } from 'ngx-mask'

import { AdminComponent } from './admin.component';
import { AdminRoutingModule } from './admin-routing.module';
import { PartialsAdminModule } from './partials/partials-admin.module';
import { SharedModule } from '../shared/shared.module';

const maskConfig: Partial<IConfig> = {};

@NgModule({
  declarations: [
    AdminComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AdminRoutingModule,
    TranslateModule,
    PartialsAdminModule,
    SharedModule,
    NgxMaskModule.forRoot(maskConfig),
  ]
})
export class AdminModule { }
