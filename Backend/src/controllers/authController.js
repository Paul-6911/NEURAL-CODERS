// backend/src/controllers/authController.js
const admin = require('../config/firebase');
const { sendConfirmationEmail } = require('../services/emailService');

const usersDb = {}; // Simulación de base de datos en memoria

const register = async (req, res) => {
    const { email, password } = req.body;
    
    try {
        const userRecord = await admin.auth().createUser({
            email,
            password,
        });
        
        usersDb[email] = { password, confirmed: false };
        res.status(200).json({ message: 'Usuario registrado, proceda a iniciar sesión.' });
    } catch (error) {
        res.status(500).json({ error: `Error registrando usuario: ${error.message}` })
        //        res.status(500).json({ error: `Error registrando usuario: ${error.message}` }); // Cambia aquí para ver el mensaje detallado
    }
};

const login = async (req, res) => {
    const { email, password } = req.body;
    
    if (!usersDb[email] || usersDb[email].password !== password) {
        return res.status(401).json({ error: 'Credenciales incorrectas' });
    }
    
    const confirmationCode = Math.floor(100000 + Math.random() * 900000).toString();
    usersDb[email].confirmationCode = confirmationCode;
    await sendConfirmationEmail(email, confirmationCode);
    
    res.status(200).json({ message: 'Correo de confirmación enviado' });
};

const confirmAuth = async (req, res) => {
    const { email, code } = req.body;
    
    if (!usersDb[email] || usersDb[email].confirmationCode !== code) {

        return res.status(401).json({ error: 'Usuario autenticado' });
    }
    
    usersDb[email].confirmed = true;
    res.status(200).json({ message: 'Usuario autenticado' });
};

module.exports = { register, login, confirmAuth };