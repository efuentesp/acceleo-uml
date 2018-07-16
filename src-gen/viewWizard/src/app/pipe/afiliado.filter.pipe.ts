
import { Injectable, Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter',
    pure: false
})

@Injectable()
export class SearchAfiliadoPipe implements PipeTransform {
      transform(items: any[], busquedaAfiliado): any {
        return busquedaAfiliado && items ? items.filter(item =>
				
					(item.foto.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.genero.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.observaciones.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.nss.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.nombre.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.monto_pension.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.apellido_materno.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.acta_nacimiento.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.semanas_cotizadas.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.beneficiario.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.decimal.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.apellido_paterno.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.fecha_afiliacion.indexOf(busquedaAfiliado) !== -1) 
				
||				
					(item.email.indexOf(busquedaAfiliado) !== -1) 
				

        ): items;
    }
}

