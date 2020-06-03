const path = require('path');

module.exports = {
  outputDir: path.resolve(__dirname, '../resources/static'),
  configureWebpack: require('./webpack.config.js'),
  devServer: {
    proxy: {
      '/api': {
          target: 'http://localhost:8080',
          // changeOrigin: true,
          pathRewrite: {'^/api' : ''}//后端接口地址为/test，前台访问/api/test时把/api替换为空串
      }
    },
    port: 3000
  }
};
