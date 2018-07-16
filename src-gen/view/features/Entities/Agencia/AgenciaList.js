
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import SweetAlert from 'react-bootstrap-sweetalert';
import { Row, Col, Table, ButtonToolbar } from 'react-bootstrap';

import Loading from '../../Common/Loading/Loading';

class AgenciaList extends Component {

renderEditItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/agencia/edit/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/agencia/edit/${item_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
   }
}

renderDeleteItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/agencia/delete/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/agencia/delete/${item_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
   }
}

  renderList() {
    return this.props.agencia.all.map((item) => {
      return (
        <tr key={item.id}>
		<td>{item.pais}</td>	
		<td>{item.nombre}</td>	
          <td>
            <ButtonToolbar>
			
			
              { this.renderEditItem(item.id) }
              { this.renderDeleteItem(item.id) }
            </ButtonToolbar>
          </td>
        </tr>
      );
    });
  }

  render() {
    if ((this.props.agencia || {}).loading) {
      return (
        <Loading />
      );
    }

    if ((this.props.agencia || {}).error) {
      return (
        <SweetAlert
          type="error"
          title={this.props.agencia.error.message}
          content="Comunicate con el Administrador del Sistema o intentalo más tarde."
          confirmBtnText="Intentar de nuevo"/>
      );
    }

    return (
      <Row>
        <Col sm={12}>
          <Table responsive striped>
            <thead>
              <tr>
				<th>Pais</th>
				<th>Nombre</th>
				<th>Operaciones</th>
              </tr>
            </thead>
            <tbody>
              {this.renderList()}
            </tbody>
          </Table>
        </Col>
      </Row>
    );
  }
}

AgenciaList.propTypes = {
  parentId: PropTypes.string,
  agencia: PropTypes.object.isRequired,
  searchTerm: PropTypes.string
};

function mapStateToProps(state) {
  return {
    agencia: state.agencia
  };
}

export default connect(mapStateToProps)(AgenciaList);

