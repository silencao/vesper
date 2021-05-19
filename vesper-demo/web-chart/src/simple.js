(split => {
    console.log(split(2739, 4));
    console.log(split(2839, 4));
    console.log(split(61321, 5));
    console.log(split(631321, 6));
    console.log(split(3540312, 5));
    console.log(split(42140312, 6));
})((max, part) => {
    function toLabel(x) {
        let unit;

        unit = 10000
        if (x > unit) return (x / unit) + ' 万';

        return x;
    }
    let tmp1 = max / part;
    let length = tmp1.toFixed().length;
    let number = Math.pow(10, length - (length > 4 ? 2 : 1));
    let x = Math.floor(tmp1 / number) * number;

    let arr = [{
        label: toLabel(x) + '以下',
        max: x
    }];

    for (let i = 1;i<part;i++) {
        let min = i * x;
        let max = (i+1)*x;
        arr.push({
            label: toLabel(min) + ' ~ ' + toLabel(max),
            max,
            min
        })
    }

    let min = x * part;
    arr.push({
        label: toLabel(min) + '以上',
        min: min
    })

    console.log(arr);
})