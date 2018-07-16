
import React, { Component, PropTypes } from 'react';
import ReactCSSTransitionGroup from 'react-addons-css-transition-group';

import Header from '../Common/Layout/Header';
import Sidebar from '../Common/Layout/Sidebar';
import SidebarSection from '../Common/Layout/SidebarSection';
import SidebarItem from '../Common/Layout/SidebarItem';
import SidebarMenu from '../Common/Layout/SidebarMenu';
import SidebarSubMenu from '../Common/Layout/SidebarSubMenu';
import Footer from '../Common/Layout/Footer';

class App extends Component {
  render() {
    const animationName = 'rag-fadeIn';

    return(
      <div className="wrapper">
        <Header />

        <Sidebar
          avatar="efp.jpg"
          user_name="Edgar Felipe Fuentes Perea (EFP)"
          user_role="Especialista de Práctica" >
          <SidebarSection
            title="Principal" />
            <SidebarItem
              link="/"
              title="Inicio"
              icon="fa fa-dashboard" />
              
              
              <SidebarMenu
                            title="Cliente"
                            icon="fa fa-briefcase">
                            <SidebarSubMenu
                              route="/cliente_mgmnt"
                              title="Administrar Cliente" />
                            <SidebarSubMenu
                              route="/cliente"
                              title="Agregar Cliente" />
                          </SidebarMenu>
              






<SidebarMenu
              title="Articulo"
              icon="fa fa-briefcase">
              <SidebarSubMenu
                route="/articulo_mgmnt"
                title="Administrar Articulo" />
              <SidebarSubMenu
                route="/articulo"
                title="Agregar Articulo" />
            </SidebarMenu>


<SidebarMenu
              title="Agencia"
              icon="fa fa-briefcase">
              <SidebarSubMenu
                route="/agencia_mgmnt"
                title="Administrar Agencia" />
              <SidebarSubMenu
                route="/agencia"
                title="Agregar Agencia" />
            </SidebarMenu>

            <SidebarSection
              title="Configuración" />
              <SidebarMenu
                title="Seguridad"
                icon="fa fa-lock">
                <SidebarSubMenu
                  route="/roles"
                  title="Administrar Roles" />
              </SidebarMenu>
        </Sidebar>

          <ReactCSSTransitionGroup
            component="section"
            transitionName={animationName}
            transitionEnterTimeout={500}
            transitionLeaveTimeout={500}>
            {this.props.children}
          </ReactCSSTransitionGroup>

          <Footer
            company_name="Softtek"
            initial_year="2017" />
      </div>
    );
  }
}

App.propTypes = {
  children: PropTypes.object.isRequired
};

export default App;
