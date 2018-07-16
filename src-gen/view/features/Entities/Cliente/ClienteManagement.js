import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router';
import { Col, Row, Panel, Pagination } from 'react-bootstrap';

import ContentWrapper from "../../Common/Layout/ContentWrapper";
import ClienteSearch from './ClienteSearch';
import ClienteList from './ClienteList';
import { fetchClienteList, fetchClienteListByParent, fetchParent} from './actions';

class ClienteManagement extends Component {

  constructor(props) {
    super(props);

    this.state = {
      current_page: 1,
      search_term: ""
    };
  }

  componentWillMount() {
    const parent_id = this.props.location.query.parent_id;
    if (parent_id) {
      this.props.fetchParent(parent_id);
    }
    this.getClienteList(this.state.current_page, parent_id);
  }

  getClienteList(page, parent_id, search_term) {
    if (parent_id) {
      this.props.fetchClienteListByParent(page, parent_id, search_term);
    } else {
      this.props.fetchClienteList(page, search_term);
    }
  }

  renderPagination() {
    const pages = Math.ceil((this.props.cliente || {}).items_count / this.props.items_per_page);
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
    const parent_id = this.props.location.query.parent_id;
    if (parent_id) {
      return (
        <Link className="btn btn-info" to={`/cliente?parent_id=${parent_id}`}>
          <em className="fa fa-plus" />
        </Link>
      );
    } else {
      return (
        <Link className="btn btn-info" to={`/cliente`}>
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
    const parent_id = this.props.location.query.parent_id;
    this.getClienteList(page, parent_id, form.term);
  }

  onPaginationClick(page) {
    this.setState({
      current_page: page
    });
    const parent_id = this.props.location.query.parent_id;
    this.getClienteList(page, parent_id, this.state.search_term);
  }

  render() {
    const parent_id = this.props.location.query.parent_id;
    return (
      <ContentWrapper>
        <ol className="breadcrumb pull-right">
           <li>
             <Link to="/">Inicio</Link>
           </li>
           <li className="active">Parent</li>
           <li>
             <Link to="/parent_mgmnt">Administrar Parent</Link>
           </li>
           <li className="active">Administrar Cliente</li>
        </ol>
        <h3>Administración de Cliente <small>{(this.props.parent || {}).nombre}</small></h3>
        <Panel header="Cliente registrados">
          <Col sm={12}>
            <Row>
              <ClienteSearch
                parentId={parent_id}
                onSearchSubmit={this.onSearchSubmit.bind(this)} />
              <br />
            </Row>
            <Row>
              {this.renderAddItem()}
            </Row>
            <Row>
              <ClienteList
                parentId={parent_id} />
            </Row>
            {this.renderPagination()}
          </Col>
        </Panel>
      </ContentWrapper>
    );
  }
}

ClienteManagement.defaultProps = {
  items_per_page: 10,
  maxButtons: 10
};

ClienteManagement.propTypes = {
  location: PropTypes.object,
  parent: PropTypes.object,
  cliente: PropTypes.object,
  fetchParent: PropTypes.func.isRequired,
  fetchClienteList: PropTypes.func,
  fetchClienteListByParent: PropTypes.func.isRequired,
  items_per_page: PropTypes.number.isRequired,
  maxButtons: PropTypes.number.isRequired
};

function mapStateToProps(state) {
  return {
	
    cliente: state.cliente
  };
}

export default connect( mapStateToProps,
                        { fetchParent, fetchClienteList, fetchClienteListByParent }
                      )(ClienteManagement);
