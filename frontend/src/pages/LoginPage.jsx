import React, { useState } from 'react';
import { useAuth } from '../AuthContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './LoginPage.css';

const API_PROTECTED_URL = 'http://localhost:8080/api/usuarios';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const [isLoggingIn, setIsLoggingIn] = useState(false);
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setIsLoggingIn(true);

        try {
            const response = await axios.get(API_PROTECTED_URL, {
                auth: {
                    username: username,
                    password: password
                }
            });

            if (response.status === 200) {
                login(username, password);
                navigate('/usuarios');
            }

        } catch (err) {
            if (err.response && err.response.status === 401) {
                setError('Credenciales inválidas. Usuario o contraseña incorrectos.');
            } else {
                setError(`Error al conectar con el servidor. ¿API corriendo? (${err.message})`);
            }
        } finally {
            setIsLoggingIn(false);
        }
    };

    return (
        <div className="login-container">
            <div className="login-box">
                <h2>Iniciar Sesión</h2>
                <form onSubmit={handleSubmit} className="login-form">
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="Usuario"
                        required
                        disabled={isLoggingIn}
                    />
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Contraseña"
                        required
                        disabled={isLoggingIn}
                    />
                    <button
                        type="submit"
                        className="login-button"
                        disabled={isLoggingIn}
                    >
                        {isLoggingIn ? 'Verificando...' : 'Entrar'}
                    </button>
                    {error && <p className="error-message">{error}</p>}
                </form>
            </div>
        </div>
    );
};

export default LoginPage;