import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CNPJDirective } from './cnpj.directive';

import { PipesModule } from '../pipes/pipes.module';

@NgModule({
  declarations: [
    CNPJDirective
  ],
  imports: [
    CommonModule,
    PipesModule
  ],
  exports: [
    CNPJDirective
  ]
})
export class DirectivesModule { }
