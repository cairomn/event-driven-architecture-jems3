import { Directive, ElementRef, HostListener } from '@angular/core';
import { CNPJPipe } from '../pipes/cnpj.pipe';

@Directive({
  selector: '[appCNPJ]'
})
export class CNPJDirective {

  /**
   * Construtor da Classe.
   * 
   * @param el 
   */
  constructor(
    private el: ElementRef,
    private CNPJPipe: CNPJPipe
  ) { }

  @HostListener("focus", ["$event.target.value"])
  onFocus(value) {
    this.el.nativeElement.value = this.CNPJPipe.parse(value);
  }

  @HostListener("blur", ["$event.target.value"])
  onBlur(value) {
    this.el.nativeElement.value = this.CNPJPipe.transform(value);
  }

}
