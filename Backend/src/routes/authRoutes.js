// backend/src/routes/authRoutes.js
const express = require('express');
const { register, login, confirmAuth } = require('../controllers/authController');
const router = express.Router();

router.post('/register', register);
router.post('/login', login);
router.post('/confirm-auth', confirmAuth);
module.exports = router;
