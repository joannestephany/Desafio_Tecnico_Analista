import { useState, useEffect } from 'react';
import { servidoresApi, statusApi, feriasApi } from '../services/api';
import VacationTable from './VacationTable';
import DetailsModal from './DetailsModal';
import NewVacationModal from './NewVacationModal';
import './Dashboard.css';

function Dashboard({ servidor, onLogout }) {
    const [ferias, setFerias] = useState([]);
    const [statusList, setStatusList] = useState([]);
    const [statusFilter, setStatusFilter] = useState('');
    const [selectedFerias, setSelectedFerias] = useState(null);
    const [showNewModal, setShowNewModal] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadData();
    }, [servidor.id]);

    const loadData = async () => {
        try {
            const [feriasData, statusData] = await Promise.all([
                servidoresApi.listarFerias(servidor.id),
                statusApi.listarTodos()
            ]);
            setFerias(feriasData);
            setStatusList(statusData);
        } catch (error) {
            console.error('Error loading data:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleFilterChange = async (status) => {
        setStatusFilter(status);
        setLoading(true);
        try {
            if (status) {
                const filtered = await feriasApi.buscarPorStatus(status);
                setFerias(filtered);
            } else {
                const all = await servidoresApi.listarFerias(servidor.id);
                setFerias(all);
            }
        } catch (error) {
            setFerias([]);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        if (!window.confirm('Tem certeza que deseja remover este período de férias?')) return;
        try {
            await feriasApi.deletar(id);
            loadData();
        } catch (error) {
            alert('Erro ao remover férias.');
        }
    };

    const handleCreate = async (data) => {
        try {
            await feriasApi.criar({
                servidor: { id: servidor.id },
                status: { id: parseInt(data.statusId) },
                dataInicio: data.dataInicio,
                dataFim: data.dataFim,
            });
            setShowNewModal(false);
            loadData();
        } catch (error) {
            alert('Erro ao criar solicitação.');
        }
    };

    const stats = {
        total: ferias.length,
        pending: ferias.filter(f => f.status?.descricao?.toLowerCase() === 'solicitado').length,
        approved: ferias.filter(f => ['aprovado', 'concluído', 'concluido'].includes(f.status?.descricao?.toLowerCase())).length,
    };

    return (
        <div className="dashboard-screen">
            <nav className="navbar">
                <div className="navbar-user">
                    <button className="btn btn-ghost" onClick={onLogout}>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                            <path d="M17 7l-1.41 1.41L18.17 11H8v2h10.17l-2.58 2.58L17 17l5-5zM4 5h8V3H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h8v-2H4V5z" />
                        </svg>
                        Sair
                    </button>
                </div>
            </nav>

            <main className="main-content">
                <header className="dashboard-header">
                    <div className="welcome-section">
                        <h1>Bem-vindo, {servidor.nome}!</h1>
                    </div>
                    <div className="header-actions">
                        <button className="btn btn-primary" onClick={() => setShowNewModal(true)}>
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                                <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z" />
                            </svg>
                            Nova Solicitação
                        </button>
                    </div>
                </header>

                <section className="stats-grid">
                    <div className="stat-card glass-card">
                        <div className="stat-info">
                            <span className="stat-value">{stats.total}</span>
                            <span className="stat-label">Total de Períodos</span>
                        </div>
                    </div>
                    <div className="stat-card glass-card">
                        <div className="stat-info">
                            <span className="stat-value">{stats.pending}</span>
                            <span className="stat-label">Aguardando Aprovação</span>
                        </div>
                    </div>
                    <div className="stat-card glass-card">
                        <div className="stat-info">
                            <span className="stat-value">{stats.approved}</span>
                            <span className="stat-label">Aprovadas</span>
                        </div>
                    </div>
                </section>

                <section className="table-section">
                    <div className="section-header">
                        <div className="filter-group">
                            <label htmlFor="status-filter">Filtrar por status:</label>
                            <select
                                id="status-filter"
                                className="select-input"
                                value={statusFilter}
                                onChange={(e) => handleFilterChange(e.target.value)}
                            >
                                <option value="">Todos</option>
                                <option value="Solicitado">Solicitado</option>
                                <option value="Aprovado">Aprovado</option>
                                <option value="Concluído">Concluído</option>
                            </select>
                        </div>
                    </div>
                    <VacationTable
                        ferias={ferias}
                        loading={loading}
                        onView={(f) => setSelectedFerias(f)}
                        onDelete={handleDelete}
                    />
                </section>
            </main>

            {selectedFerias && (
                <DetailsModal
                    feriasId={selectedFerias.id}
                    onClose={() => setSelectedFerias(null)}
                    onStatusChange={loadData}
                />
            )}

            {showNewModal && (
                <NewVacationModal
                    statusList={statusList}
                    onClose={() => setShowNewModal(false)}
                    onSubmit={handleCreate}
                />
            )}
        </div>
    );
}

export default Dashboard;
