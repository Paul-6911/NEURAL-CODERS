// backend/src/config/firebase.js
const admin = require('firebase-admin');
const serviceAccount = require('../../firebaseServiceAccountKey.json');

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://neuralcoders-back-default-rtdb.firebaseio.com/"
});

module.exports = admin;