function assert(str, num) {
    const reduceRight = Array.from(str)
        .reverse()
        .reduce(
            (acc, str, idx) =>
                acc + Math.pow(26, idx) * (str.charCodeAt(0) - 96),
            0
        );
    console.assert(reduceRight === num, 'err', [str, num, reduceRight]);
}

assert('', 0);
assert('a', 1);
assert('z', 26);
assert('az', 26 + 26);
assert('aa', 26 + 1);
assert('ba', 26 * 2 + 1);
assert('ca', 26 * 3 + 1);
assert('za', 26 * 26 + 1);
assert('zz', 26 * 26 + 26);
assert('aaa', 26 * 26 + 26 + 1);
