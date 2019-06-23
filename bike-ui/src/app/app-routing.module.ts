import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminComponent} from './components/admin/admin.component';
import { HomeComponent } from './components/home/home.component';
import {ViewRegistrationComponent} from './components/view-registration/view-registration.component';
import {EditComponent} from './components/edit/edit.component';
import { CallbackComponent } from './components/callback/callback.component';
import {AuthGuard} from './services/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'admin/edit/:id',
    component: EditComponent,
    canActivate: [AuthGuard]
  },
  {
    path:'admin/view/:id',
    component: ViewRegistrationComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'callback',
    component: CallbackComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
