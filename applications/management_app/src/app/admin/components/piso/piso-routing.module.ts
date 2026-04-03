import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { PisoFormComponent } from "./piso-form/piso-form.component";
import { PisoListComponent } from "./piso-list/piso-list.component";

const routes: Routes = [
    {
        path: '',
        component: PisoListComponent
    },
    {
        path: 'inserir',
        component: PisoFormComponent
    },
    {
        path: ':id/editar',
        component: PisoFormComponent
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PisoRoutingModule {}