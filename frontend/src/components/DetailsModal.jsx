import { useState, useEffect } from 'react';
import { feriasApi } from '../services/api';
import './Modal.css';

function DetailsModal({ feriasId, onClose, onStatusChange }) {
    const [details, setDetails] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadDetails();
    }, [feriasId]);

    const loadDetails = async () => {
        try {
            const data = await feriasApi.buscarPorId(feriasId);
            setDetails(data);
        } catch (error) {
            console.error('Error loading details:', error);
        } finally {
            setLoading(false);
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return '-';
        const date = new Date(dateString + 'T00:00:00');
        return date.toLocaleDateString('pt-BR');
    };

    const formatCurrency = (value) => {
        if (value === null || value === undefined) return 'R$ 0,00';
        return new Intl.NumberFormat('pt-BR', {
            style: 'currency',
            currency: 'BRL',
        }).format(value);
    };

    const calculateDays = (start, end) => {
        const startDate = new Date(start);
        const endDate = new Date(end);
        const diffTime = Math.abs(endDate - startDate);
        return Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    };

    const handleStatusChange = async (newStatusId) => {
        try {
            await feriasApi.atualizar(feriasId, {
                ...details,
                servidor: { id: details.servidor?.id },
                status: { id: newStatusId }
            });
            onStatusChange();
            onClose();
        } catch (error) {
            alert('Erro ao atualizar status.');
        }
    };

    const getStatusClass = (status) => {
        const desc = status?.descricao?.toLowerCase() || '';
        if (desc === 'solicitado') return 'status-solicitado';
        if (desc === 'aprovado') return 'status-aprovado';
        if (desc === 'concluído' || desc === 'concluido') return 'status-concluido';
        if (desc === 'negado') return 'status-negado';
        return '';
    };

    return (
        <div className="modal active">
            <div className="modal-overlay" onClick={onClose}></div>
            <div className="modal-content glass-card">
                <div className="modal-header">
                    <h2>Detalhes do Período</h2>
                    <button className="modal-close" onClick={onClose}>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                            <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
                        </svg>
                    </button>
                </div>
                <div className="modal-body">
                    {loading ? (
                        <p className="loading-text">Carregando...</p>
                    ) : details ? (
                        <div className="details-grid">
                            <div className="details-section">
                                <h3>Período de Férias</h3>
                                <div className="detail-row">
                                    <span className="detail-label">ID</span>
                                    <span className="detail-value">#{details.id}</span>
                                </div>
                                <div className="detail-row">
                                    <span className="detail-label">Data de Início</span>
                                    <span className="detail-value">{formatDate(details.dataInicio)}</span>
                                </div>
                                <div className="detail-row">
                                    <span className="detail-label">Data de Término</span>
                                    <span className="detail-value">{formatDate(details.dataFim)}</span>
                                </div>
                                <div className="detail-row">
                                    <span className="detail-label">Duração</span>
                                    <span className="detail-value">{calculateDays(details.dataInicio, details.dataFim)} dias</span>
                                </div>
                                <div className="detail-row">
                                    <span className="detail-label">Status</span>
                                    <span className={`status-badge ${getStatusClass(details.status)}`}>
                                        {details.status?.descricao || '-'}
                                    </span>
                                </div>
                            </div>

                            {(details.valorBruto || details.valorLiquido) && (
                                <div className="details-section">
                                    <h3>Informações de Pagamento</h3>
                                    <div className="detail-row">
                                        <span className="detail-label">Valor Bruto</span>
                                        <span className="detail-value">{formatCurrency(details.valorBruto)}</span>
                                    </div>
                                    <div className="detail-row">
                                        <span className="detail-label">Valor Líquido</span>
                                        <span className="detail-value">{formatCurrency(details.valorLiquido)}</span>
                                    </div>
                                    <div className="detail-row">
                                        <span className="detail-label">Data do Pagamento</span>
                                        <span className="detail-value">{formatDate(details.dataPagamento)}</span>
                                    </div>
                                </div>
                            )}

                            {details.status?.descricao?.toLowerCase() === 'solicitado' && (
                                <div className="details-actions">
                                    <button className="btn btn-success" onClick={() => handleStatusChange(2)}>
                                        Aprovar
                                    </button>
                                    <button className="btn btn-danger" onClick={() => handleStatusChange(4)}>
                                        Negar
                                    </button>
                                </div>
                            )}

                            {details.status?.descricao?.toLowerCase() === 'aprovado' && (
                                <div className="details-actions">
                                    <button className="btn btn-primary" onClick={() => handleStatusChange(3)}>
                                        Concluir Férias
                                    </button>
                                </div>
                            )}
                        </div>
                    ) : (
                        <p>Erro ao carregar detalhes.</p>
                    )}
                </div>
            </div>
        </div>
    );
}

export default DetailsModal;
