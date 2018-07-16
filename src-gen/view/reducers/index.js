import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import { reducer as formReducer } from 'redux-form';
import { reducer as toastrReducer } from 'react-redux-toastr';


import ClienteReducer from '../features/Entities/Cliente/reducer';
import OrdenReducer from '../features/Entities/Orden/reducer';
import ArticuloReducer from '../features/Entities/Articulo/reducer';
import AgenciaReducer from '../features/Entities/Agencia/reducer';


import RoleReducer from '../features/Common/Security/Role/reducer';

const rootReducer = combineReducers({
  routing: routerReducer,
  form: formReducer,
  toastr: toastrReducer,
	cliente: ClienteReducer,
	orden: OrdenReducer,
	articulo: ArticuloReducer,
	agencia: AgenciaReducer,
  roles: RoleReducer
});

export default rootReducer;


