import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useAuth } from '../AuthContext';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import VotacionForm from '../components/VotacionForm';
import './VotationListPage.css';

const API_URL = 'http://localhost:8080/api/votaciones';

const VotationListPage = () => {
    const [votaciones, setVotaciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [votacionToEdit, setVotacionToEdit] = useState(null);

    const { auth, getAuthHeader } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (!auth.isAuthenticated) navigate('/login');
    }, [auth.isAuthenticated, navigate]);

    useEffect(() => {
        if (auth.isAuthenticated) fetchVotaciones();
    }, [auth.isAuthenticated]);

    const fetchVotaciones = async () => {
        setLoading(true);
        try {
            const response = await axios.get(API_URL, { headers: getAuthHeader() });
            setVotaciones(response.data);
            setError(null);
        } catch (err) {
            setError(`Error al cargar datos. Asegúrate que la API esté disponible.`);
            setVotaciones([]);
        } finally {
            setLoading(false);
        }
    };

    const handleOpenCreate = () => {
        setVotacionToEdit(null);
        setIsModalOpen(true);
    };

    const handleOpenEdit = (votacion) => {
        setVotacionToEdit(votacion);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setVotacionToEdit(null);
        setError(null);
    };

    const handleFormSubmit = async (formData) => {
        setLoading(true);
        let payload = { ...formData };

        try {
            if (payload.id) {
                await axios.put(`${API_URL}/${payload.id}`, payload, { headers: getAuthHeader() });
            } else {
                delete payload.id;
                await axios.post(API_URL, payload, { headers: getAuthHeader() });
            }

            handleCloseModal();
            fetchVotaciones();
        } catch (err) {
            const apiErrorMessage = err.response?.data?.message || err.message;
            setError(`Error al guardar: ${apiErrorMessage}.`);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        if (!window.confirm(`¿Seguro que quieres eliminar el voto ID ${id}?`)) return;
        try {
            await axios.delete(`${API_URL}/${id}`, { headers: getAuthHeader() });
            fetchVotaciones();
        } catch (err) {
            setError(`Error al eliminar registro ${id}.`);
        }
    };

    if (loading) return <div className="loading-message">Cargando registros...</div>;

    return (
        <>
            <Navbar />

            <div className="page-container">
                <div className="page-header">
                    <h2>Registros de Votos</h2>
                    <button onClick={handleOpenCreate} className="create-button">
                         Crear Nuevo Registro
                    </button>
                </div>

                {error && (
                    <div className="alert-message alert-error">
                         {error}
                    </div>
                )}

                <div style={{ overflowX: 'auto' }}>
                    <table className="users-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Votante</th>
                            <th>Candidato</th>
                            <th>Partido</th>
                            <th>País</th>
                            <th>Depto</th>
                            <th>Ciudad</th>
                            <th>Puesto</th>
                            <th>Mesa</th>
                            <th>Tarjetón</th>
                            <th>Duración</th>
                            <th style={{ minWidth: '150px' }}>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        {votaciones.length === 0 ? (
                            <tr>
                                <td colSpan="13" style={{ textAlign: 'center' }}>
                                    No hay registros de votos.
                                </td>
                            </tr>
                        ) : (
                            votaciones.map(voto => (
                                <tr key={voto.id}>
                                    <td>{voto.id}</td>
                                    <td>{voto.fecha}</td>
                                    <td>{voto.votante}</td>
                                    <td>{voto.candidato}</td>
                                    <td>{voto.partidoPolitico}</td>
                                    <td>{voto.pais}</td>
                                    <td>{voto.departamento}</td>
                                    <td>{voto.ciudad}</td>
                                    <td>{voto.puestoPolitico}</td>
                                    <td>{voto.mesa}</td>
                                    <td>{voto.numeroTarjeton}</td>
                                    <td>{voto.duracion}</td>
                                    <td className="action-buttons">
                                        <button onClick={() => handleOpenEdit(voto)} className="edit-button">✏️</button>
                                        <button onClick={() => handleDelete(voto.id)} className="delete-button">🗑️</button>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>

                {isModalOpen && (
                    <VotacionForm
                        votacionToEdit={votacionToEdit}
                        onSubmit={handleFormSubmit}
                        onCancel={handleCloseModal}
                    />
                )}
            </div>
        </>
    );
};

export default VotationListPage;
