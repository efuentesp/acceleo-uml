import axios from 'axios';
import {browserHistory } from 'react-router';
import { toastr } from 'react-redux-toastr';

export const FETCH_ARTICULO_LIST_REQUEST = 'FETCH_ARTICULO_LIST_REQUEST';
export const FETCH_ARTICULO_LIST_SUCCESS = 'FETCH_ARTICULO_LIST_SUCCESS';
export const FETCH_ARTICULO_LIST_FAILURE = 'FETCH_ARTICULO_LIST_FAILURE';
export const FETCH_ARTICULO_REQUEST = 'FETCH_ARTICULO_REQUEST';
export const FETCH_ARTICULO_SUCCESS = 'FETCH_ARTICULO_SUCCESS';
export const FETCH_ARTICULO_FAILURE = 'FETCH_ARTICULO_FAILURE';
export const CREATE_ARTICULO_REQUEST = 'CREATE_ARTICULO_REQUEST';
export const CREATE_ARTICULO_SUCCESS = 'CREATE_ARTICULO_SUCCESS';
export const CREATE_ARTICULO_FAILURE = 'CREATE_ARTICULO_FAILURE';
export const UPDATE_ARTICULO_REQUEST = 'UPDATE_ARTICULO_REQUEST';
export const UPDATE_ARTICULO_SUCCESS = 'UPDATE_ARTICULO_SUCCESS';
export const UPDATE_ARTICULO_FAILURE = 'UPDATE_ARTICULO_FAILURE';

const ROOT_URL = 'http://localhost:8080/SADF';

/**
 ** Fetch Articulo List actions
 **/

export function fetchArticuloListRequest() {
  return { type: FETCH_ARTICULO_LIST_REQUEST };
}

export function fetchArticuloListSuccess(payload) {
  return { type: FETCH_ARTICULO_LIST_SUCCESS, payload };
}

export function fetchArticuloListFailure(error) {
  return { type: FETCH_ARTICULO_LIST_FAILURE, payload: error };
}

export function fetchArticuloList(page, term) {

  let url = `${ROOT_URL}/articulo?_page=${page}`;
  if (term) {
    url = `${url}&q=${term}`;
  }

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchArticuloListRequest());
    return request
      .then(res => dispatch(fetchArticuloListSuccess(res)))
      .catch(ex => dispatch(fetchArticuloListFailure(ex)));
  };
}

/**
 ** Fetch Cliente actions
 **/

export function fetchArticuloRequest() {
  return { type: FETCH_ARTICULO_REQUEST };
}

export function fetchArticuloSuccess(payload) {
  return { type: FETCH_ARTICULO_SUCCESS, payload };
}

export function fetchArticuloFailure(error) {
  return { type: FETCH_ARTICULO_FAILURE, payload: error };
}

export function fetchArticulo(id) {

  const url = `${ROOT_URL}/articulo/${id}`;

  const request = axios({
    method: 'get',
    url: url,
    headers: []
  });

  return dispatch => {
    dispatch(fetchArticuloRequest());
    return request
      .then(res => dispatch(fetchArticuloSuccess(res)))
      .catch(ex => dispatch(fetchArticuloFailure(ex)));
  };
}


/**
 ** Create Articulo actions
 **/

export function createArticuloRequest() {
  return { type: CREATE_ARTICULO_REQUEST };
}

export function createArticuloSuccess(payload) {
  return { type: CREATE_ARTICULO_SUCCESS, payload };
}

export function createArticuloFailure(error) {
  return { type: CREATE_ARTICULO_FAILURE, payload: error };
}

export function createArticulo(props) {

  const url = `${ROOT_URL}/articulo`;

  const request = axios({
    method: 'post',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(createArticuloRequest());
    return request
      .then(res => dispatch(createArticuloSuccess(res)))
      .catch(ex => dispatch(createArticuloFailure(ex)));
  };
}

/**
 ** Update Articulo actions
 **/

export function updateArticuloRequest() {
  return { type: UPDATE_ARTICULO_REQUEST };
}

export function updateArticuloSuccess(payload) {
  return { type: UPDATE_ARTICULO_SUCCESS, payload };
}

export function updateArticuloFailure(error) {
  return { type: UPDATE_ARTICULO_FAILURE, payload: error };
}

export function updateArticulo(id, props) {

  const url = `${ROOT_URL}/articulo/${id}`;

  const request = axios({
    method: 'put',
    url: url,
    data: props,
    headers: []
  });

  return dispatch => {
    dispatch(updateArticuloRequest());
    return request
      .then(res => dispatch(updateArticuloSuccess(res)))
      .catch(ex => dispatch(updateArticuloFailure(ex)));
  };
}

/**
 ** Delete Articulo actions
 **/

export function deleteArticulo(id, parent_id) {

  const url = `${ROOT_URL}/articulo/${id}`;

  const request = axios({
    method: 'delete',
    url: url,
    headers: []
  });

  return () => {
    request
      .then((res) => {
      if (res.status == 200){ 
        const articulo_mgmnt = (parent_id) ? "/articulo_mgmnt?parent_id=" + parent_id : "/articulo_mgmnt";
        browserHistory.push(articulo_mgmnt);
        toastr.success("Articulo borrada", `El Articulo fue borrado exitosamente.`);
      }	
      })
      .catch(() => {
        const toastrOptions = {
          timeOut: 8000
        };
        toastr.error("Error al borrar Articulo", toastrOptions);
      });
  };
}

export function fetchArticuloListByParent () {

}

export function fetchParent () {

}
