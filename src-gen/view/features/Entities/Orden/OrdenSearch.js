import React, { Component, PropTypes } from 'react';
import { Button } from 'react-bootstrap';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';

class OrdenSearch extends Component {

  render() {
    const { handleSubmit, submitting } = this.props;

    return(
      <form
        role="form"
        className="input-group"
        onSubmit={handleSubmit(this.props.onSearchSubmit)}>
        <Field
          name="term"
          component="Input"
          className="form-control"
          placeholder="Buscar Orden..." />
        <span className="input-group-btn">
          <Button
            type="submit"
            className="btn btn-green"
            disabled={submitting}>
              <i className={`${submitting ? 'fa fa-refresh fa-spin' : 'fa fa-search'}`} />
          </Button>
        </span>
      </form>
    );
  }
}

OrdenSearch.propTypes = {
 clienteId: PropTypes.string,
  handleSubmit: PropTypes.func.isRequired,
  submitting: PropTypes.bool.isRequired,
  onSearchSubmit: PropTypes.func.isRequired
};

const form = reduxForm({
  form: 'OrdenSearchForm',
});

export default connect(null, null)(form(OrdenSearch));
