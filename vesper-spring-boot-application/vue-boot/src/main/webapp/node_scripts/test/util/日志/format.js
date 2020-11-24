const json = require('./下载结果.json');

console.log(json);

console.log((function (result) {
    let {total, success, failure} = result;

    return `共下载${total}个文件\n`
        + success.reduce((str, obj) => `${str}  ${obj}\n`, `成功${success.length}个: \n`)
        + failure.reduce((str, obj) => `${str}  ${obj.url}(${obj.msg})\n`, `失败${failure.length}个: \n`)
})(json));