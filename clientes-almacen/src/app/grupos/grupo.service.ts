import { Injectable } from '@angular/core';
import { Grupo } from './grupo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError} from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Categoria} from './categoria';



@Injectable({
  providedIn: 'root'
})

export class GrupoService {

  //exportamos url Backend
  private urlEndpoint: string = '//localhost:8080/api/grupos';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})


  constructor(private http: HttpClient, private router: Router) { }
  

///////Métodos CRUD
      getGrupos(): Observable<Grupo[]> {
        return this.http.get(this.urlEndpoint).pipe(
          map(response => response as Grupo[])
        );
      }

/////GET CATEGORIAS
      getCategorias(): Observable<Categoria[]>{
        return this.http.get<Categoria[]>(this.urlEndpoint + '/categorias');
      }



//////CREATE
      create(grupo: Grupo): Observable <Grupo>{
        return this.http.post<Grupo>(this.urlEndpoint, grupo, {headers: this.httpHeaders}).pipe(
          //capturamos errores
          catchError(e => {
            this.router.navigate(['/grupos']);
            console.error(e.error.mensaje);
            Swal.fire('Error al crear el cliente',e.error.mensaje, 'error');
            return throwError(e);

          })
        );
      }


//////EDIT BY ID
      getGrupo(id): Observable<Grupo>{
        return this.http.get<Grupo>(`${this.urlEndpoint}/${id}`).pipe(
          //capturamos errores
          catchError(e => {
            this.router.navigate(['/grupos']);
            console.error(e.error.mensaje);
            Swal.fire('Error al editar',e.error.mensaje, 'error');
            return throwError(e);

          })
        );
      }


//////UPDATE
      update(grupo: Grupo): Observable<Grupo>{
        return this.http.put<Grupo>(`${this.urlEndpoint}/${grupo.id}`, grupo, {headers: this.httpHeaders}).pipe(
          //capturamos errores
          catchError(e => {
            this.router.navigate(['/grupos']);
            console.error(e.error.mensaje);
            Swal.fire('Error al Actualizar',e.error.mensaje, 'error');
            return throwError(e);

          })
        );
      }


//////DELETE
      delete(id: number): Observable<Grupo> {
        return this.http.delete<Grupo>(`${this.urlEndpoint}/${id}`, { headers: this.httpHeaders }).pipe(
          catchError(e => {
            console.error(e.error.mensaje);
            Swal.fire(e.error.mensaje, e.error.error, 'error');
            return throwError(e);
          })
        );
      }

}


