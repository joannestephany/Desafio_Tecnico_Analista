import { useState } from 'react';
import './Login.css';

function Login({ onLogin }) {
    const [matricula, setMatricula] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        if (!matricula.trim()) {
            setError('Por favor, informe sua matrícula.');
            return;
        }

        setLoading(true);
        try {
            await onLogin(matricula.trim());
        } catch (err) {
            setError('Matrícula não encontrada. Por favor, verifique e tente novamente.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-screen">
            <div className="login-container">
                <div className="login-card glass-card">
                    <div className="login-header">
                        <div className="logo-icon">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                                <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z" />
                            </svg>
                        </div>
                        <h1>Gestão de Férias</h1>
                        <p className="subtitle">Portal do Servidor</p>
                    </div>
                    <form className="login-form" onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="matricula">Matrícula</label>
                            <input
                                type="text"
                                id="matricula"
                                value={matricula}
                                onChange={(e) => setMatricula(e.target.value)}
                                placeholder="Digite sua matrícula"
                                disabled={loading}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary btn-full" disabled={loading}>
                            <span>{loading ? 'Entrando...' : 'Entrar'}</span>
                            {!loading && (
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="btn-icon">
                                    <path d="M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z" />
                                </svg>
                            )}
                        </button>
                    </form>
                    {error && <p className="error-message">{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default Login;
