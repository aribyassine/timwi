import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Hero} from "../entities/Hero";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HeroService {
  public API: string = '//localhost:8080'
  public HERO_API: string = this.API + '/hero'

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.HERO_API)
  }

  save(hero: Hero): Observable<any> {
    console.log(hero._links.self.href,hero)
    return this.http.put<Hero>(hero._links.self.href, hero)
  }
}
