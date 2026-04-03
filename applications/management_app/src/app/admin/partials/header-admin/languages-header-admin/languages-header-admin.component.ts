import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { NavService } from 'src/app/shared/services/nav.service';

@Component({
  selector: 'cmdca-languages-header-admin',
  templateUrl: './languages-header-admin.component.html',
  styleUrls: ['./languages-header-admin.component.scss']
})
export class LanguagesHeaderAdminComponent implements OnInit {

  public language: boolean = false;

  public languages: any[] = [{
    language: 'English',
    code: 'en',
    type: 'US',
    icon: 'us'
  },
  {
    language: 'Español',
    code: 'es',
    icon: 'es'
  },
  {
    language: 'Français',
    code: 'fr',
    icon: 'fr'
  },
  {
    language: 'Português',
    code: 'pt',
    type: 'BR',
    icon: 'pt'
  }]

  public selectedLanguage: any = {
    language: 'English',
    code: 'en',
    type: 'US',
    icon: 'us'
  }
  
  constructor(
    private translate: TranslateService,
    public navServices: NavService
  ) { }

  ngOnInit() { }

  changeLanguage(lang) {
    this.translate.use(lang.code)
    this.selectedLanguage = lang;
  }
}
