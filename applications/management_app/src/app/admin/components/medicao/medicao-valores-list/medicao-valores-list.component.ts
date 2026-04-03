import { Component, OnInit } from '@angular/core';
import { Medicao } from 'src/app/admin/models/medicao';
import { PagedResponse } from 'src/app/admin/models/responses/paged-response';

@Component({
  selector: 'app-medicao-valores-list',
  templateUrl: './medicao-valores-list.component.html',
  styleUrls: ['./medicao-valores-list.component.scss']
})
export class MedicaoValoresListComponent implements OnInit {

  public medicoes: PagedResponse<Medicao> = {} as PagedResponse<Medicao>;

  constructor() { }

  ngOnInit(): void {
  }

}
