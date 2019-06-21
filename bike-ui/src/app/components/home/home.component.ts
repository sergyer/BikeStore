import { Component, OnInit } from '@angular/core';
import { BikeService } from '../../services/bike.service';
import {FormGroup, FormControl,Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  bikeModels: string[];
  bikeRegistrationForm: FormGroup;
  validMessage: string= "";; 
  

  constructor(private bikeService: BikeService) { }

  ngOnInit() {
    this.bikeModels= this.bikeService.getBikeModels();
    this.bikeRegistrationForm = new FormGroup({
      name: new FormControl('',Validators.required),
      model: new FormControl('',Validators.required),
      serialNumber: new FormControl('',Validators.required),
      purchasePrice: new FormControl('',Validators.required),
    })
  }

  submitRegistration(){
    if(this.bikeRegistrationForm.valid){
      this.validMessage="Your bike registration has been submitted. Thank you!";
      this.bikeService.createBike(this.bikeRegistrationForm.value).subscribe(
        data =>{
          this.bikeRegistrationForm.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      )
    }
    else{
      this.validMessage="Please fill out the form before submitting";
    }
  }
}
