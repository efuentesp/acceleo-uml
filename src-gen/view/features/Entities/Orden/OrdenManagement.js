import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import { Col, Row, Panel, Pagination } from 'react-bootstrap';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import OrdenSearch from './OrdenSearch';
import OrdenList from './OrdenList';
import { fetchCliente } from '../Cliente/actions';
import { fetchOrdenList, fetchOrdenListByCliente } from './actions';

class OrdenManagement extends Component {

  constructor(props) {
    super(props);

    this.state = {
      current_page: 1,
      search_term: ""
    };
  }

  componentWillMount() {
    const cliente_id = this.props.location.query.cliente_id;
    if (cliente_id) {
      this.props.fetchCliente(cliente_id);
    }
    this.getOrdenList(this.state.current_page, cliente_id);
  }

  getOrdenList(page, cliente_id, search_term) {
    if (cliente_id) {
      this.props.fetchOrdenListByCliente(page, cliente_id, search_term);
    } else {
      this.props.fetchOrdenList(page, search_term);
    }
  }

  renderPagination() {
    const pages = Math.ceil((this.props.orden || {}).items_count / this.props.items_per_page);
    if (pages > 1) {
      return(
        <Row>
          <Pagination
            activePage={this.state.current_page}
            items={pages}
            maxButtons={this.props.maxButtons}
            boundaryLinks
            onSelect={this.onPaginationClick.bind(this)} />
        </Row>
      );
    }
  }

  renderAddItem() {
    const cliente_id = this.props.location.query.cliente_id;
    if (cliente_id) {
      return (
        <Link className="btn btn-info" to={`/orden?cliente_id=${cliente_id}`}>
          <em className="fa fa-plus" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-info" to={`/orden`}>
          <em className="fa fa-plus" />
        </Link>
      );
    }
  }

  onSearchSubmit(form) {
    const page = 1;
    this.setState({
      search_term: form.term,
      current_page: page
    });
    const cliente_id = this.props.location.query.cliente_id;
    this.getOrdenList(page, cliente_id, form.term);
  }

  onPaginationClick(page) {
    this.setState({
      current_page: page
    });
    const cliente_id = this.props.location.query.cliente_id;
    this.getOrdenList(page, cliente_id, this.state.search_term);
  }

  render() {
    const cliente_id = this.props.location.query.cliente_id;
    return (
      <ContentWrapper>
        <ol className="breadcrumb pull-right">
           <li>
             <Link to="/">Inicio</Link>
           </li>
           <li className="active">Cliente</li>
           <li>
             <Link to="/cliente_mgmnt">Administrar Cliente</Link>
           </li>
           <li className="active">Administrar Orden</li>
        </ol>
        <h3>Administración de Orden <small>{(this.props.cliente || {}).nombre}</small></h3>
        <Panel header="Orden registrados">
          <Col sm={12}>
            <Row>
              <OrdenSearch
                clienteId={cliente_id}
                onSearchSubmit={this.onSearchSubmit.bind(this)} />
              <br />
            </Row>
            <Row>
              {this.renderAddItem()}
            </Row>
            <Row>
              <OrdenList
                clienteId={cliente_id} />
            </Row>
            {this.renderPagination()}
          </Col>
        </Panel>
      </ContentWrapper>
    );
  }
}

OrdenManagement.defaultProps = {
  items_per_page: 10,
  maxButtons: 10
};

OrdenManagement.propTypes = {
  location: PropTypes.object,
  cliente: PropTypes.object,
  orden: PropTypes.object,
  fetchCliente: PropTypes.func.isRequired,
  fetchOrdenList: PropTypes.func,
  fetchOrdenListByCliente: PropTypes.func.isRequired,
  items_per_page: PropTypes.number.isRequired,
  maxButtons: PropTypes.number.isRequired
};

function mapStateToProps(state) {
  return {
	cliente: state.cliente.item,
    orden: state.orden
  };
}

export default connect( mapStateToProps,
                        { fetchCliente, fetchOrdenList, fetchOrdenListByCliente }
                      )(OrdenManagement);
