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

module.exports = {
    stringify
}