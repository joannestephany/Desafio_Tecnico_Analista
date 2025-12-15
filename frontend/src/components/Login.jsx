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
                        <h1>Gestão de Férias</h1>
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
