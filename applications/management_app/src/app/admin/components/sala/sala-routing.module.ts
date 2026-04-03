import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SalaFormComponent } from "./sala-form/sala-form.component";
import { SalaListComponent } from "./sala-list/sala-list.component";

const routes: Routes = [
    {
        path: '',
        component: SalaListComponent
    },
    {
        path: 'inserir',
        component: SalaFormComponent
    },
    {
        path: ':id/editar',
        component: SalaFormComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SalaRoutingModule {}