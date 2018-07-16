import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import { fetchCliente, createCliente, fetchParent } from './actions';

class ClienteCreate extends Component {

  constructor(props) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

componentWillMount() {
    const parent_id = this.props.location.query.parent_id;
    if (parent_id) {
      this.props.fetchParent(parent_id)
      .then(() => {
        this.props.initialize({
          "parent_id": parent_id
        });
    });
  }
}

onFormSubmit(props) {
    const parent_id = this.props.location.query.parent_id;
    this.props.createCliente(props)
      .then(() => {
        const cliente_mgmnt = (parent_id) ? "/cliente_mgmnt?parent_id=" + parent_id : "/cliente_mgmnt";
        this.context.router.push(cliente_mgmnt);
        toastr.success("Cliente creado", `El Cliente fue creado exitosamente.`);
    });
 }

  render() {
    const { handleSubmit, pristine, submitSucceeded } = this.props;
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
                   <li className="active">Agregar Cliente</li>
                </ol>
                <h3>
                  <span className="mr">Agregar de Cliente</span>
                </h3>
        
        
        <Panel header="Cliente">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
			<Field
              name="nombre"
              component={FormTextField}
              label="Nombre" />
			<Field
              name="rfc"
              component={FormTextField}
              label="Rfc" />
			<Field
              name="direccion"
              component={FormTextField}
              label="Direccion" />
			<Field
              name="numero"
              component={FormTextField}
              label="Numero" />
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

ClienteCreate.propTypes = {
  location: PropTypes.object,
  initialize: PropTypes.func.isRequired,
  parent: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  createCliente: PropTypes.func.isRequired,
  fetchParent: PropTypes.func
};

ClienteCreate.contextTypes = {
  router: PropTypes.object.isRequired
};

const validate = values => {
  const errors = {};

  if (!values.nombre) {
    errors.nombre = 'Campo requerido.';
  }
  if (!values.rfc) {
    errors.rfc = 'Campo requerido.';
  }
  if (!values.direccion) {
    errors.direccion = 'Campo requerido.';
  }
  if (!values.numero) {
    errors.numero = 'Campo requerido.';
  }

  return errors;
};

function mapStateToProps(state) {
  return {
    
    cliente: state.cliente.item
  };
}

const form = reduxForm({
  form: 'ClienteCreateForm',
  validate
});

export default connect(mapStateToProps, { fetchParent, fetchCliente, createCliente })(form(ClienteCreate));

