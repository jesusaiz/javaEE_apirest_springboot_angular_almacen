import { Component, OnInit } from '@angular/core';
import { Grupo} from'./grupo';
import { GrupoService } from './grupo.service';
import { tap } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-grupos',
  templateUrl: './grupos.component.html',
  styleUrls: ['./grupos.component.css']
})
export class GruposComponent implements OnInit {

  grupos: Grupo[];

  constructor(private grupoService: GrupoService) { }

  ngOnInit() {
    this.grupoService.getGrupos().pipe(
      tap(grupos => {
        console.log('GruposComponent: tap 3');
        grupos.forEach(grupo => {
          console.log(grupo.nombre);
        });
      })
    ).subscribe(grupos => this.grupos = grupos);

}


/////MÉTODOS

/////DELETE
        delete(grupo: Grupo): void {
          Swal.fire({
              title: 'Esta seguro que quiere eliminarlo?',
              text: '¡Si lo elimina no podrá recuperarlo!',
              icon: 'warning',
              showCancelButton: true,
              confirmButtonText: '¡Sí, elimínelo!',
              confirmButtonColor: '#oaeba5',
              cancelButtonText: 'No'
          }).then((result) => {
            if (result.value) {

              this.grupoService.delete(grupo.id)
              .subscribe( () => {
                  this.grupos = this.grupos.filter(gru => gru !== grupo)
                  Swal.fire(
                    'Grupo Eliminado!',
                    `Grupo  ${grupo.nombre} eliminado con éxito.`,
                    'success'
                  )
                }
              )

            }
          });
        }


}