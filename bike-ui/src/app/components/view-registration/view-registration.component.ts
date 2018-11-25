import { Component, OnInit } from '@angular/core';
import {BikeService} from '../../services/bike.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-view-registration',
  templateUrl: './view-registration.component.html',
  styleUrls: ['./view-registration.component.css']
})
export class ViewRegistrationComponent implements OnInit {

  public bike;
  constructor(private bikeService : BikeService, private route:ActivatedRoute) { }

  ngOnInit() { 
    this.getBike(this.route.snapshot.params.id);
  }

  getBike(id:number){
    this.bikeService.getBike(id).subscribe(
      data => this.bike=data,    

      error => console.log(error),
      () => console.log('Successfully got bike')

    );
  }

}
