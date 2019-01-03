import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CompanyComponent } from './components/company/company.component';
import { CustomerComponent } from './components/customer/customer.component';

const routes: Routes = [
{path: 'company', component: CompanyComponent},
{path: 'customer', component: CustomerComponent},
{path: '', redirectTo: '/company' , pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
