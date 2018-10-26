import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


const httpOptions = {headers: new HttpHeaders({'Content-Type':'application/json'}) }

@Injectable()
export class BikeService {

  constructor(private http:HttpClient) { }

  getBikes(){
    return this.http.get('/server/api/v1/bikes');
  }

  getBike(id:number){
    return this.http.get('/server/api/v1/'+id);
  }

  createBike(bike){
    let body = JSON.stringify(bike);
    return this.http.post('/server/api/v1/bikes',body,httpOptions);
  }

  removeBike(id:number){
    return this.http.delete('/server/api/v1/bikes'+id);
  }

} 
