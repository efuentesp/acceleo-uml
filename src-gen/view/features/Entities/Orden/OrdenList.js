
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import SweetAlert from 'react-bootstrap-sweetalert';
import { Row, Col, Table, ButtonToolbar } from 'react-bootstrap';

import Loading from '../../Common/Loading/Loading';

class OrdenList extends Component {

renderEditItem(item_id) {
    const cliente_id = this.props.clienteId;
    if (cliente_id) {
      return (
        <Link className="btn btn-default" to={`/orden/edit/${item_id}?cliente_id=${cliente_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/orden/edit/${item_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
   }
}

renderDeleteItem(item_id) {
    const cliente_id = this.props.clienteId;
    if (cliente_id) {
      return (
        <Link className="btn btn-default" to={`/orden/delete/${item_id}?cliente_id=${cliente_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/orden/delete/${item_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
   }
}

  renderList() {
    return this.props.orden.all.map((item) => {
      return (
        <tr key={item.id}>
		<td>{item.almacen}</td>	
		<td>{item.cliente_id}</td>	
		<td>{item.estatus}</td>	
		<td>{item.numero}</td>	
		<td>{item.fecha}</td>	
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
    if ((this.props.orden || {}).loading) {
      return (
        <Loading />
      );
    }

    if ((this.props.orden || {}).error) {
      return (
        <SweetAlert
          type="error"
          title={this.props.orden.error.message}
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
				<th>Almacen</th>
				<th>Cliente_id</th>
				<th>Estatus</th>
				<th>Numero</th>
				<th>Fecha</th>
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

OrdenList.propTypes = {
  clienteId: PropTypes.string,
  orden: PropTypes.object.isRequired,
  searchTerm: PropTypes.string
};

function mapStateToProps(state) {
  return {
    orden: state.orden
  };
}

export default connect(mapStateToProps)(OrdenList);

