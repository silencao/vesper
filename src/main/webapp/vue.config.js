const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, '../resources/static'),
    configureWebpack: require('./webpack.config.js'),
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
                pathRewrite: { '^/api': '' }
            }
        },
        port: 3000 /*
          前端请求：http://localhost:3000/api/user',
          代理请求：http://localhost:8080/user',    pathRewrite 配置重写了路径
      */
    }
};
