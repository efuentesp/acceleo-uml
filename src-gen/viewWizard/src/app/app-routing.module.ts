import { NgModule }                     from '@angular/core';
import { Routes, RouterModule }         from '@angular/router';
import { BaseComponent}                 from './base/base.component';

import { AfiliadoMngComponent}       from './afiliado_mgmnt/afiliado_mgmnt.component';
import { AfiliadoComponent}       from './afiliado/afiliado.component';
import { BeneficiarioMngComponent}       from './beneficiario_mgmnt/beneficiario_mgmnt.component';
import { BeneficiarioComponent}       from './beneficiario/beneficiario.component';
		

export const appRoutes: Routes = [


	{ path: 'afiliado_mgmnt',  component: AfiliadoMngComponent},  
	{ path: 'afiliado',  component: AfiliadoComponent},  
	{ path: 'beneficiario_mgmnt',  component: BeneficiarioMngComponent},  
	{ path: 'beneficiario',  component: BeneficiarioComponent},  
    { path: '**', component: BaseComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true} )],
  exports: [RouterModule],
})

export class AppRoutingModule {}

