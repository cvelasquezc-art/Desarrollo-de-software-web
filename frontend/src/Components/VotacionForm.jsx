import React, { useState, useEffect } from 'react';
import './UserForm.css';

const VotacionForm = ({ votacionToEdit, onSubmit, onCancel }) => {
    const [formData, setFormData] = useState({
        fecha: '',
        partidoPolitico: '',
        candidato: '',
        votante: '',
        pais: '',
        departamento: '',
        ciudad: '',
        mesa: '',
        puestoPolitico: '',
        duracion: '',
        numeroTarjeton: 0,
    });
    const [error, setError] = useState(null);

    useEffect(() => {
        if (votacionToEdit) {
            setFormData({
                fecha: votacionToEdit.fecha || '',
                partidoPolitico: votacionToEdit.partidoPolitico || '',
                candidato: votacionToEdit.candidato || '',
                votante: votacionToEdit.votante || '',
                pais: votacionToEdit.pais || '',
                departamento: votacionToEdit.departamento || '',
                ciudad: votacionToEdit.ciudad || '',
                mesa: votacionToEdit.mesa || '',
                puestoPolitico: votacionToEdit.puestoPolitico || '',
                duracion: votacionToEdit.duracion || '',
                numeroTarjeton: votacionToEdit.numeroTarjeton || 0,
            });
        }
    }, [votacionToEdit]);

    const handleChange = (e) => {
        const { name, value, type } = e.target;
        setFormData({
            ...formData,
            [name]: type === 'number' ? parseInt(value) || 0 : value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(null);


        if (!formData.fecha || !formData.partidoPolitico || !formData.candidato || !formData.votante) {
            setError('La fecha, partido, candidato y votante son campos obligatorios.');
            return;
        }

        onSubmit({
            ...formData,
            id: votacionToEdit ? votacionToEdit.id : null
        });
    };

    return (
        <div className="modal-backdrop">
            <div className="modal-content" style={{ maxWidth: '700px' }}> {/* Ampliamos el modal */}
                <h3>{votacionToEdit ? 'Editar Registro de Votacion' : 'Crear Nuevo Registro de Votacion'}</h3>
                {error && <p className="alert-message alert-error">{error}</p>}

                <form onSubmit={handleSubmit} style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '20px' }}>

                    <div>
                        <div className="form-group">
                            <label>Fecha:</label>
                            <input type="text" name="fecha" value={formData.fecha} onChange={handleChange} required />
                        </div>
                        <div className="form-group">
                            <label>Partido Político:</label>
                            <input type="text" name="partidoPolitico" value={formData.partidoPolitico} onChange={handleChange} required />
                        </div>
                        <div className="form-group">
                            <label>Candidato:</label>
                            <input type="text" name="candidato" value={formData.candidato} onChange={handleChange} required />
                        </div>
                        <div className="form-group">
                            <label>Votante:</label>
                            <input type="text" name="votante" value={formData.votante} onChange={handleChange} required />
                        </div>
                        <div className="form-group">
                            <label>Número de Tarjetón:</label>
                            <input type="number" name="numeroTarjeton" value={formData.numeroTarjeton} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label>Duración:</label>
                            <input type="text" name="duracion" value={formData.duracion} onChange={handleChange} />
                        </div>
                    </div>

                    <div>
                        <div className="form-group">
                            <label>País:</label>
                            <input type="text" name="pais" value={formData.pais} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label>Departamento:</label>
                            <input type="text" name="departamento" value={formData.departamento} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label>Ciudad:</label>
                            <input type="text" name="ciudad" value={formData.ciudad} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label>Mesa de Votación:</label>
                            <input type="text" name="mesa" value={formData.mesa} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label>Puesto Político:</label>
                            <input type="text" name="puestoPolitico" value={formData.puestoPolitico} onChange={handleChange} />
                        </div>
                    </div>

                    <div style={{ gridColumn: '1 / 3' }} className="form-button-group">
                        <button type="submit" className="submit-button">
                            {votacionToEdit ? 'Guardar Cambios' : 'Registrar Votación'}
                        </button>
                        <button type="button" onClick={onCancel} className="cancel-button">
                            Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default VotacionForm;