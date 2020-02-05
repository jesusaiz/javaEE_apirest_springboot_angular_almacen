import { NgModule} from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule} from '@angular/material/icon';
import { MatPaginatorModule} from '@angular/material/paginator';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatNativeDateModule } from '@angular/material';

@NgModule({
    exports: [ MatToolbarModule, 
            MatInputModule, 
            MatCardModule, 
            MatButtonModule, 
            MatSelectModule,
            MatFormFieldModule, 
            MatDatepickerModule,
            MatTableModule,
            MatIconModule,
            MatPaginatorModule,
            MatProgressSpinnerModule,
            MatNativeDateModule
          
             ]
})

export class MaterialModule {}