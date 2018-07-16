
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import SweetAlert from 'react-bootstrap-sweetalert';
import { Row, Col, Table, ButtonToolbar } from 'react-bootstrap';

import Loading from '../../Common/Loading/Loading';

class ClienteList extends Component {

renderEditItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/cliente/edit/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/cliente/edit/${item_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
   }
}

renderDeleteItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/cliente/delete/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/cliente/delete/${item_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
   }
}

  renderList() {
    return this.props.cliente.all.map((item) => {
      return (
        <tr key={item.id}>
		<td>{item.nombre}</td>	
		<td>{item.rfc}</td>	
		<td>{item.direccion}</td>	
		<td>{item.numero}</td>	
          <td>
            <ButtonToolbar>
			
			<Link className="btn btn-default" to={`/orden_mgmnt?cliente_id=${item.id}`}>
					<em className="fa fa-check-circle-o" />
					<span> Orden</span>
			</Link>
			
			
			
              { this.renderEditItem(item.id) }
              { this.renderDeleteItem(item.id) }
            </ButtonToolbar>
          </td>
        </tr>
      );
    });
  }

  render() {
    if ((this.props.cliente || {}).loading) {
      return (
        <Loading />
      );
    }

    if ((this.props.cliente || {}).error) {
      return (
        <SweetAlert
          type="error"
          title={this.props.cliente.error.message}
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
				<th>Nombre</th>
				<th>Rfc</th>
				<th>Direccion</th>
				<th>Numero</th>
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

ClienteList.propTypes = {
  parentId: PropTypes.string,
  cliente: PropTypes.object.isRequired,
  searchTerm: PropTypes.string
};

function mapStateToProps(state) {
  return {
    cliente: state.cliente
  };
}

export default connect(mapStateToProps)(ClienteList);

