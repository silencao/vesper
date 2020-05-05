const path = require('path');

module.exports = {
  outputDir: path.resolve(__dirname, '../resources/static'),
  configureWebpack: require('./webpack.config.js'),
  devServer: {
    proxy: {
      '/api': {
          target: 'http://localhost:8080',
          changeOrigin: true,
          pathRewrite: {'^/api' : ''}
      }
    },
    port: 3000
  }
};
