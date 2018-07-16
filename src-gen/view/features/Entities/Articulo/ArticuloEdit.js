
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import Loading from '../../Common/Loading/Loading';
import { fetchArticulo, updateArticulo } from './actions';

class ArticuloEdit extends Component {

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

 onFormSubmit(props) {
    const parent_id = this.props.location.query.parent_id;
    this.props.updateArticulo(this.props.params.id, props)
      .then(() => {
        const articulo_mgmnt = (parent_id) ? "/articulo_mgmnt?parent_id=" + parent_id : "/articulo_mgmnt";
        this.context.router.push(articulo_mgmnt);
        toastr.success("Articulo modificado", `El Articulo fue modificado exitosamente.`);
      });
  }

  render() {
    const { articulo, handleSubmit, reset, pristine, submitSucceeded } = this.props;

    if ((articulo || {}).loading) {
      return (
        <ContentWrapper>
          <h3>Editar Articulo</h3>
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
           <li className="active">Editar Articulo</li>
        </ol>
        <h3>
          <span className="mr">Editar Articulo</span>
        </h3>
        <Panel header="Articulo">
          <form
            role="form"
            onSubmit={handleSubmit(this.onFormSubmit)}>
            <Field
          name="precio"
              component={FormTextField}
              label="Precio" />
            <Field
          name="descripcion"
              component={FormTextField}
              label="Descripcion" />
            <Field
          name="categoria"
              component={FormTextField}
              label="Categoria" />
            <Field
          name="cantidad"
              component={FormTextField}
              label="Cantidad" />
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

ArticuloEdit.contextTypes = {
  router: PropTypes.object.isRequired
};

ArticuloEdit.propTypes = {
  location: PropTypes.object,
  params: PropTypes.object.isRequired,
  fetchArticulo: PropTypes.func.isRequired,
  updateArticulo: PropTypes.func.isRequired,
  initialize: PropTypes.func.isRequired,
  articulo: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  reset: PropTypes.func.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  pristine: PropTypes.bool.isRequired,
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
  form: 'ArticuloEditForm',
  validate
});

export default connect(mapStateToProps, { fetchArticulo, updateArticulo })(form(ArticuloEdit));
