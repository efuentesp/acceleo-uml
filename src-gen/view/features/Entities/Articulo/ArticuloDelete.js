import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import Loading from '../../Common/Loading/Loading';
import { fetchArticulo, deleteArticulo } from './actions';

class ArticuloDelete extends Component {

  constructor(props) {
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  componentWillMount() {
    this.props.fetchArticulo(this.props.params.id)
      .then(() => {
        this.props.initialize({
		"precio": this.props.articulo.precio,
		"descripcion": this.props.articulo.descripcion,
		"categoria": this.props.articulo.categoria,
		"cantidad": this.props.articulo.cantidad
        });
      });
  }

  onFormSubmit() {
    const parent_id = this.props.location.query.parent_id;
	this.props.deleteArticulo(this.props.params.id, parent_id);
  }

  render() {
    const { articulo, handleSubmit, submitSucceeded } = this.props;

    if (!articulo) {
      return (
        <ContentWrapper>
          <h3>
            <span className="mr">Borrar Articulo</span>
          </h3>
          <Panel header="Articulo">
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
           <li className="active">Articulo</li>
           <li>
             <Link to="/articulo_mgmnt">Administrar Articulo</Link>
           </li>
           <li className="active">Borrar Articulo</li>
        </ol>
        <h3>
          <span className="mr">Borrar Articulo</span>
        </h3>
        <Panel header="Articulo">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
            <Field
              type="label"
              name="precio"
              component={FormTextField}
              label="Precio" />
            <Field
              type="label"
              name="descripcion"
              component={FormTextField}
              label="Descripcion" />
            <Field
              type="label"
              name="categoria"
              component={FormTextField}
              label="Categoria" />
            <Field
              type="label"
              name="cantidad"
              component={FormTextField}
              label="Cantidad" />
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

ArticuloDelete.propTypes = {
  location: PropTypes.object,
  params: PropTypes.object.isRequired,
  initialize: PropTypes.func.isRequired,
  articulo: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  fetchArticulo: PropTypes.func.isRequired,
  deleteArticulo: PropTypes.func.isRequired
};

ArticuloDelete.contextTypes = {
  router: PropTypes.object.isRequired
};

const validate = values => {
  const errors = {};

  if (!values.precio) {
    errors.precio = 'Campo requerido.';
  }
  if (!values.descripcion) {
    errors.descripcion = 'Campo requerido.';
  }
  if (!values.categoria) {
    errors.categoria = 'Campo requerido.';
  }
  if (!values.cantidad) {
    errors.cantidad = 'Campo requerido.';
  }

  return errors;
};

function mapStateToProps(state) {
  return { articulo: state.articulo.item };
}

const form = reduxForm({
  form: 'ArticuloDeleteForm',
  validate
});

export default connect(mapStateToProps, { fetchArticulo, deleteArticulo })(form(ArticuloDelete));
