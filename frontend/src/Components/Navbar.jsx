import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './Navbar.css';

const Navbar = () => {
    const { auth, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <nav className="navbar">
            <div className="navbar-left">
                <Link to="/usuarios">Usuarios</Link>
                <Link to="/votaciones">Votaciones</Link>
            </div>

            <div className="navbar-right">
                {auth.isAuthenticated ? (
                    <>
                        <span className="username-display">Hola, {auth.username}</span>
                        <button onClick={handleLogout} className="logout-button">
                            Cerrar Sesión
                        </button>
                    </>
                ) : (
                    <Link to="/login">Iniciar Sesión</Link>
                )}
            </div>
        </nav>
    );
};

export default Navbar;