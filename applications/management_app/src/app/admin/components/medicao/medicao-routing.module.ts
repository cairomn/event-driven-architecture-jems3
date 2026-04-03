import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { MedicaoListComponent } from './medicao-list/medicao-list.component';

const routes: Routes = [
    {
        path: '',
        component: MedicaoListComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MedicaoRoutingModule {}