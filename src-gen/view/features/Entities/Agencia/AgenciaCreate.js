import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import { fetchAgencia, createAgencia, fetchParent } from './actions';

class AgenciaCreate extends Component {

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
    this.props.createAgencia(props)
      .then(() => {
        const agencia_mgmnt = (parent_id) ? "/agencia_mgmnt?parent_id=" + parent_id : "/agencia_mgmnt";
        this.context.router.push(agencia_mgmnt);
        toastr.success("Agencia creado", `El Agencia fue creado exitosamente.`);
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
                   <li className="active">Agencia</li>
                   <li>
                     <Link to="/agencia_mgmnt">Administrar Agencia</Link>
                   </li>
                   <li className="active">Agregar Agencia</li>
                </ol>
                <h3>
                  <span className="mr">Agregar de Agencia</span>
                </h3>
        
        
        <Panel header="Agencia">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
			<Field
              name="pais"
              component={FormTextField}
              label="Pais" />
			<Field
              name="nombre"
              component={FormTextField}
              label="Nombre" />
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

AgenciaCreate.propTypes = {
  location: PropTypes.object,
  initialize: PropTypes.func.isRequired,
  parent: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  createAgencia: PropTypes.func.isRequired,
  fetchParent: PropTypes.func
};

AgenciaCreate.contextTypes = {
  router: PropTypes.object.isRequired
};

const validate = values => {
  const errors = {};

  if (!values.pais) {
    errors.pais = 'Campo requerido.';
  }
  if (!values.nombre) {
    errors.nombre = 'Campo requerido.';
  }

  return errors;
};

function mapStateToProps(state) {
  return {
    
    agencia: state.agencia.item
  };
}

const form = reduxForm({
  form: 'AgenciaCreateForm',
  validate
});

export default connect(mapStateToProps, { fetchParent, fetchAgencia, createAgencia })(form(AgenciaCreate));

