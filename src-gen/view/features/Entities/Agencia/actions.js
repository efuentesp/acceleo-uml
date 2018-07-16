import axios from 'axios';
import {browserHistory } from 'react-router';
import { toastr } from 'react-redux-toastr';

export const FETCH_AGENCIA_LIST_REQUEST = 'FETCH_AGENCIA_LIST_REQUEST';
export const FETCH_AGENCIA_LIST_SUCCESS = 'FETCH_AGENCIA_LIST_SUCCESS';
export const FETCH_AGENCIA_LIST_FAILURE = 'FETCH_AGENCIA_LIST_FAILURE';
export const FETCH_AGENCIA_REQUEST = 'FETCH_AGENCIA_REQUEST';
export const FETCH_AGENCIA_SUCCESS = 'FETCH_AGENCIA_SUCCESS';
export const FETCH_AGENCIA_FAILURE = 'FETCH_AGENCIA_FAILURE';
export const CREATE_AGENCIA_REQUEST = 'CREATE_AGENCIA_REQUEST';
export const CREATE_AGENCIA_SUCCESS = 'CREATE_AGENCIA_SUCCESS';
export const CREATE_AGENCIA_FAILURE = 'CREATE_AGENCIA_FAILURE';
export const UPDATE_AGENCIA_REQUEST = 'UPDATE_AGENCIA_REQUEST';
export const UPDATE_AGENCIA_SUCCESS = 'UPDATE_AGENCIA_SUCCESS';
export const UPDATE_AGENCIA_FAILURE = 'UPDATE_AGENCIA_FAILURE';

const ROOT_URL = 'http://localhost:8080/SADF';

/**
 ** Fetch Agencia List actions
 **/

export function fetchAgenciaListRequest() {
  return { type: FETCH_AGENCIA_LIST_REQUEST };
}

export function fetchAgenciaListSuccess(payload) {
  return { type: FETCH_AGENCIA_LIST_SUCCESS, payload };
}

export function fetchAgenciaListFailure(error) {
  return { type: FETCH_AGENCIA_LIST_FAILURE, payload: error };
}

export function fetchAgenciaList(page, term) {

  let url = `${ROOT_URL}/agencia?_page=${page}`;
  if (term) {
    url = `${url}&q=${term}`;
  }

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchAgenciaListRequest());
    return request
      .then(res => dispatch(fetchAgenciaListSuccess(res)))
      .catch(ex => dispatch(fetchAgenciaListFailure(ex)));
  };
}

/**
 ** Fetch Cliente actions
 **/

export function fetchAgenciaRequest() {
  return { type: FETCH_AGENCIA_REQUEST };
}

export function fetchAgenciaSuccess(payload) {
  return { type: FETCH_AGENCIA_SUCCESS, payload };
}

export function fetchAgenciaFailure(error) {
  return { type: FETCH_AGENCIA_FAILURE, payload: error };
}

export function fetchAgencia(id) {

  const url = `${ROOT_URL}/agencia/${id}`;

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchAgenciaRequest());
    return request
      .then(res => dispatch(fetchAgenciaSuccess(res)))
      .catch(ex => dispatch(fetchAgenciaFailure(ex)));
  };
}


/**
 ** Create Agencia actions
 **/

export function createAgenciaRequest() {
  return { type: CREATE_AGENCIA_REQUEST };
}

export function createAgenciaSuccess(payload) {
  return { type: CREATE_AGENCIA_SUCCESS, payload };
}

export function createAgenciaFailure(error) {
  return { type: CREATE_AGENCIA_FAILURE, payload: error };
}

export function createAgencia(props) {

  const url = `${ROOT_URL}/agencia`;

  const request = axios({
    method: 'post',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(createAgenciaRequest());
    return request
      .then(res => dispatch(createAgenciaSuccess(res)))
      .catch(ex => dispatch(createAgenciaFailure(ex)));
  };
}

/**
 ** Update Agencia actions
 **/

export function updateAgenciaRequest() {
  return { type: UPDATE_AGENCIA_REQUEST };
}

export function updateAgenciaSuccess(payload) {
  return { type: UPDATE_AGENCIA_SUCCESS, payload };
}

export function updateAgenciaFailure(error) {
  return { type: UPDATE_AGENCIA_FAILURE, payload: error };
}

export function updateAgencia(id, props) {

  const url = `${ROOT_URL}/agencia/${id}`;

  const request = axios({
    method: 'put',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(updateAgenciaRequest());
    return request
      .then(res => dispatch(updateAgenciaSuccess(res)))
      .catch(ex => dispatch(updateAgenciaFailure(ex)));
  };
}

/**
 ** Delete Agencia actions
 **/

export function deleteAgencia(id, parent_id) {

  const url = `${ROOT_URL}/agencia/${id}`;

  const request = axios({
    method: 'delete',
    url: url,
    headers: []
  });

  return () => {
    request
      .then((res) => {
      if (res.status == 200){ 
        const agencia_mgmnt = (parent_id) ? "/agencia_mgmnt?parent_id=" + parent_id : "/agencia_mgmnt";
        browserHistory.push(agencia_mgmnt);
        toastr.success("Agencia borrada", `El Agencia fue borrado exitosamente.`);
      }	
      })
      .catch(() => {
        const toastrOptions = {
          timeOut: 8000
        };
        toastr.error("Error al borrar Agencia", toastrOptions);
      });
  };
}

export function fetchAgenciaListByParent () {

}

export function fetchParent () {

}
