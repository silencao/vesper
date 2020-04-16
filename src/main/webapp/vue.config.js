const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, '../resources/static'),
    configureWebpack: require('./webpack.config.js'),
    devServer: {
        proxy: 'http://localhost:8080',
        port: 3000
    }
};
