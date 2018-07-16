import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import { fetchCliente } from '../Cliente/actions';
import { fetchOrden, createOrden } from './actions';

class OrdenCreate extends Component {

  constructor(props) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

componentWillMount() {
    const cliente_id = this.props.location.query.cliente_id;
    if (cliente_id) {
      this.props.fetchCliente(cliente_id)
      .then(() => {
        this.props.initialize({
          "cliente_id": cliente_id
        });
    });
  }
}

onFormSubmit(props) {
    const cliente_id = this.props.location.query.cliente_id;
    this.props.createOrden(props)
      .then(() => {
        const orden_mgmnt = (cliente_id) ? "/orden_mgmnt?cliente_id=" + cliente_id : "/orden_mgmnt";
        this.context.router.push(orden_mgmnt);
        toastr.success("Orden creado", `El Orden fue creado exitosamente.`);
    });
 }

  render() {
    const cliente_id = this.props.location.query.cliente_id;
    const { handleSubmit, pristine, submitSucceeded } = this.props;
	const orden_mgmnt = (cliente_id) ? "/orden_mgmnt?cliente_id=" + cliente_id : "/orden_mgmnt";
    return (
      <ContentWrapper>
        
        <ol className="breadcrumb pull-right">
                   <li>
                     <Link to="/">Inicio</Link>
                   </li>
                   <li className="active">Cliente</li>
                     <li>
                       <Link to="/cliente_mgmnt">Administrar Cliente</Link>
                     </li>
                   <li>
                     <Link to={orden_mgmnt}>Administrar Orden</Link>
                   </li>
                   <li className="active">Agregar Orden</li>
                </ol><h3>Agregar Orden <small>{(this.props.cliente || {}).nombre}</small></h3>
        
        
        
        
        <Panel header="Orden">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
			<Field
              name="almacen"
              component={FormTextField}
              label="Almacen" />
			<Field
              name="cliente_id"
              component={FormTextField}
              label="Cliente_id" />
			<Field
              name="estatus"
              component={FormTextField}
              label="Estatus" />
			<Field
              name="numero"
              component={FormTextField}
              label="Numero" />
			<Field
              name="fecha"
              component={FormTextField}
              label="Fecha" />
            <ButtonToolbar>
              <Button
                type="submit"
                bsStyle="primary"
                disabled={pristine || submitSucceeded}>
                  <i className={`${submitSucceeded ? 'fa fa-refresh fa-spin' : 'fa fa-save'}`} />
                  <span> Guardar</span>
              </Button>
            </ButtonToolbar>
          </form>
        </Panel>
      </ContentWrapper>
    );
  }
}

OrdenCreate.propTypes = {
  location: PropTypes.object,
  initialize: PropTypes.func.isRequired,
  cliente: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  createOrden: PropTypes.func.isRequired,
  fetchCliente: PropTypes.func
};

OrdenCreate.contextTypes = {
  router: PropTypes.object.isRequired
};

const validate = values => {
  const errors = {};

  if (!values.almacen) {
    errors.almacen = 'Campo requerido.';
  }
  if (!values.cliente_id) {
    errors.cliente_id = 'Campo requerido.';
  }
  if (!values.estatus) {
    errors.estatus = 'Campo requerido.';
  }
  if (!values.numero) {
    errors.numero = 'Campo requerido.';
  }
  if (!values.fecha) {
    errors.fecha = 'Campo requerido.';
  }

  return errors;
};

function mapStateToProps(state) {
  return {
    cliente: state.cliente.item,
    orden: state.orden.item
  };
}

const form = reduxForm({
  form: 'OrdenCreateForm',
  validate
});

export default connect(mapStateToProps, { fetchCliente, fetchOrden, createOrden })(form(OrdenCreate));

