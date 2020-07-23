const axios = require('axios');
const fsUtil = require('./util/fs');
const JSONUtil = require('./util/JSON');
const PromiseUtil = require('./util/Promise');
const https = require('https');
const {resolve} = require('path');
const Collectors = require('./util/Collectors');

function savePNG(url, filename) {
    return axios.get(url, {
        responseType: "arraybuffer"
    }).then(
        ({data: buffer}) => {

            return fsUtil.writeFile(resolve(__dirname, filename), buffer)

        }
    );
}

(async request => {
    const articles = ["496752", "496751", "496750", "496749", "496748", "496747", "489623", "489619", "489618", "489617", "489616", "489614", "488339", "488338", "488335", "488334", "488333", "488332", "485049", "485048", "485047", "485046", "485045", "485044", "485043", "485042", "485038", "485037", "485035", "485033", "485032", "485030", "485029", "485027", "485026", "485024", "485022", "485021", "485019", "485018", "485016", "485014", "482656", "482655", "482653", "482652", "482651", "482650", "480390", "480389"]
    // const articles = ["391516", "391515", "387810", "387809", "379583", "379582", "379573", "379572", "379571", "379570", "379567", "379565", "379564", "379563", "379562", "379561"]

    console.time('下载完成')
    const result = JSONUtil.stringify(await PromiseUtil
        .doChain(Collectors.toPages(articles, 3), request)
    );

    console.timeEnd('下载完成')
    console.log(result)
    fsUtil.writeFile('下载结果.json', result);

})(article => axios.get(`https://apis.naver.com/cafe-web/cafe-articleapi/v1/cafes/28708849/articles/${article}?useCafeId=true&requestFrom=A`, {
        "headers": {
            "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
            "accept-language": "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
            "cache-control": "max-age=0",
            "sec-fetch-dest": "document",
            "sec-fetch-mode": "navigate",
            "sec-fetch-site": "cross-site",
            "sec-fetch-user": "?1",
            "upgrade-insecure-requests": "1",
            "cookie": "NNB=4HPLPEYWDQAF6; MM_NOW_COACH=2; NFS=3; MM_NEW=5; nid_inf=-1464845232; NID_AUT=+3DsENRx2zlm3xhdU9scNX9xVMboikO2sGRDkwDkfINSB+9M4Y1HH176MJxHptE1; NID_JKL=oEtiNuKvYXShnnYmBFTzXbTmI5Uchjh0UxLPfQh0pTY=; NID_SES=AAABh5OddFzFj15Pj2gfRzrExzKCX7HxBoy5iw/oA7qJEav8x7KJC5lrJmevENNjm+EBtMlREZjSujKFfE9s5b0M5DRgrmXlyWhRvtvaF5LxONmv6ZJ1+beD/8P6FefdagxM2EECn6llO8jUQdhJBFW0xR7dU4zAlXD3UDYQMmq5DI/xZZi36bQpEXQEp6oM5PZtWWa9+3FWwDipSDUoOxFNy4vmw3gponMppFa4Q7bFdk/k/8hghlF2IFkNg0Fger+C4yesX62kEAdasC7RMwTHXwCvL66kXazGKW2Q4h18R0cNclV5QEYPzlcIeZdg5As/flrrrnbuQen5LONGna1TEm4Hu+MtSx1geTSVDEgRHvHzL9Q3ZuU1tIpuTokciiXMW1bjNBWJl/Ur797XYAa1EYdO0vKpS4A3Gf/NdagKzNGwvf3amY6E5qUhuKccMsc5+5dbSLOSRAVfi8H+QIZCVuSogboG/o0rQ1mumqE54lFLhRj418+yIJcgE+aYs+rddjdQbJ1vdM0TmEjbI/1hK8w="
        },
    }).then(({data, data: {article: {contentElements}}}) => {
        console.log(`文章ID ${article.padStart(6)}, 共有 ${contentElements.length} 张图片！`);

        return PromiseUtil.doChain(
            Collectors.toPages(contentElements, 2),
            ({type, json}) => {
                const image = json.originalImage;
                console.log(`下载图片 ${image.originUrl}`)
                if (type === 'IMAGE') {
                    return savePNG(
                        image.originUrl,
                        `./package/棕色尘埃/${article}/${image.originName}`,
                    )/*.then(value => fsUtil.copyFile(
                        resolve(__dirname, `棕色尘埃/${article}/${image.originName}`),
                        resolve(__dirname, `棕色尘埃/${image.originName}`))
                    )*/;
                } else
                    throw `未知类型，${type}`
            }
        );
    }).then(results => {
        return {
            article,
            message: value,
            children: results
        };
    })
)
