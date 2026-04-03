import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { InstituicaoFormComponent } from "./instituicao-form/instituicao-form.component";
import { InstituicaoListComponent } from "./instituicao-list/instituicao-list.component";

const routes: Routes = [
    {
        path: '',
        component: InstituicaoListComponent
    },
    {
        path: 'inserir',
        component: InstituicaoFormComponent
    },
    {
        path: ':id/editar',
        component: InstituicaoFormComponent
    },
    {
        path: 'bloco',
        loadChildren: () => import('../bloco/bloco.module').then(m => m.BlocoModule)
    },
    {
        path: 'piso',
        loadChildren: () => import('../piso/piso.module').then(m => m.PisoModule)
    },
    {
        path: 'sala',
        loadChildren: () => import('../sala/sala.module').then(m => m.SalaModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class InstituicaoRoutingModule {}