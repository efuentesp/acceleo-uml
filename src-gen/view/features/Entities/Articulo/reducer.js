
import {
  FETCH_ARTICULO_LIST_REQUEST,
  FETCH_ARTICULO_LIST_SUCCESS,
  FETCH_ARTICULO_LIST_FAILURE,
  FETCH_ARTICULO_REQUEST,
  FETCH_ARTICULO_SUCCESS,
  FETCH_ARTICULO_FAILURE,
} from './actions';

const INITIAL_STATE = { all: [], item: null, loading: false, error:null, items_count: 0 };

export default function (state = INITIAL_STATE, action) {
  let error;

  switch (action.type) {
    case FETCH_ARTICULO_LIST_REQUEST:
      return { ...state, all: [], loading: true, error: null };

    case FETCH_ARTICULO_LIST_SUCCESS:
      return { ...state,
        all: action.payload.data,
        loading: false,
        error: null,
        items_count: (Number.parseInt(action.payload.headers['x-total-count']) || 0) };

    case FETCH_ARTICULO_LIST_FAILURE:
      error = action.payload.data || { message: action.payload.message };
      return { ...state, all: [], loading: false, error: error };

    case FETCH_ARTICULO_REQUEST:
      return { ...state, item: null, loading: true, error: null };

    case FETCH_ARTICULO_SUCCESS:
      return { ...state, item: action.payload.data, loading: false, error: null };

    case FETCH_ARTICULO_FAILURE:
      error = action.payload.data || { message: action.payload.message };
      return { ...state, item: null, loading: false, error: error };

  }

  return state;
}
