const axios = require('axios');
const path = require('path');
const fsUtil = require('../util/fs');
const JSONUtil = require('../util/JSON');

function saveAliMap(district) {
    const {adcode} = district;

    if (district.level === 'street') {
        return `${district.name}不需要下载地图！`
    } else {
        return axios.get(`https://restapi.amap.com/v3/config/district?keywords=${adcode}&key=531bb49d57108b57d5863b3a06304270&extensions=all`)
            .then(res => {
                    const {data     } = res;
                    const {districts} = data;

                    if (data.status === '1') {
                        return districts.reduce(function (chain, curDistrict) {
                            return chain.then(() => {
                                return fsUtil
                                    .writeFile(path.resolve(__dirname, `./data/${adcode}.json`), JSON.stringify(curDistrict))
                                    .then(() => curDistrict.districts.reduce(function (chain, childDistrict) {
                                        return chain.then(() => saveAliMap(childDistrict));
                                    }, chain))
                                    .then(() => `${curDistrict.adcode}-${curDistrict.name}下载成功！`)

                            }).then(log => console.log(log));
                        }, Promise.resolve());
                    } else {
                        throw new Error("请求失败")
                    }
                }
            );
    }
}

function makeFeature(district) {
    const meta = require(`./data/${district.adcode}`);

    return {
        "type": "Feature",
        "properties": {
            "adcode": district.adcode,
            "name": district.name
        },
        "geometry": {
            "type": "MultiPolygon",
            "coordinates": meta.polyline.split("|").map(coordinate => [
                coordinate.split(';').map(coordinate =>
                    coordinate.split(',').map(parseFloat))
            ])
        }
    };
}

function makeGeoJson(district) {
    const meta = require(`./data/${district.adcode}.json`);

    if (district.level === 'street') {
        return `${district.name}不需要生成地图！`;
    } else {
        const result = {
            type: "FeatureCollection"
        };

        if (district.adcode.endsWith('00')) {
            result.features = meta.districts.map(makeFeature);
        } else {
            result.features = [makeFeature(meta)];
        }

        return fsUtil
            .writeFile(path.resolve(__dirname, `./map/${district.adcode}.json`), JSON.stringify(result))
            .then(() => meta.districts
                .reduce((chain, district) => chain.then(() => makeGeoJson(district)), Promise.resolve()))
            .then(() => console.log(`${meta.adcode}-${meta.name}创建成功！`))
    }
}

(async () => {
    await saveAliMap ({adcode: '330000'});
    await makeGeoJson({adcode: '330000'});

    console.log('执行完成！')
})();