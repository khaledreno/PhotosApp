import { Routes } from '@angular/router';
import { PicListComponent } from './pic-list/pic-list.component';
import { UploadComponent } from './upload/upload.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [



    { path: 'pics', component: PicListComponent },
    { path: 'upload', component: UploadComponent }, // Route for the about page
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },

    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/home', pathMatch: 'full' } // Handle unknown routes


        // { path: '', component: HomeComponent }, // Default route for the home page
    
    //{ path: '', redirectTo: 'pics', pathMatch: 'full' }, // Default route+
    // { path: 'login', component: LoginComponent },
   //{ path: '**', redirectTo: 'pics' } // Fallback route for unknown paths

    // Route for the about page

    // { path: '', redirectTo: 'pics', pathMatch: 'full' },
    // { path: 'login', component: LoginComponent },
    // { path: 'pics', component: PicListComponent, canActivate: [AuthGuard] },
    // { path: 'upload', component: UploadComponent, canActivate: [AuthGuard] },
    // { path: '**', redirectTo: 'pics' },


 
  // { path: '**', redirectTo: '' } // Fallback route for unknown paths
 ];
 