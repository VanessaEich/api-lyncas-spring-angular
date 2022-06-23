import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersFormComponent } from './users-form/users-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsersListComponent } from './users-list/users-list.component';
import { NgxMaskModule } from 'ngx-mask';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatPaginatorIntlPtBr } from './users-list/paginator-ptbr-i8n';

@NgModule({
  declarations: [
    UsersFormComponent,
    UsersListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    UsersRoutingModule,
    NgxMaskModule.forRoot({ dropSpecialCharacters: true }),
    ReactiveFormsModule,
    MatPaginatorModule

  ],
  exports: [
    UsersFormComponent,
    UsersListComponent
  ],
  providers: [
    { provide: MatPaginatorIntl, useClass: MatPaginatorIntlPtBr },
  ],
})
export class UsersModule { }
