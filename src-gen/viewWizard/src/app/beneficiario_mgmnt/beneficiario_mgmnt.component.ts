import { Component, OnInit, ViewChild}                     from '@angular/core';
import { Router }                                          from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import swal from 'sweetalert2';

import { BeneficiarioFakeService }                              from '../beneficiario/beneficiario.component.servicefake';
import { BeneficiarioService }                                  from '../beneficiario/beneficiario.component.service';
import { Beneficiario }                                         from '../beneficiario/beneficiario.component.model';





@Component ({
    selector: 'app-view',
    templateUrl: './beneficiario_mgmnt.component.html'
})

export class BeneficiarioMngComponent implements OnInit {

    title = 'Nuevo Beneficiario';
    beneficiarioList: Beneficiario;
    beneficiario: Beneficiario;
    form: any;





    constructor(private router: Router, private beneficiarioService: BeneficiarioService, private beneficiarioFakeService: BeneficiarioFakeService
) {}

    ngOnInit() {
		//this.beneficiario = this.beneficiarioFakeService.getBeneficiario();
        // this.beneficiario = this.beneficiarioService.getBeneficiario();
        this.loadBeneficiarios();


    }

    loadBeneficiarios() {
      this.beneficiarioFakeService.getAllBeneficiario().subscribe(data => {
        if (data) {
          this.beneficiarioList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the beneficiarios.', 'error');
      });
    }

	/*
    loadBeneficiarios() {
      this.beneficiarioService.getAllBeneficiario().subscribe(data => {
        if (data) {
          this.beneficiarioList = data;
        }
      }, error => {
        swal('Error...', 'An error occurred while calling the beneficiarios.', 'error');
      });
    }*/

    save(beneficiario, modal){
      this.beneficiarioFakeService.saveBeneficiario(this.beneficiario).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Beneficiario save successfully.', 'success');
        }else{
          swal('Error...', 'Beneficiario save unsuccessfully.', 'error');
        }

      } );
    }

	/*
    save(beneficiario, modal){
      this.beneficiarioService.saveBeneficiario(this.beneficiario).subscribe(res => {
        if (res.status == 201){
          swal('Success...', 'Beneficiario save successfully.', 'success');
        }else{
          swal('Error...', 'Beneficiario save unsuccessfully.', 'error');
        }

      } );
    }
	*/


	/*
	*/


	/*
	*/


	/*
	*/
	

	/*
	*/

  add(){
    this.router.navigate(['/beneficiario']);
  }

}

