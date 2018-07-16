import { Component, OnInit, ViewChild}                     from '@angular/core';
import { Router }                                          from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import swal from 'sweetalert2';

import { GeneroFakeService }                              from '../genero/genero.component.servicefake';
import { GeneroService }                                  from '../genero/genero.component.service';
import { Genero }                                         from '../genero/genero.component.model';





@Component ({
    selector: 'app-view',
    templateUrl: './genero.component.html'
})

export class GeneroComponent implements OnInit {

    title = 'Nuevo Genero';
    generoList: Genero;
    genero: Genero;
    form: any;





    constructor(private router: Router, 
				private generoService: GeneroService, 
				private generoFakeService: GeneroFakeService
) {
		

	}

    ngOnInit() {
        //this.genero = this.generoService.getGenero();
		this.genero = this.generoFakeService.getGenero();
        this.loadGeneros();


    }

    loadGeneros() {
      this.generoFakeService.getAllGenero().subscribe(data => {
        if (data) {
          this.generoList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the generos.', 'error');
      });
    }

	  /*  
      this.generoService.getAllGenero().subscribe(data => {
        if (data) {
          this.generoList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the generos.', 'error');
      });
    }*/

    save(genero, modal){
      this.generoFakeService.saveGenero(this.genero).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Genero save successfully.', 'success');
        }else{
          swal('Error...', 'Genero save unsuccessfully.', 'error');
        }

      } );
    }

	/*  
    save(genero, modal){
      this.generoService.saveGenero(this.genero).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Genero save successfully.', 'success');
        }else{
          swal('Error...', 'Genero save unsuccessfully.', 'error');
        }

      } );
    }*/

	

	/*	
	*/


	/*	
	*/


	/* 
	*/
	

	/* 
	*/
}

