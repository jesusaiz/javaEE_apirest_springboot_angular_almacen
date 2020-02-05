import { Component, OnInit } from '@angular/core';
import { Grupo } from './grupo';
import { GrupoService} from './grupo.service';
import { Router, ActivatedRoute} from '@angular/router';
import Swal from 'sweetalert2';
import { Categoria} from './categoria';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  public grupo : Grupo = new Grupo();
  categorias: Categoria[];
  private titulo: string = "Crear grupo"


  constructor(private grupoService: GrupoService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = +params.get('id');
      if(id){
        this.grupoService.getGrupo(id)
        .subscribe((grupo) => this.grupo = grupo)
      }
    });
      this.grupoService.getCategorias()
        .subscribe((categorias) => this.categorias = categorias);
  }


//MÉTODOS DEL FORM

////////CREATE
        create(): void {
          this.grupoService.create(this.grupo)
            .subscribe(grupo => {
              this.router.navigate(['/grupos']);
              Swal.fire(
                    'Grupo Creado!',
                    `Grupo creado con éxito.`,
                    'success')
            }
            );
        }


////////UPDATE
        update(): void{
          this.grupoService.update(this.grupo)
          .subscribe( grupo => {
            this.router.navigate(['/grupos']);
            Swal.fire(
              'Grupo Actualizado', 
              `Grupo actualizado con éxito!`, 
              'success')
          }

          )
        }

 ////////COMPARAR CATEGORIA
        compararCategoria(o1: Categoria, o2: Categoria){
          return o1 ==null || o2 ==null? false:o1.id===o2.id;
        }       

}
