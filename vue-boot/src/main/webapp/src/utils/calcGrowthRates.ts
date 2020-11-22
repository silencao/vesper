function splitAndCast(numStr: string): number[] {
    const strings: string[] = numStr.match(/(\d+\.?\d?)|[a-z]+/g) || [];

    return [
        Number(strings[0]),
        strings.length > 1
            ? Array.from(strings[1]).reverse().reduce((acc, str, idx) => acc + Math.pow(26, idx) * (str.charCodeAt(0) - 96), 0)
            : 0
    ]
}

function calcGrowthRates(small: string, large: string): number {
    const smalls = splitAndCast(small)
    const larges = splitAndCast(large)

    return larges[0] / smalls[0] * Math.pow(1000, larges[1] - smalls[1]);
}

function assert(small: string, large: string, num: number): unknown {
    let res = calcGrowthRates(small, large);
    return console.assert(res === num, 'error: ', [
        `${small} -> ${large} => ${num}, get ${res}`
    ])
}

function log(data: string[]): void {
    console.table(data.map(value => {
        const [small, large] = value.split(' -> ');

        return {
            'old': small,
            'new': large,
            'res': `${(calcGrowthRates(small, large) * 100).toFixed()}%`
        }
    }))
}

assert('1', '800', 800)
assert('500', '1a', 2)
assert('1a', '2a', 3)
log([
    '0.1 -> 2',
    '1a -> 200a',
    '500 -> 2b',
    '800zz -> 1aaa',
    ' 13.3a ->  23.0b',
])