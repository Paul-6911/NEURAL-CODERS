// backend/src/config/env.js
require('dotenv').config();

module.exports = {
    PORT: process.env.PORT || 3000,
    EMAIL_SERVICE: process.env.EMAIL_SERVICE,
    EMAIL_USER: process.env.EMAIL_USER,
    EMAIL_PASSWORD: process.env.EMAIL_PASSWORD,
};
