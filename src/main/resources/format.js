let json = require('./growths.json')

console.log('[\n' + json.map(o => '  ' + JSON.stringify(o, (key, value) => {
    if (key === 'sum') {
        return value.match(/(\d+\.?\d?)|[a-z]+/g).reduce((numStr, unit)=> parseFloat(numStr).toFixed(1) + unit).padStart(6);
    }

    return value;
})).join(',\n') + '\n]');