const fsUtil = require('./util/fs');
const JSONUtil = require('./util/JSON');
const Collectors = require('./util/Collectors');

(async () =>{
    fsUtil.readFile("./asset/Mesh/aidang_2-mesh.obj").then(rawStr => {
        const message = rawStr.match(/(?<=vt\s).+(?=\r\n)/g).map(str=>{
            return str.split(/\s/).reduce((x,y)=>{

                // return {x:parseFloat(x) * 1024,y:parseFloat(y)*484}
                return {
                    x: Math.round(parseFloat(x) * 1024),
                    y: Math.round(parseFloat(y) * 484)
                }
            })
        });
        const match = Collectors.toPages(rawStr.match(/(?<=v\s).+(?=\s0\r\n)/g),4);

        const pages = Collectors.toPages(message, 4).map((image,idx)=>{
            let [lb,lt,rt,rb]=image;
            let [l,b]=match[idx][1].split(/\s/);
            return {
                width:rt.x-lt.x + 2,
                height:lt.y-lb.y+2,
                x:1-lt.x,
                y: lt.y- 483,
                bottom: b+'px',
                left:parseInt(-l)+'px'
            }
        });
        console.log(pages.length);
        fsUtil.writeFile('../src/assets/json/aidang_2-mesh.json', JSONUtil.stringify(pages))
    })
})()