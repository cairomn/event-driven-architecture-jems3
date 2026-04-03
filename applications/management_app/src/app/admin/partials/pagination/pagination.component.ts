import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PagedResponse } from '../../models/responses/paged-response';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {

  @Output("paginar") paginar: EventEmitter<number> = new EventEmitter<number>();
  @Input("pagedResponse") paged: PagedResponse<any> = {} as PagedResponse<any>;

  constructor() { }

  ngOnInit(): void {
    this.paged = {
      page: 0,
      last: true,
      size: 1,
      totalElements: 1,
      totalPages: 0,
      content: []
    } as PagedResponse<any>;
  }

  public paginarPorPagina(pagina: number) {
    this.paginar.emit(pagina);
  }

  public getTotalPagina(): number {
    if (this.paged.content.length <= this.paged.size) {
      return this.paged.content.length;
    }
    
    return Math.ceil(this.paged.totalElements / this.paged.size);
  }
}
