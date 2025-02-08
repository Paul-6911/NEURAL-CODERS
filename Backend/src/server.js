// backend/src/server.js
const app = require('./app');
const { PORT } = require('./config/env');

app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
