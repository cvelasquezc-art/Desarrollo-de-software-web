import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useAuth } from '../AuthContext';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import UserForm from '../components/UserForm';
import './UserListPage.css';


const API_URL = 'http://localhost:8080/api/usuarios';

const UserListPage = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);


    const [isModalOpen, setIsModalOpen] = useState(false);
    const [userToEdit, setUserToEdit] = useState(null);

    const { auth, getAuthHeader } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (!auth.isAuthenticated) {
            navigate('/login');
        }
    }, [auth.isAuthenticated, navigate]);

    useEffect(() => {
        if (auth.isAuthenticated) {
            fetchUsers();
        }
    }, [auth.isAuthenticated]);

    const fetchUsers = async () => {
        setLoading(true);
        try {
            const response = await axios.get(API_URL, {
                headers: getAuthHeader()
            });
            setUsers(response.data);
            setError(null);
        } catch (err) {
            console.error("Error al obtener usuarios:", err);
            setError(`Error al cargar datos: ${err.message}. ¿Backend corriendo?`);
            setUsers([]);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        if (!window.confirm(`¿Seguro que quieres eliminar al usuario con ID ${id}?`)) {
            return;
        }
        try {
            await axios.delete(`${API_URL}/${id}`, {
                headers: getAuthHeader()
            });
            fetchUsers();
        } catch (err) {
            console.error("Error al eliminar:", err);
            setError(`Error al eliminar usuario ${id}. ¡Revisa si tienes permisos ADMIN!`);
        }
    };

    const handleOpenCreate = () => {
        setUserToEdit(null);
        setIsModalOpen(true);
    };

    const handleOpenEdit = (user) => {
        setUserToEdit(user);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setUserToEdit(null);
        setError(null);
    };

    const handleFormSubmit = async (formData) => {
        setLoading(true);
        try {
            if (formData.id) {
                const updatedUser = { ...formData };
                await axios.put(`${API_URL}/${formData.id}`, updatedUser, {
                    headers: getAuthHeader()
                });
            } else {
                await axios.post(API_URL, formData, {
                    headers: getAuthHeader()
                });
            }

            handleCloseModal();
            fetchUsers();

        } catch (err) {
            console.error("Error al guardar:", err);
            const apiErrorMessage = err.response?.data?.message || err.message;
            setError(`Error al guardar: ${apiErrorMessage}.`);
        } finally {
            setLoading(false);
        }
    };

    if (loading) return <div>Cargando usuarios...</div>;

    return (
        <>
            <Navbar />
            <div className="page-container">
                <div className="page-header">
                    <h2>Lista de Usuarios</h2>
                    <button onClick={handleOpenCreate} className="create-button">
                        Crear Nuevo Usuario
                    </button>
                </div>

                {error && <div className="alert-message alert-error">🚨 {error}</div>}

                <table className="users-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Rol</th>
                        <th>Clave</th>
                        <th style={{minWidth: '160px'}}>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.length === 0 ? (
                        <tr><td colSpan="5" style={{textAlign: 'center'}}>No hay usuarios registrados.</td></tr>
                    ) : (
                        users.map(user => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.nombre}</td>
                                <td>{user.rol}</td>
                                <td>{user.clave}</td>
                                <td className="action-buttons">
                                    <button onClick={() => handleOpenEdit(user)} className="edit-button">
                                        Editar
                                    </button>
                                    <button onClick={() => handleDelete(user.id)} className="delete-button">
                                        Eliminar
                                    </button>
                                </td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>

            {isModalOpen && (
                <UserForm
                    userToEdit={userToEdit}
                    onSubmit={handleFormSubmit}
                    onCancel={handleCloseModal}
                />
            )}
        </>
    );
};


export default UserListPage;