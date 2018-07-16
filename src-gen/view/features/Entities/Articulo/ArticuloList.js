
import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux';
import SweetAlert from 'react-bootstrap-sweetalert';
import { Row, Col, Table, ButtonToolbar } from 'react-bootstrap';

import Loading from '../../Common/Loading/Loading';

class ArticuloList extends Component {

renderEditItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/articulo/edit/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/articulo/edit/${item_id}`}>
          <em className="fa fa-pencil" />
        </Link>
      );
   }
}

renderDeleteItem(item_id) {
    const parent_id = this.props.parentId;
    if (parent_id) {
      return (
        <Link className="btn btn-default" to={`/articulo/delete/${item_id}?parent_id=${parent_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-default" to={`/articulo/delete/${item_id}`}>
          <em className="fa fa-trash" />
        </Link>
      );
   }
}

  renderList() {
    return this.props.articulo.all.map((item) => {
      return (
        <tr key={item.id}>
		<td>{item.precio}</td>	
		<td>{item.descripcion}</td>	
		<td>{item.categoria}</td>	
		<td>{item.cantidad}</td>	
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
    if ((this.props.articulo || {}).loading) {
      return (
        <Loading />
      );
    }

    if ((this.props.articulo || {}).error) {
      return (
        <SweetAlert
          type="error"
          title={this.props.articulo.error.message}
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
				<th>Precio</th>
				<th>Descripcion</th>
				<th>Categoria</th>
				<th>Cantidad</th>
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

ArticuloList.propTypes = {
  parentId: PropTypes.string,
  articulo: PropTypes.object.isRequired,
  searchTerm: PropTypes.string
};

function mapStateToProps(state) {
  return {
    articulo: state.articulo
  };
}

export default connect(mapStateToProps)(ArticuloList);

