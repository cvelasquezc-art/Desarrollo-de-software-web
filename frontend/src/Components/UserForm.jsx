import React, { useState, useEffect } from 'react';
import './UserForm.css';

const UserForm = ({ userToEdit, onSubmit, onCancel }) => {
    const [formData, setFormData] = useState({
        nombre: '',
        clave: '',
        rol: 'USER',
    });
    const [error, setError] = useState(null);

    useEffect(() => {
        if (userToEdit) {
            setFormData({
                nombre: userToEdit.nombre,
                clave: '',
                rol: userToEdit.rol,
            });
        }
    }, [userToEdit]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(null);

        if (!formData.nombre || (!userToEdit && !formData.clave) || !formData.rol) {
            setError('Todos los campos son obligatorios, especialmente la clave al crear.');
            return;
        }

        onSubmit({
            ...formData,
            id: userToEdit ? userToEdit.id : null
        });
    };

    return (
        <div className="modal-backdrop">
            <div className="modal-content">
                <h3>{userToEdit ? 'Editar Usuario' : 'Crear Nuevo Usuario'}</h3>
                {error && <p className="alert-message alert-error">{error}</p>}

                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Nombre:</label>
                        <input
                            type="text"
                            name="nombre"
                            value={formData.nombre}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Clave:</label>
                        <input
                            type="password"
                            name="clave"
                            value={formData.clave}
                            onChange={handleChange}
                            required={!userToEdit}
                            placeholder={userToEdit ? 'Dejar vacío para no cambiar la clave' : 'Requerido'}
                        />
                    </div>

                    <div className="form-group">
                        <label>Rol:</label>
                        <select
                            name="rol"
                            value={formData.rol}
                            onChange={handleChange}
                            required
                        >
                            <option value="USER">USER</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                    </div>

                    <div className="form-button-group">
                        <button type="submit" className="submit-button">
                            {userToEdit ? 'Guardar Cambios' : 'Crear'}
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

export default UserForm;