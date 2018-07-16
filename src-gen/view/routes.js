import React from 'react';
import { Route, IndexRoute } from 'react-router';

import App from './features/App/App';
import HomePage from './features/Home/Home';
import LoginPage from './features/Common/Pages/Login';
import NotFoundPage from './features/Common/Pages/NotFound';

import ClienteManagement from './features/Entities/Cliente/ClienteManagement';
import ClienteCreate from './features/Entities/Cliente/ClienteCreate';
import ClienteEdit from './features/Entities/Cliente/ClienteEdit';
import ClienteDelete from './features/Entities/Cliente/ClienteDelete';					
import OrdenManagement from './features/Entities/Orden/OrdenManagement';
import OrdenCreate from './features/Entities/Orden/OrdenCreate';
import OrdenEdit from './features/Entities/Orden/OrdenEdit';
import OrdenDelete from './features/Entities/Orden/OrdenDelete';					
import ArticuloManagement from './features/Entities/Articulo/ArticuloManagement';
import ArticuloCreate from './features/Entities/Articulo/ArticuloCreate';
import ArticuloEdit from './features/Entities/Articulo/ArticuloEdit';
import ArticuloDelete from './features/Entities/Articulo/ArticuloDelete';					
import AgenciaManagement from './features/Entities/Agencia/AgenciaManagement';
import AgenciaCreate from './features/Entities/Agencia/AgenciaCreate';
import AgenciaEdit from './features/Entities/Agencia/AgenciaEdit';
import AgenciaDelete from './features/Entities/Agencia/AgenciaDelete';					

// import ProductoManagement from './features/Entities/Producto/ProductoManagement';
// import ProductoCreate from './features/Entities/Producto/ProductoCreate';
// import ProductoEdit from './features/Entities/Producto/ProductoEdit';
// import ProductoDelete from './features/Entities/Producto/ProductoDelete';

import RoleManagement from './features/Common/Security/Role/RoleManagement';
import RoleEdit from './features/Common/Security/Role/RoleEdit';
import RoleDelete from './features/Common/Security/Role/RoleDelete';

export default (
  <Route path="/" component={App}>
    <IndexRoute component={HomePage}/>

	<Route path="cliente_mgmnt" component={ClienteManagement} />
	<Route path="cliente" component={ClienteCreate} />
	<Route path="cliente/:id" component={ClienteCreate} />
	<Route path="cliente/edit/:id" component={ClienteEdit} />
	<Route path="cliente/delete/:id" component={ClienteDelete} />
	
	
	
	
	<Route path="orden_mgmnt" component={OrdenManagement} />
	<Route path="orden" component={OrdenCreate} />
	<Route path="orden/:id" component={OrdenCreate} />
	<Route path="orden/edit/:id" component={OrdenEdit} />
	<Route path="orden/delete/:id" component={OrdenDelete} />
	
	
	
	<Route path="orden/:id" component={OrdenCreate} />
	
	
	<Route path="articulo_mgmnt" component={ArticuloManagement} />
	<Route path="articulo" component={ArticuloCreate} />
	<Route path="articulo/:id" component={ArticuloCreate} />
	<Route path="articulo/edit/:id" component={ArticuloEdit} />
	<Route path="articulo/delete/:id" component={ArticuloDelete} />
	
	
	<Route path="agencia_mgmnt" component={AgenciaManagement} />
	<Route path="agencia" component={AgenciaCreate} />
	<Route path="agencia/:id" component={AgenciaCreate} />
	<Route path="agencia/edit/:id" component={AgenciaEdit} />
	<Route path="agencia/delete/:id" component={AgenciaDelete} />
	
	

    <Route path="roles" component={RoleManagement} />
    <Route path="roles/edit/:id" component={RoleEdit} />
    <Route path="roles/delete/:id" component={RoleDelete} />

    <Route path="/login" component={LoginPage}/>
    <Route path="*" component={NotFoundPage}/>
  </Route>
);

//---- Mover junto a las dem?s rutas
// <Route path="producto_mgmnt" component={ProductoManagement} />
// <Route path="producto" component={ProductoCreate} />
// <Route path="producto/edit/:id" component={ProductoEdit} />
// <Route path="producto/delete/:id" component={ProductoDelete} />




