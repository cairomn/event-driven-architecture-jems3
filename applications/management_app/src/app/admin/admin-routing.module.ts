import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';

// @ts-ignore
const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard'
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./components/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'instituicao',
        loadChildren: () => import('./components/instituicao/instituicao.module').then(m => m.InstituicaoModule)
      },
      {
        path: 'usuario',
        loadChildren: () => import('./components/usuario/usuario.module').then(m => m.UsuarioModule)
      },
      {
        path: 'microcontrolador',
        loadChildren: () => import('./components/microcontrolador/microcontrolador.module').then(m => m.MicrocontroladorModule)
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
