import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  apiUrl: string = "http://localhost:8080/home";

  response: string = "";

  constructor(private httpClient: HttpClient) { }

  sendRequest(){
      return this.httpClient.get<LoginResponse>(this.apiUrl).pipe(
        tap((value) => {
          console.log(value);
        })
      );
    }
}
