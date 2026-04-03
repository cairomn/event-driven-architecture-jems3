import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UsuarioFormComponent} from "./usuario-form/usuario-form.component";
import {UsuarioListComponent} from "./usuario-list/usuario-list.component";
import {SharedModule} from "../../../shared/shared.module";
import {PartialsAdminModule} from "../../partials/partials-admin.module";
import {UsuarioRoutingModule} from "./usuario-routing.module";

@NgModule({
  declarations: [
      UsuarioFormComponent,
      UsuarioListComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    UsuarioRoutingModule,
    PartialsAdminModule
  ]
})
export class UsuarioModule { }
