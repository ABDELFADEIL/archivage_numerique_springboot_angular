import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PreLoginComponent } from './pre-login/pre-login.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { NewClientComponent } from './clients/new-client/new-client.component';
import { ClientsComponent } from './clients/clients.component';
import { ContractsComponent } from './contracts/contracts.component';
import { NewContractComponent } from './new-contract/new-contract.component';
import { AccountsComponent } from './accounts/accounts.component';
import { NewAccountComponent } from './new-account/new-account.component';
import { DocumentsComponent } from './documents/documents.component';
import { DocumentsToDestroyComponent } from './documents-to-destroy/documents-to-destroy.component';
import { DocumentsToBeValidatedComponent } from './documents-to-be-validated/documents-to-be-validated.component';
import { DocumentsDestroyedComponent } from './documents-destroyed/documents-destroyed.component';
import { UpdateDfpmDocComponent } from './update-dfpm-doc/update-dfpm-doc.component';
import {HttpClientModule, HttpClient, HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ClassificationNatureComponent } from './classification-nature/classification-nature.component';
import { HistoryComponent } from './history/history.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ClassificationNatureManageComponent } from './classification-nature/classification-nature-manage/classification-nature-manage.component';
import {JwtInterceptor} from './helpers/jwt.interceptor';
import {ErrorInterceptor} from './helpers/error.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent } from './footer/footer.component';
import { UpdateClassificationNatureComponent } from './classification-nature/update-classification-nature/update-classification-nature.component';
import { UpdateAccountComponent } from './update-account/update-account.component';
import { UpdateContractComponent } from './update-contract/update-contract.component';
import { UpdateClientComponent } from './update-client/update-client.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PreLoginComponent,
    LoginComponent,
    NotFoundComponent,
    HomeComponent,
    NewClientComponent,
    ClientsComponent,
    ContractsComponent,
    NewContractComponent,
    AccountsComponent,
    NewAccountComponent,
    DocumentsComponent,
    DocumentsToDestroyComponent,
    DocumentsToBeValidatedComponent,
    DocumentsDestroyedComponent,
    UpdateDfpmDocComponent,
    ClassificationNatureComponent,
    HistoryComponent,
    SidebarComponent,
    ClassificationNatureManageComponent,
    FooterComponent,
    UpdateClassificationNatureComponent,
    UpdateAccountComponent,
    UpdateContractComponent,
    UpdateClientComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        NgbModule,


    ],
  providers: [Title, { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    UpdateClassificationNatureComponent
  ]
})
export class AppModule { }
