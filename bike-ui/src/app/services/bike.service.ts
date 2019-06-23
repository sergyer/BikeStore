import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const token = localStorage.getItem('access_token');
const httpOptions = {headers: new HttpHeaders({'Content-Type':'application/json'}).set('Authorization','Bearer '+token) }

@Injectable()
export class BikeService {
  private apiUrl:string='/server/api/v1/bikes/';

  constructor(private http:HttpClient) { }

  getBikes(){
    return this.http.get(this.apiUrl,httpOptions);
  }

  getBike(id:number){
    return this.http.get(this.apiUrl+id,httpOptions);
  }

  createBike(bike){
    let body = JSON.stringify(bike);
    return this.http.post(this.apiUrl,body,{headers: new HttpHeaders({'Content-Type':'application/json'})});
  }

  updateBike(id:number,bike){
    let body = JSON.stringify(bike);
    return this.http.put(this.apiUrl+id,body,httpOptions);
  }

  removeBike(id:number){
    return this.http.delete(this.apiUrl+id,httpOptions);
  }

  getBikeModels() : string[]{
    return [
      'GLOBO MTB 29 FULL SUSPENSION',
      'GLOBO CARBON FIBER RACE SERIES',
      'GLOBO TIME TRIAL BLADE'
  ];
  }

} 
