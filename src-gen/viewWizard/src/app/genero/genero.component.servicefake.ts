import { Injectable }                              from '@angular/core';
import { environment }                             from "../../environments/environment";
import { Http, Response }                          from "@angular/http";
import { Headers, RequestOptions }                 from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Genero }                           from '../genero/genero.component.model';
const GENERO_API: string = "./assets/data/genero.json";

@Injectable()
export class GeneroFakeService {

    private isGeneroFormValid: boolean = false;
    private env: any = environment;
    private genero = new Genero();

    constructor(private http: Http) {
    }

    getAllGenero(){
     	return this.http.get(GENERO_API).map(res => res.json().generos).catch((error: any) => Observable.throw(error.json()));
    }

    resetGenero(): Genero {
        this.clear();
        return this.genero;
    }

    getGenero(): Genero {
        var genero: Genero = {
					female: this.genero.female,     
					male: this.genero.male,     
					id: this.genero.id
        };
        return genero;
    }

    setGenero(genero: Genero) {
       
	this.isGeneroFormValid = true;
			this.genero.female = genero.female;    
			this.genero.male = genero.male;    
		this.genero.id        = genero.id;
        this.validateGenero();
    }

    isFormValid() {
        return this.isGeneroFormValid;
    }

    validateGenero() {

    }

    clear() {

			this.genero.female = '';    
			this.genero.male = '';    
			this.genero.id = null;
    }

    saveGenero(genero){
      return this.http.post(this.env.api + "/genero", genero).map(res => res  );
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
