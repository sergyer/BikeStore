import { Component, OnInit } from '@angular/core';
import { BikeService } from 'src/app/services/bike.service';
import {ActivatedRoute,Router} from '@angular/router';
import {FormGroup, FormControl,Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  public bike;
  bikeModels: string[];

  bikeEditForm: FormGroup;
  validMessage: string= "";; 

  constructor(private bikeService:BikeService, private route:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.bikeModels = this.bikeService.getBikeModels();
    this.getBike(this.route.snapshot.params.id);
    this.bikeEditForm = new FormGroup({
      name: new FormControl('',Validators.required),
      serialNumber: new FormControl('',Validators.required),
      purchasePrice: new FormControl('',Validators.required),
      model: new FormControl('',Validators.required),
      id: new FormControl('',Validators.required)
    });
  }

  getBike(id:number){
    this.bikeService.getBike(id).subscribe(
      data => this.bike=data,
      error => console.log(error),
      () => {console.log("Successfully got bike"+this.bike)
          this.populate();
          }
    );
  }

  populate(){
    this.bikeEditForm.setValue({
      name: this.bike.name,
      serialNumber: this.bike.serialNumber,
      purchasePrice: this.bike.purchasePrice,
      id: this.bike.id,
      model: this.bike.model
    });
  }

  updateBike(){
    this.bikeService.updateBike(this.bikeEditForm.value.id,this.bikeEditForm.value).subscribe(
      data => {console.log("success");
      this.getBike(this.bikeEditForm.value.id);
      this.validMessage="Successfully updated bike";
    },
      error => console.log(error)
    );
    
  }

  removeBike(id:number){
    this.bikeService.removeBike(id)
    .subscribe(()=> {
      console.log("Success");
      this.validMessage='Successfully deleted bike';
      setTimeout(()=>{this.router.navigate(['/admin']);},2000);
    },
    error => console.log(error));
  }

}
