import axios from 'axios';
import {browserHistory } from 'react-router';
import { toastr } from 'react-redux-toastr';

export const FETCH_CLIENTE_LIST_REQUEST = 'FETCH_CLIENTE_LIST_REQUEST';
export const FETCH_CLIENTE_LIST_SUCCESS = 'FETCH_CLIENTE_LIST_SUCCESS';
export const FETCH_CLIENTE_LIST_FAILURE = 'FETCH_CLIENTE_LIST_FAILURE';
export const FETCH_CLIENTE_REQUEST = 'FETCH_CLIENTE_REQUEST';
export const FETCH_CLIENTE_SUCCESS = 'FETCH_CLIENTE_SUCCESS';
export const FETCH_CLIENTE_FAILURE = 'FETCH_CLIENTE_FAILURE';
export const CREATE_CLIENTE_REQUEST = 'CREATE_CLIENTE_REQUEST';
export const CREATE_CLIENTE_SUCCESS = 'CREATE_CLIENTE_SUCCESS';
export const CREATE_CLIENTE_FAILURE = 'CREATE_CLIENTE_FAILURE';
export const UPDATE_CLIENTE_REQUEST = 'UPDATE_CLIENTE_REQUEST';
export const UPDATE_CLIENTE_SUCCESS = 'UPDATE_CLIENTE_SUCCESS';
export const UPDATE_CLIENTE_FAILURE = 'UPDATE_CLIENTE_FAILURE';

const ROOT_URL = 'http://localhost:8080/SADF';

/**
 ** Fetch Cliente List actions
 **/

export function fetchClienteListRequest() {
  return { type: FETCH_CLIENTE_LIST_REQUEST };
}

export function fetchClienteListSuccess(payload) {
  return { type: FETCH_CLIENTE_LIST_SUCCESS, payload };
}

export function fetchClienteListFailure(error) {
  return { type: FETCH_CLIENTE_LIST_FAILURE, payload: error };
}

export function fetchClienteList(page, term) {

  let url = `${ROOT_URL}/cliente?_page=${page}`;
  if (term) {
    url = `${url}&q=${term}`;
  }

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchClienteListRequest());
    return request
      .then(res => dispatch(fetchClienteListSuccess(res)))
      .catch(ex => dispatch(fetchClienteListFailure(ex)));
  };
}

/**
 ** Fetch Cliente actions
 **/

export function fetchClienteRequest() {
  return { type: FETCH_CLIENTE_REQUEST };
}

export function fetchClienteSuccess(payload) {
  return { type: FETCH_CLIENTE_SUCCESS, payload };
}

export function fetchClienteFailure(error) {
  return { type: FETCH_CLIENTE_FAILURE, payload: error };
}

export function fetchCliente(id) {

  const url = `${ROOT_URL}/cliente/${id}`;

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchClienteRequest());
    return request
      .then(res => dispatch(fetchClienteSuccess(res)))
      .catch(ex => dispatch(fetchClienteFailure(ex)));
  };
}




/**
 ** Create Cliente actions
 **/

export function createClienteRequest() {
  return { type: CREATE_CLIENTE_REQUEST };
}

export function createClienteSuccess(payload) {
  return { type: CREATE_CLIENTE_SUCCESS, payload };
}

export function createClienteFailure(error) {
  return { type: CREATE_CLIENTE_FAILURE, payload: error };
}

export function createCliente(props) {

  const url = `${ROOT_URL}/cliente`;

  const request = axios({
    method: 'post',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(createClienteRequest());
    return request
      .then(res => dispatch(createClienteSuccess(res)))
      .catch(ex => dispatch(createClienteFailure(ex)));
  };
}

/**
 ** Update Cliente actions
 **/

export function updateClienteRequest() {
  return { type: UPDATE_CLIENTE_REQUEST };
}

export function updateClienteSuccess(payload) {
  return { type: UPDATE_CLIENTE_SUCCESS, payload };
}

export function updateClienteFailure(error) {
  return { type: UPDATE_CLIENTE_FAILURE, payload: error };
}

export function updateCliente(id, props) {

  const url = `${ROOT_URL}/cliente/${id}`;

  const request = axios({
    method: 'put',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(updateClienteRequest());
    return request
      .then(res => dispatch(updateClienteSuccess(res)))
      .catch(ex => dispatch(updateClienteFailure(ex)));
  };
}

/**
 ** Delete Cliente actions
 **/

export function deleteCliente(id, parent_id) {

  const url = `${ROOT_URL}/cliente/${id}`;

  const request = axios({
    method: 'delete',
    url: url,
    headers: []
  });

  return () => {
    request
      .then((res) => {
      if (res.status == 200){ 
        const cliente_mgmnt = (parent_id) ? "/cliente_mgmnt?parent_id=" + parent_id : "/cliente_mgmnt";
        browserHistory.push(cliente_mgmnt);
        toastr.success("Cliente borrada", `El Cliente fue borrado exitosamente.`);
      }	
      })
      .catch(() => {
        const toastrOptions = {
          timeOut: 8000
        };
        toastr.error("Error al borrar Cliente", toastrOptions);
      });
  };
}

export function fetchClienteListByParent () {

}

export function fetchParent () {

}
