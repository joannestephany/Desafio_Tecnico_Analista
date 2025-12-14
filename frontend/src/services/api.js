const API_BASE_URL = '/api';

async function request(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;
    const defaultOptions = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const config = { ...defaultOptions, ...options };

    const response = await fetch(url, config);

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
    }

    if (response.status === 204) {
        return null;
    }

    return await response.json();
}

// Servidores
export const servidoresApi = {
    listarTodos: () => request('/servidores'),
    buscarPorId: (id) => request(`/servidores/${id}`),
    buscarPorMatricula: (matricula) => request(`/servidores/matricula/${matricula}`),
    listarFerias: (servidorId) => request(`/servidores/${servidorId}/ferias`),
};

// Ferias
export const feriasApi = {
    listarTodas: () => request('/ferias'),
    buscarPorId: (id) => request(`/ferias/${id}`),
    buscarPorStatus: (status) => request(`/ferias?status=${encodeURIComponent(status)}`),
    criar: (ferias) => request('/ferias', {
        method: 'POST',
        body: JSON.stringify(ferias),
    }),
    atualizar: (id, ferias) => request(`/ferias/${id}`, {
        method: 'PUT',
        body: JSON.stringify(ferias),
    }),
    deletar: (id) => request(`/ferias/${id}`, {
        method: 'DELETE',
    }),
};

// Status
export const statusApi = {
    listarTodos: () => request('/status'),
    buscarPorId: (id) => request(`/status/${id}`),
};

// Pagamentos
export const pagamentosApi = {
    listarTodos: () => request('/pagamentos'),
    buscarPorId: (id) => request(`/pagamentos/${id}`),
    buscarPorPeriodo: (periodoId) => request(`/pagamentos/periodo/${periodoId}`),
};
