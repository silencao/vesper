function doChain(pageDataList, genPromise) {
    return pageDataList.reduce((promise, pageData) => promise
        .then(prevResults => Promise
            .allSettled(pageData
                .map(genPromise))
            .then(curResults => {
                prevResults.results.push(...curResults)

                return prevResults;
            })
        ), Promise.resolve({
        count: 0,
        results: []
    }));
}

module.exports = {doChain}