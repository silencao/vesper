async function fn(x) {
    if (Math.random()>0.5)
    return x;
    else
        throw x*2;
}

fn(1).then(value => {

},reason => {

})

Promise.resolve(fn(2)).then(value => {

},reason => {

})

Promise.resolve({
    then:fn
}).then(value => {

},reason => {

})