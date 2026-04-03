import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { HeaderAdminComponent } from './header-admin/header-admin.component';
import { SidebarAdminComponent } from './sidebar-admin/sidebar-admin.component';
import { FooterAdminComponent } from './footer-admin/footer-admin.component';
import { BookmarkHeaderAdminComponent } from './header-admin/bookmark-header-admin/bookmark-header-admin.component';
import { CartHeaderAdminComponent } from './header-admin/cart-header-admin/cart-header-admin.component';
import { LanguagesHeaderAdminComponent } from './header-admin/languages-header-admin/languages-header-admin.component';
import { MegaMenuHeaderAdminComponent } from './header-admin/mega-menu-header-admin/mega-menu-header-admin.component';
import { MessageBoxHeaderAdminComponent } from './header-admin/message-box-header-admin/message-box-header-admin.component';
import { MyAccountHeaderAdminComponent } from './header-admin/my-account-header-admin/my-account-header-admin.component';
import { NotificationHeaderAdminComponent } from './header-admin/notification-header-admin/notification-header-admin.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ModalDeletarComponent } from './modals/modal-deletar/modal-deletar.component';
import { PaginationComponent } from './pagination/pagination.component';
import { AcaoModelComponent } from './modals/acao-model/acao-model.component';

@NgModule({
  declarations: [
    SidebarAdminComponent,
    HeaderAdminComponent,
    FooterAdminComponent,
    BookmarkHeaderAdminComponent,
    CartHeaderAdminComponent,
    LanguagesHeaderAdminComponent,
    MegaMenuHeaderAdminComponent,
    MessageBoxHeaderAdminComponent,
    MyAccountHeaderAdminComponent,
    NotificationHeaderAdminComponent,
    ModalDeletarComponent,
    PaginationComponent,
    AcaoModelComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    TranslateModule,
    SharedModule
  ],
  exports: [
    SidebarAdminComponent,
    HeaderAdminComponent,
    FooterAdminComponent,
    ModalDeletarComponent,
    PaginationComponent
  ],
})
export class PartialsAdminModule { }
