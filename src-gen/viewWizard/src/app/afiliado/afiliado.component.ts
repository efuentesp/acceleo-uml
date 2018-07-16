import { Component, OnInit, ViewChild}                     from '@angular/core';
import { Router }                                          from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import swal from 'sweetalert2';

import { AfiliadoFakeService }                              from '../afiliado/afiliado.component.servicefake';
import { AfiliadoService }                                  from '../afiliado/afiliado.component.service';
import { Afiliado }                                         from '../afiliado/afiliado.component.model';

import { GeneroFakeService }                                  from '../genero/genero.component.servicefake';
import { GeneroService }                                  from '../genero/genero.component.service';
import { Genero }                                         from '../genero/genero.component.model';





@Component ({
    selector: 'app-view',
    templateUrl: './afiliado.component.html'
})

export class AfiliadoComponent implements OnInit {

    title = 'Nuevo Afiliado';
    afiliadoList: Afiliado;
    afiliado: Afiliado;
    form: any;

			generoList: Genero;
		    genero: Genero;




    constructor(private router: Router, 
				private afiliadoService: AfiliadoService, 
				private afiliadoFakeService: AfiliadoFakeService
		, private generoService: GeneroService
		, private generoFakeService: GeneroFakeService				
) {
		

	}

    ngOnInit() {
        //this.afiliado = this.afiliadoService.getAfiliado();
		this.afiliado = this.afiliadoFakeService.getAfiliado();
        this.loadAfiliados();

		//this.genero = this.generoService.getGenero();
		this.genero = this.generoFakeService.getGenero();
        this.loadGeneros();

    }

    loadAfiliados() {
      this.afiliadoFakeService.getAllAfiliado().subscribe(data => {
        if (data) {
          this.afiliadoList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the afiliados.', 'error');
      });
    }

	  /*  
      this.afiliadoService.getAllAfiliado().subscribe(data => {
        if (data) {
          this.afiliadoList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the afiliados.', 'error');
      });
    }*/

    save(afiliado, modal){
      this.afiliadoFakeService.saveAfiliado(this.afiliado).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Afiliado save successfully.', 'success');
        }else{
          swal('Error...', 'Afiliado save unsuccessfully.', 'error');
        }

      } );
    }

	/*  
    save(afiliado, modal){
      this.afiliadoService.saveAfiliado(this.afiliado).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Afiliado save successfully.', 'success');
        }else{
          swal('Error...', 'Afiliado save unsuccessfully.', 'error');
        }

      } );
    }*/

	

	/*	
	*/


	/*	
	*/

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
    loadGeneros() {
      this.generoService.getAllGenero().subscribe(data => {
        if (data) {
          this.generoList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the generos.', 'error');
      });
    }
	*/
	

	/* 
	*/
}

