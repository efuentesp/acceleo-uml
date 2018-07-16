
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import Loading from '../../Common/Loading/Loading';
import { fetchOrden, updateOrden } from './actions';

class OrdenEdit extends Component {

  constructor(props) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  componentWillMount() {
    this.props.fetchOrden(this.props.params.id)
      .then(() => {
        this.props.initialize({
		"almacen": this.props.orden.almacen,
		"cliente_id": this.props.orden.cliente_id,
		"estatus": this.props.orden.estatus,
		"numero": this.props.orden.numero,
		"fecha": this.props.orden.fecha
        });
      });
  }

 onFormSubmit(props) {
    const cliente_id = this.props.location.query.cliente_id;
    this.props.updateOrden(this.props.params.id, props)
      .then(() => {
        const orden_mgmnt = (cliente_id) ? "/orden_mgmnt?cliente_id=" + cliente_id : "/orden_mgmnt";
        this.context.router.push(orden_mgmnt);
        toastr.success("Orden modificado", `El Orden fue modificado exitosamente.`);
      });
  }

  render() {
    const cliente_id = this.props.location.query.cliente_id;
    const { orden, handleSubmit, reset, pristine, submitSucceeded } = this.props;

    if ((orden || {}).loading) {
      return (
        <ContentWrapper>
          <h3>Editar Orden</h3>
          <Panel header="Orden">
            <Loading />
          </Panel>
        </ContentWrapper>
      );
    }

    return (
      <ContentWrapper>
        <ol className="breadcrumb pull-right">
           <li>
             <Link to="/">Inicio</Link>
           </li>
           <li className="active">Orden</li>
           <li>
             <Link to="/orden_mgmnt">Administrar Orden</Link>
           </li>
           <li className="active">Editar Orden</li>
        </ol>
        <h3>
          <span className="mr">Editar Orden</span>
        </h3>
        <Panel header="Orden">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
            <Field
          name="almacen"
              component={FormTextField}
              label="Almacen" />
            <Field
              type={(cliente_id)? "label" : ""}
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
              <Button
                type="button"
                bsStyle="default"
                disabled={pristine || submitSucceeded}
                onClick={reset}>
                  <i className="fa fa-undo" />
                  <span> Deshacer</span>
              </Button>
            </ButtonToolbar>
          </form>
        </Panel>
      </ContentWrapper>
    );
  }
}

OrdenEdit.contextTypes = {
  router: PropTypes.object.isRequired
};

OrdenEdit.propTypes = {
  location: PropTypes.object,
  params: PropTypes.object.isRequired,
  fetchOrden: PropTypes.func.isRequired,
  updateOrden: PropTypes.func.isRequired,
  initialize: PropTypes.func.isRequired,
  orden: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  reset: PropTypes.func.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  pristine: PropTypes.bool.isRequired,
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
  return { orden: state.orden.item };
}

const form = reduxForm({
  form: 'OrdenEditForm',
  validate
});

export default connect(mapStateToProps, { fetchOrden, updateOrden })(form(OrdenEdit));
