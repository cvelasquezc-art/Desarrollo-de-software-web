import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './AuthContext';
import LoginPage from './pages/LoginPage';
import UserListPage from './pages/UserListPage';
import VotationListPage from './pages/VotationListPage';

const ProtectedRoute = ({ children }) => {
    const { auth } = useAuth();
    if (!auth.isAuthenticated) {
        return <Navigate to="/login" replace />;
    }
    return children;
};

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>

                    <Route path="/login" element={<LoginPage />} />

                    <Route path="/usuarios" element={
                        <ProtectedRoute>
                            <UserListPage />
                        </ProtectedRoute>
                    } />
                    <Route path="/votaciones" element={
                        <ProtectedRoute>

                            <VotationListPage />
                        </ProtectedRoute>
                    } />

                    <Route path="/" element={<Navigate to="/usuarios" replace />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

export default App;