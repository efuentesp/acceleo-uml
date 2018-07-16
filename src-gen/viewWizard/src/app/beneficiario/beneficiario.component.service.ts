import { Injectable }                              from '@angular/core';
import { environment }                             from "../../environments/environment";
import { Http, Response }                          from "@angular/http";
import { Headers, RequestOptions }                 from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Beneficiario }                           from '../beneficiario/beneficiario.component.model';

@Injectable()
export class BeneficiarioService {

    private isBeneficiarioFormValid: boolean = false;
    private env: any = environment;
    private beneficiario = new Beneficiario();

    constructor(private http: Http) {
    }

    getAllBeneficiario(){
      return this.http.get(this.env.api + "/beneficiario").map(res => res.json()).catch(BeneficiarioService.handleError);
    }

    resetBeneficiario(): Beneficiario {
        this.clear();
        return this.beneficiario;
    }

    getBeneficiario(): Beneficiario {
        var beneficiario: Beneficiario = {
					curp: this.beneficiario.curp,     
					apellido_paterno: this.beneficiario.apellido_paterno,     
					fecha_nacimiento: this.beneficiario.fecha_nacimiento,     
					parentesco: this.beneficiario.parentesco,     
					apellido_materno: this.beneficiario.apellido_materno,     
					nombre: this.beneficiario.nombre,     
					id: this.beneficiario.id
        };
        return beneficiario;
    }

    setBeneficiario(beneficiario: Beneficiario) {
       
	this.isBeneficiarioFormValid = true;
			this.beneficiario.curp = beneficiario.curp;    
			this.beneficiario.apellido_paterno = beneficiario.apellido_paterno;    
			this.beneficiario.fecha_nacimiento = beneficiario.fecha_nacimiento;    
			this.beneficiario.parentesco = beneficiario.parentesco;    
			this.beneficiario.apellido_materno = beneficiario.apellido_materno;    
			this.beneficiario.nombre = beneficiario.nombre;    
		this.beneficiario.id        = beneficiario.id;
        this.validateBeneficiario();
    }

    isFormValid() {
        return this.isBeneficiarioFormValid;
    }

    validateBeneficiario() {

    }

    clear() {

			this.beneficiario.curp = '';    
			this.beneficiario.apellido_paterno = '';    
			this.beneficiario.fecha_nacimiento = '';    
			this.beneficiario.parentesco = '';    
			this.beneficiario.apellido_materno = '';    
			this.beneficiario.nombre = '';    
			this.beneficiario.id = null;
    }

    saveBeneficiario(beneficiario){
      return this.http.post(this.env.api + "/beneficiario", beneficiario).map(res => res  );
    }

	private static handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
        if (error.status === 404) {
            errMsg = 'Resource was not found';
        } else {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = 'Error';
        }
    } else {
        errMsg = error.message ? error.message : error.toString();
    }

    return Observable.throw(errMsg);
    }
}
