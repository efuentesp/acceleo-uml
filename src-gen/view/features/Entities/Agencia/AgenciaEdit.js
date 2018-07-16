
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import Loading from '../../Common/Loading/Loading';
import { fetchAgencia, updateAgencia } from './actions';

class AgenciaEdit extends Component {

  constructor(props) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  componentWillMount() {
    this.props.fetchAgencia(this.props.params.id)
      .then(() => {
        this.props.initialize({
		"pais": this.props.agencia.pais,
		"nombre": this.props.agencia.nombre
        });
      });
  }

 onFormSubmit(props) {
    const parent_id = this.props.location.query.parent_id;
    this.props.updateAgencia(this.props.params.id, props)
      .then(() => {
        const agencia_mgmnt = (parent_id) ? "/agencia_mgmnt?parent_id=" + parent_id : "/agencia_mgmnt";
        this.context.router.push(agencia_mgmnt);
        toastr.success("Agencia modificado", `El Agencia fue modificado exitosamente.`);
      });
  }

  render() {
    const { agencia, handleSubmit, reset, pristine, submitSucceeded } = this.props;

    if ((agencia || {}).loading) {
      return (
        <ContentWrapper>
          <h3>Editar Agencia</h3>
          <Panel header="Agencia">
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
           <li className="active">Agencia</li>
           <li>
             <Link to="/agencia_mgmnt">Administrar Agencia</Link>
           </li>
           <li className="active">Editar Agencia</li>
        </ol>
        <h3>
          <span className="mr">Editar Agencia</span>
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

AgenciaEdit.contextTypes = {
  router: PropTypes.object.isRequired
};

AgenciaEdit.propTypes = {
  location: PropTypes.object,
  params: PropTypes.object.isRequired,
  fetchAgencia: PropTypes.func.isRequired,
  updateAgencia: PropTypes.func.isRequired,
  initialize: PropTypes.func.isRequired,
  agencia: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  reset: PropTypes.func.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  pristine: PropTypes.bool.isRequired,
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
  return { agencia: state.agencia.item };
}

const form = reduxForm({
  form: 'AgenciaEditForm',
  validate
});

export default connect(mapStateToProps, { fetchAgencia, updateAgencia })(form(AgenciaEdit));
