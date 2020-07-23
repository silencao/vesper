function toPages(list, pageSize) {
    return list.reduce((pages, item, idx) => {
        if (idx % pageSize > 0) {
            pages[Math.floor(idx / pageSize)].push(item)
        } else {
            pages.push([item]);
        }

        return pages;
    }, []);
}

module.exports = {toPages}
