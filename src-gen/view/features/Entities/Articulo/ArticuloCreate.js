import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import { Panel, ButtonToolbar, Button } from 'react-bootstrap';
import { reduxForm, Field } from 'redux-form';
import { toastr } from 'react-redux-toastr';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import FormTextField from "../../Common/Form/FormTextField";
import { fetchArticulo, createArticulo, fetchParent } from './actions';

class ArticuloCreate extends Component {

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
    this.props.createArticulo(props)
      .then(() => {
        const articulo_mgmnt = (parent_id) ? "/articulo_mgmnt?parent_id=" + parent_id : "/articulo_mgmnt";
        this.context.router.push(articulo_mgmnt);
        toastr.success("Articulo creado", `El Articulo fue creado exitosamente.`);
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
                   <li className="active">Articulo</li>
                   <li>
                     <Link to="/articulo_mgmnt">Administrar Articulo</Link>
                   </li>
                   <li className="active">Agregar Articulo</li>
                </ol>
                <h3>
                  <span className="mr">Agregar de Articulo</span>
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
            </ButtonToolbar>
          </form>
        </Panel>
      </ContentWrapper>
    );
  }
}

ArticuloCreate.propTypes = {
  location: PropTypes.object,
  initialize: PropTypes.func.isRequired,
  parent: PropTypes.object,
  handleSubmit: PropTypes.func.isRequired,
  pristine: PropTypes.bool.isRequired,
  submitSucceeded: PropTypes.bool.isRequired,
  createArticulo: PropTypes.func.isRequired,
  fetchParent: PropTypes.func
};

ArticuloCreate.contextTypes = {
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
  return {
    
    articulo: state.articulo.item
  };
}

const form = reduxForm({
  form: 'ArticuloCreateForm',
  validate
});

export default connect(mapStateToProps, { fetchParent, fetchArticulo, createArticulo })(form(ArticuloCreate));

