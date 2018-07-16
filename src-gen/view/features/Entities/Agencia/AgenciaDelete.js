import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import Loading from '../../Common/Loading/Loading';
import { fetchAgencia, deleteAgencia } from './actions';

class AgenciaDelete extends Component {

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

  onFormSubmit() {
    const parent_id = this.props.location.query.parent_id;
	this.props.deleteAgencia(this.props.params.id, parent_id);
  }

  render() {
    const { agencia, handleSubmit, submitSucceeded } = this.props;

    if (!agencia) {
      return (
        <ContentWrapper>
          <h3>
            <span className="mr">Borrar Agencia</span>
          </h3>
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
           <li className="active">Borrar Agencia</li>
        </ol>
        <h3>
          <span className="mr">Borrar Agencia</span>
        </h3>
        <Panel header="Agencia">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
            <Field
              type="label"
              name="pais"
              component={FormTextField}
              label="Pais" />
            <Field
              type="label"
              name="nombre"
              component={FormTextField}
              label="Nombre" />
            <ButtonToolbar>
              <Button
                type="submit"
                bsStyle="danger"
                disabled={submitSucceeded}>
                  <i className={`${submitSucceeded}`} />
                  <span> Borrar</span>
              </Button>
            </ButtonToolbar>
          </form>
        </Panel>
      </ContentWrapper>
    );
  }
}

AgenciaDelete.propTypes = {
  location: PropTypes.object,
  params: PropTypes.object.isRequired,
  initialize: PropTypes.func.isRequired,
  agencia: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  fetchAgencia: PropTypes.func.isRequired,
  deleteAgencia: PropTypes.func.isRequired
};

AgenciaDelete.contextTypes = {
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
  return { agencia: state.agencia.item };
}

const form = reduxForm({
  form: 'AgenciaDeleteForm',
  validate
});

export default connect(mapStateToProps, { fetchAgencia, deleteAgencia })(form(AgenciaDelete));
