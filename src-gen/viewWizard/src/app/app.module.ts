import { NgModule }           from '@angular/core';
import { BrowserModule }      from '@angular/platform-browser';
import { FormsModule }        from '@angular/forms';
import { HttpModule, Http }   from '@angular/http';

/* App Root */
import { AppComponent }                         from './app.component';

/* Begin Components */
import { BaseComponent }              			from './base/base.component';


import { AfiliadoMngComponent}    from './afiliado_mgmnt/afiliado_mgmnt.component';
import { AfiliadoComponent}       from './afiliado/afiliado.component';
import { AfiliadoFakeService }    from './afiliado/afiliado.component.servicefake';
import { AfiliadoService }        from './afiliado/afiliado.component.service';
import { SearchAfiliadoPipe }     from './pipe/afiliado.filter.pipe';

import { GeneroFakeService }        from './genero/genero.component.servicefake';		
import { GeneroService }        from './genero/genero.component.service';	

import { BeneficiarioMngComponent}    from './beneficiario_mgmnt/beneficiario_mgmnt.component';
import { BeneficiarioComponent}       from './beneficiario/beneficiario.component';
import { BeneficiarioFakeService }    from './beneficiario/beneficiario.component.servicefake';
import { BeneficiarioService }        from './beneficiario/beneficiario.component.service';
import { SearchBeneficiarioPipe }     from './pipe/beneficiario.filter.pipe';



/* Routing Module */
import { AppRoutingModule }    from './app-routing.module';
import { NgxPaginationModule}  from 'ngx-pagination';

@NgModule({
    imports:      [ BrowserModule,
                    FormsModule,
                    AppRoutingModule,
					NgxPaginationModule,
                    HttpModule
                  ],
    providers:    [
				{ provide: AfiliadoService,   useClass: AfiliadoService },
				{ provide: AfiliadoFakeService,   useClass: AfiliadoFakeService },
				{ provide: GeneroService,   useClass: GeneroService },
				{ provide: GeneroFakeService,   useClass: GeneroFakeService },
				{ provide: BeneficiarioService,   useClass: BeneficiarioService },
				{ provide: BeneficiarioFakeService,   useClass: BeneficiarioFakeService },
        

                  ],
    declarations: [ 		
				AfiliadoMngComponent,
				AfiliadoComponent,
				SearchAfiliadoPipe,
				BeneficiarioMngComponent,
				BeneficiarioComponent,
				SearchBeneficiarioPipe,
				BaseComponent,
				AppComponent
				  ],
    bootstrap:    [ AppComponent ]
})

export class AppModule {}

