import React, { createContext, useState, useContext, useEffect } from 'react';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {

    const initialAuth = JSON.parse(localStorage.getItem('auth')) || { isAuthenticated: false, username: '', password: '' };

    const [auth, setAuth] = useState(initialAuth);


    useEffect(() => {
        localStorage.setItem('auth', JSON.stringify(auth));
    }, [auth]);

    const login = (username, password) => {

        setAuth({ isAuthenticated: true, username, password });
    };

    const logout = () => {
        setAuth({ isAuthenticated: false, username: '', password: '' });
    };

    const getAuthHeader = () => {
        if (!auth.isAuthenticated) return {};
        const base64Credentials = btoa(`${auth.username}:${auth.password}`);
        return {
            Authorization: `Basic ${base64Credentials}`
        };
    };

    return (
        <AuthContext.Provider value={{ auth, login, logout, getAuthHeader }}>
            {children}
        </AuthContext.Provider>
    );
};