import { Injectable }                              from '@angular/core';
import { environment }                             from "../../environments/environment";
import { Http, Response }                          from "@angular/http";
import { Headers, RequestOptions }                 from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Afiliado }                           from '../afiliado/afiliado.component.model';

@Injectable()
export class AfiliadoService {

    private isAfiliadoFormValid: boolean = false;
    private env: any = environment;
    private afiliado = new Afiliado();

    constructor(private http: Http) {
    }

    getAllAfiliado(){
      return this.http.get(this.env.api + "/afiliado").map(res => res.json()).catch(AfiliadoService.handleError);
    }

    resetAfiliado(): Afiliado {
        this.clear();
        return this.afiliado;
    }

    getAfiliado(): Afiliado {
        var afiliado: Afiliado = {
					foto: this.afiliado.foto,     
					observaciones: this.afiliado.observaciones,     
					nss: this.afiliado.nss,     
					nombre: this.afiliado.nombre,     
					monto_pension: this.afiliado.monto_pension,     
					apellido_materno: this.afiliado.apellido_materno,     
					acta_nacimiento: this.afiliado.acta_nacimiento,     
					semanas_cotizadas: this.afiliado.semanas_cotizadas,     
					decimal: this.afiliado.decimal,     
					apellido_paterno: this.afiliado.apellido_paterno,     
					fecha_afiliacion: this.afiliado.fecha_afiliacion,     
					email: this.afiliado.email,     
					id: this.afiliado.id
        };
        return afiliado;
    }

    setAfiliado(afiliado: Afiliado) {
       
	this.isAfiliadoFormValid = true;
			this.afiliado.foto = afiliado.foto;    
			this.afiliado.observaciones = afiliado.observaciones;    
			this.afiliado.nss = afiliado.nss;    
			this.afiliado.nombre = afiliado.nombre;    
			this.afiliado.monto_pension = afiliado.monto_pension;    
			this.afiliado.apellido_materno = afiliado.apellido_materno;    
			this.afiliado.acta_nacimiento = afiliado.acta_nacimiento;    
			this.afiliado.semanas_cotizadas = afiliado.semanas_cotizadas;    
			this.afiliado.decimal = afiliado.decimal;    
			this.afiliado.apellido_paterno = afiliado.apellido_paterno;    
			this.afiliado.fecha_afiliacion = afiliado.fecha_afiliacion;    
			this.afiliado.email = afiliado.email;    
		this.afiliado.id        = afiliado.id;
        this.validateAfiliado();
    }

    isFormValid() {
        return this.isAfiliadoFormValid;
    }

    validateAfiliado() {

    }

    clear() {

			this.afiliado.foto = '';    
			this.afiliado.observaciones = '';    
			this.afiliado.nss = '';    
			this.afiliado.nombre = '';    
			this.afiliado.monto_pension = '';    
			this.afiliado.apellido_materno = '';    
			this.afiliado.acta_nacimiento = '';    
			this.afiliado.semanas_cotizadas = '';    
			this.afiliado.decimal = '';    
			this.afiliado.apellido_paterno = '';    
			this.afiliado.fecha_afiliacion = '';    
			this.afiliado.email = '';    
			this.afiliado.id = null;
    }

    saveAfiliado(afiliado){
      return this.http.post(this.env.api + "/afiliado", afiliado).map(res => res  );
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
