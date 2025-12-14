import { useState } from 'react';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import { servidoresApi } from './services/api';

function App() {
    const [servidor, setServidor] = useState(null);

    const handleLogin = async (matricula) => {
        const data = await servidoresApi.buscarPorMatricula(matricula);
        setServidor(data);
    };

    const handleLogout = () => {
        setServidor(null);
    };

    if (!servidor) {
        return <Login onLogin={handleLogin} />;
    }

    return <Dashboard servidor={servidor} onLogout={handleLogout} />;
}

export default App;
