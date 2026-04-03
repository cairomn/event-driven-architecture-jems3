import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { MicrocontroladorFormComponent } from "./microcontrolador-form/microcontrolador-form.component";
import { MicrocontroladorListComponent } from "./microcontrolador-list/microcontrolador-list.component";

const routes: Routes = [
    {
        path: '',
        component: MicrocontroladorListComponent
    },
    {
        path: 'inserir',
        component: MicrocontroladorFormComponent
    },
    {
        path: ':id/editar',
        component: MicrocontroladorFormComponent
    },
    {
        path: 'medicoes',
        loadChildren: () => import('../medicao/medicao.module').then(m => m.MedicaoModule)
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MicrocontroladorRoutingModule {}