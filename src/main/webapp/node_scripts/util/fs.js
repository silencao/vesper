const fs = require('fs');
const {dirname} = require('path');
const axios = require('axios')
const Collectors = require('./Collectors');
const PromiseUtil = require('./Promise');
const fsPromises = fs.promises;

const PNG = Buffer.from([137, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0]);

function isPNG(buffer) {
    return buffer.slice(0, PNG.length).equals(PNG);
}

function oprWrapper(oprSupplier, key) {
    return oprSupplier()
        .catch(function (reason) {
            if (reason.code === 'ENOENT') {
                return fsPromises.mkdir(dirname(reason[key]), {
                    recursive: true
                })
            } else {
                throw reason;
            }
        })
        .then(oprSupplier);
}

function writeFile(path, data) {
    return oprWrapper(() => fsPromises.writeFile(path, data), 'path');
}

function copyFile(src, dest) {
    return oprWrapper(() => fsPromises.copyFile(src, dest), 'dest');
}

function readFile(path) {
    return fsPromises.readFile(path, "UTF-8")
}

function saveFile(url, path) {
    return axios.get(url, {
        responseType: "arraybuffer"
    }).then(({data}) => writeFile(path, data))
}

function saveFiles(items, genPromise, pageSize = 3) {
    const pageDataList = Collectors.toPages(items, pageSize);

    return pageDataList.reduce((promise, pageData) =>
        promise.then(prevResults =>
            Promise.allSettled(pageData.map(item =>
                Promise.resolve().then(() =>
                    genPromise(item, (from, to) =>
                        saveFile(from, to).then(() => ({
                            count: prevResults.success.push(from)
                        }), ({message: msg}) => {
                            prevResults.failure.push({url: from, msg})
                            throw msg
                        })
                    ))
            )).then(() => prevResults)
        ), Promise.resolve({
        total: items.length,
        success: [],
        failure: []
    })).then(result => {
        function stringify() {
            let {total, success, failure} = result;

            return `共下载${total}个文件\n`
                + success.reduce((str, obj) => `${str}  ${obj}\n`, `成功${success.length}个: \n`)
                + failure.reduce((str, obj) => `${str}  ${obj.url}(${obj.msg})\n`, `失败${failure.length}个: \n`)
        }

        return {
            result,
            print() {
                console.log(stringify);
            },
            save(path) {
                writeFile(path, stringify())
            }
        }
    });
}

module.exports = {
    isPNG,
    readFile,
    writeFile,
    copyFile,
    saveFiles
}
