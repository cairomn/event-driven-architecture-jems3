import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BlocoFormComponent } from "./bloco-form/bloco-form.component";
import { BlocoListComponent } from "./bloco-list/bloco-list.component";

const routes: Routes = [
    {
        path: '',
        component: BlocoListComponent
    },
    {
        path: 'inserir',
        component: BlocoFormComponent
    },
    {
        path: ':id/editar',
        component: BlocoFormComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BlocoRoutingModule {}