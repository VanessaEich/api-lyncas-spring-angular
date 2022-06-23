import { Injectable } from '@angular/core';
import { User } from './users/user';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { PageUser } from './users/users-list/pageUser';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiURL: string = environment.apiURLBase + '/pessoas'

  constructor(private http: HttpClient) { }

  salvar(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiURL}`, user);
  }

  atualizar(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiURL}/${user.userId}`, user);
  }

  getUsers(page, size): Observable<PageUser> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
    return this.http.get<PageUser>(`${this.apiURL}?${params.toString()}`);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  deletar(user: User): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${user.userId}`);
  }

  buscar(name: string): Observable<User[]> {
    const httpParams = new HttpParams().set("name", name);

    const url = this.apiURL + "/name" + "?" + httpParams.toString();
    return this.http.get<any>(url);
  }
}
