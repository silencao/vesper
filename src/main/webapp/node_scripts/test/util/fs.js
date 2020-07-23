const {saveFiles} = require('../../util/fs')

saveFiles([62, 101, 119, 199], (roleID, saveFile) => {
        return saveFile(
            `https://ic-common.pmang.cloud/static/bdt_book/illust/npc${roleID}_6.png`,
            `./下载/${roleID}.png`
        ).then(result => {
            console.log(roleID);
        });
    }, 2
).then(logger => {
    logger.save('./日志/下载结果.json')
})