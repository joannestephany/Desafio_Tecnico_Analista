import './VacationTable.css';

function VacationTable({ ferias, loading, onView, onDelete }) {
    const formatDate = (dateString) => {
        if (!dateString) return '-';
        const date = new Date(dateString + 'T00:00:00');
        return date.toLocaleDateString('pt-BR');
    };

    const calculateDays = (start, end) => {
        const startDate = new Date(start);
        const endDate = new Date(end);
        const diffTime = Math.abs(endDate - startDate);
        return Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    };

    const getStatusClass = (status) => {
        const desc = status?.descricao?.toLowerCase() || '';
        if (desc === 'solicitado') return 'status-solicitado';
        if (desc === 'aprovado') return 'status-aprovado';
        if (desc === 'concluído' || desc === 'concluido') return 'status-concluido';
        if (desc === 'negado') return 'status-negado';
        return '';
    };

    if (loading) {
        return (
            <div className="table-container glass-card">
                <p className="loading-text">Carregando...</p>
            </div>
        );
    }

    if (ferias.length === 0) {
        return (
            <div className="table-container glass-card">
                <div className="empty-state">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M19 3h-1V1h-2v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V8h14v11z" />
                    </svg>
                    <p>Nenhum período de férias encontrado</p>
                </div>
            </div>
        );
    }

    return (
        <div className="table-container glass-card">
            <table className="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Data Início</th>
                        <th>Data Fim</th>
                        <th>Dias</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {ferias.map((f) => (
                        <tr key={f.id}>
                            <td>#{f.id}</td>
                            <td>{formatDate(f.dataInicio)}</td>
                            <td>{formatDate(f.dataFim)}</td>
                            <td>{calculateDays(f.dataInicio, f.dataFim)} dias</td>
                            <td>
                                <span className={`status-badge ${getStatusClass(f.status)}`}>
                                    {f.status?.descricao || '-'}
                                </span>
                            </td>
                            <td>
                                <div className="action-buttons">
                                    <button className="action-btn" onClick={() => onView(f)} title="Ver detalhes">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                                            <path d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5z" />
                                        </svg>
                                    </button>
                                    <button className="action-btn" onClick={() => onDelete(f.id)} title="Remover">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                                            <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z" />
                                        </svg>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default VacationTable;
