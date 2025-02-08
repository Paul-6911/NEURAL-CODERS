const nodemailer = require('nodemailer');
const { EMAIL_SERVICE, EMAIL_USER, EMAIL_PASSWORD } = require('../config/env');

const transporter = nodemailer.createTransport({
    service: EMAIL_SERVICE,
    auth: {
        user: EMAIL_USER,
        pass: EMAIL_PASSWORD,
    },
});

const sendConfirmationEmail = async (email, code) => {
    const mailOptions = {
        from: EMAIL_USER,
        to: email,
        subject: "Código de Confirmación",
        text: `Tu código de autenticación es: ${code}`,
    };

    return transporter.sendMail(mailOptions);
};

module.exports = { sendConfirmationEmail };
