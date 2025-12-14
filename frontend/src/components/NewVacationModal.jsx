import { useState } from 'react';
import './Modal.css';

function NewVacationModal({ statusList, onClose, onSubmit }) {
    const [dataInicio, setDataInicio] = useState('');
    const [dataFim, setDataFim] = useState('');
    const [statusId, setStatusId] = useState(statusList[0]?.id || '');
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!dataInicio || !dataFim || !statusId) {
            alert('Preencha todos os campos.');
            return;
        }
        setLoading(true);
        try {
            await onSubmit({ dataInicio, dataFim, statusId });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="modal active">
            <div className="modal-overlay" onClick={onClose}></div>
            <div className="modal-content glass-card">
                <div className="modal-header">
                    <h2>Nova Solicitação de Férias</h2>
                    <button className="modal-close" onClick={onClose}>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                            <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
                        </svg>
                    </button>
                </div>
                <div className="modal-body">
                    <form onSubmit={handleSubmit}>
                        <div className="form-row">
                            <div className="form-group">
                                <label htmlFor="data-inicio">Data de Início</label>
                                <input
                                    type="date"
                                    id="data-inicio"
                                    value={dataInicio}
                                    onChange={(e) => setDataInicio(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="data-fim">Data de Término</label>
                                <input
                                    type="date"
                                    id="data-fim"
                                    value={dataFim}
                                    onChange={(e) => setDataFim(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="status-select">Status</label>
                            <select
                                id="status-select"
                                className="select-input"
                                value={statusId}
                                onChange={(e) => setStatusId(e.target.value)}
                                required
                            >
                                {statusList.map((s) => (
                                    <option key={s.id} value={s.id}>{s.descricao}</option>
                                ))}
                            </select>
                        </div>
                        <div className="form-actions">
                            <button type="button" className="btn btn-ghost" onClick={onClose}>
                                Cancelar
                            </button>
                            <button type="submit" className="btn btn-primary" disabled={loading}>
                                {loading ? 'Enviando...' : 'Solicitar'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default NewVacationModal;
