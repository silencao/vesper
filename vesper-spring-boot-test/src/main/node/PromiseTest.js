const JSONUtil = require('./util/JSON');
const fsUtil = require('./util/fs');
const Collectors = require('./util/Collectors');

(async axios => {
    // const articles = ["106958", "103183", "103182", "88845", "88841", "82104", "82103", "75600", "75599", "75598", "69969", "69968", "66935", "66934", "66933", "63600", "63599", "56360", "56348", "48917", "48914", "48909", "48907", "32676", "32675", "24584", "24582", "13227", "5464", "5461", "5454", "5453", "5451", "5450", "128", "127", "126", "125", "124", "121", "118", "111", "109", "108", "103", "101", "97", "94", "93", "89"];
    const articles = [
        {id: "106958", data:['a1', 'b2', 'c3']},
        {id: "103183", data:['b3', 'd1']},
        {id: "103182", data:['c4']},
        {id: "88845" , data:['a6', 'b3', 'c2', 'd9']},
        {id: "88841" , data:['e5', 'g7']}
     ]
    const pageDataList = Collectors.toPages(articles, 1);
    console.log(pageDataList);
    console.time('asd')
    const result = JSONUtil.stringify(await Collectors.doChain(pageDataList, article => axios(article.id).then(value => {

        return Collectors.doChain(
            Collectors.toPages(article.data, 1)
            , axios
        ).then(results => {

            return {
                message: value,
                children: results
            };
        })

    })));
    console.log("下载完成！" + result)
    console.timeEnd('asd')
    fsUtil.writeFile('下载结果.json', result);
})(p => new Promise((resolve, reject) => {
    const x = 1000;
    const delay = Math.random() * 2 * x;
    const message = `p${p.padStart(7, '-')}`;
    console.log(`${message}: after ${(delay / 1000).toFixed(1)}s`)
    setTimeout(() => {
        console.log(`${message}: do`)
        delay > x ? resolve(message + ' 请求成功：') : reject(message + ' 请求失败！')
    }, delay)
}))