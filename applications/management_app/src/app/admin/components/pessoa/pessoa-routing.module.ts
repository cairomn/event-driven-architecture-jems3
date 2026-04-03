import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router"
import {PessoaListComponent} from "./pessoa-list/pessoa-list.component";
import {PessoaFormComponent} from "./pessoa-form/pessoa-form.component";

const routes: Routes = [
    {
        path: '',
        component: PessoaListComponent
    },
    {
        path: 'inserir',
        component: PessoaFormComponent
    },
    {
        path: ':id/editar',
        component: PessoaFormComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PessoaRoutingModule {}
