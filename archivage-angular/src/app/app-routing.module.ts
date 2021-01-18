import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {PreLoginComponent} from './pre-login/pre-login.component';
import {ClientsComponent} from './clients/clients.component';
import {ContractsComponent} from './contracts/contracts.component';
import {AccountsComponent} from './accounts/accounts.component';
import {NewClientComponent} from './clients/new-client/new-client.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {NewAccountComponent} from './new-account/new-account.component';
import {NewContractComponent} from './new-contract/new-contract.component';
import {DocumentsComponent} from './documents/documents.component';
import {DocumentsToDestroyComponent} from './documents-to-destroy/documents-to-destroy.component';
import {DocumentsToBeValidatedComponent} from './documents-to-be-validated/documents-to-be-validated.component';
import {DocumentsDestroyedComponent} from './documents-destroyed/documents-destroyed.component';
import {UpdateDfpmDocComponent} from './update-dfpm-doc/update-dfpm-doc.component';
import {HistoryComponent} from './history/history.component';
import {ClassificationNatureComponent} from './classification-nature/classification-nature.component';
import {ClassificationNatureManageComponent} from './classification-nature/classification-nature-manage/classification-nature-manage.component';
import {AuthGuard} from './helpers/authGuard';


const routes: Routes = [
  { path: '',  redirectTo: 'home', pathMatch: 'full', canActivate: [AuthGuard]},
  { path: 'home', component: HomeComponent, data : {title:'Accueil'}, canActivate: [AuthGuard]},
  { path: 'pre-login', component: PreLoginComponent},
  { path: 'login', component: LoginComponent},
  { path: 'clients', component: ClientsComponent, data : {title:'Clients'}, canActivate: [AuthGuard]},
  { path: 'new-client', component: NewClientComponent, data : {title:'Nouveau client'}, canActivate: [AuthGuard]},
  { path: 'contracts', component: ContractsComponent, data : {title:'Contrats'}, canActivate: [AuthGuard]},
  { path: 'new-contract', component: NewContractComponent, data : {title:'Nouveau contrat'}, canActivate: [AuthGuard]},
  { path: 'accounts', component: AccountsComponent, data : {title:'comptes'}, canActivate: [AuthGuard]},
  { path: 'new-account', component: NewAccountComponent, data : {title:'Nouveau compte'}, canActivate: [AuthGuard]},
  { path: 'documents', component: DocumentsComponent, data : {title:'Documents'}, canActivate: [AuthGuard]},
  { path: 'documents-to-validated', component: DocumentsToBeValidatedComponent, data : {title:'Documents à valider'}, canActivate: [AuthGuard]},
  { path: 'documents-to-destroyed', component: DocumentsToDestroyComponent, data : {title:'Documents à détruire'}, canActivate: [AuthGuard]},
  { path: 'documents-destroyed', component: DocumentsDestroyedComponent, data : {title:'Documents détruits'}, canActivate: [AuthGuard]},
  { path: 'update-dfpm-document', component: UpdateDfpmDocComponent, data : {title:'Mis à jour DFPM'}, canActivate: [AuthGuard]},
  { path: 'history', component: HistoryComponent, data : {title:'Historique'}, canActivate: [AuthGuard]},
  { path: 'classifcation-nature', component: ClassificationNatureComponent, data : {title:'Classification nature'}, canActivate: [AuthGuard]},
  { path: 'new-classifcation-nature', component: ClassificationNatureManageComponent, data : {title:'Classification nature form'}, canActivate: [AuthGuard]},
  { path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
