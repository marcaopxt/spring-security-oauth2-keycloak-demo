import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private httpClient: HttpClient) { }
  
  hello(): Observable<string> {
    const headers = new HttpHeaders()
      .append('Content-Type','text/plain; charset=utf-8')
      .append('Access-Control-Allow-Origin', '*');
    return this.httpClient.get("http://localhost:8682/api/home", {headers, responseType: 'text' });
  }	
  
}
