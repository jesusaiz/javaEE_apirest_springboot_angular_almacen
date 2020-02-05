import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { GruposComponent } from './grupos/grupos.component';
import { GrupoService } from './grupos/grupo.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes} from '@angular/router';
import { FormsModule }   from '@angular/forms';
import { MaterialModule} from './material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormComponent } from './grupos/form.component';



const routes: Routes = [
  {path: '', redirectTo: 'grupos', pathMatch: 'full'},
  {path: 'grupos', component: GruposComponent},
  {path: 'grupos/form', component: FormComponent},
  {path: 'grupos/form/:id', component: FormComponent}
  
]

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    GruposComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule
  ],
  providers: [GrupoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
