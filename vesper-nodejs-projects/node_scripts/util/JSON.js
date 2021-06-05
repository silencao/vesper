function stringify(list, replacer, spaces = 2) {
    const space = Array(spaces + 1).join(' ');

    return '[\n' + list
            .map(item => space + JSON.stringify(item, replacer
                ? (Array.isArray(replacer) || typeof replacer === 'function')
                    ? replacer
                    : (key, value) => {
                        const func = replacer[key];

                        return func
                            ? func(value)
                            : value;
                    }
                : null))
            .join(',\n') +
        '\n]'
}

export const JSONArray = {
    stringify
}

export const JSONObject = {
    stringify(obj) {
        const maxLength = Object.keys(obj).map(k => k.length).reduce((l, r) => l > r ? l : r);
        const entries = Object.entries(obj);
        return ('{\n' +
            entries.map(function ([type, item]) {
                return `    ${(`"${type}"`).padStart(maxLength + 2)}: ${JSON.stringify(item)}`
            }).join(',\n') +
        '\n}');
    }
}
